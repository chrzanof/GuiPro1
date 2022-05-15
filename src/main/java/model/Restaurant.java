package model;

import dao.ClientDao;
import dao.DeliveryManDao;
import dao.Menu;
import dao.WaiterDao;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Restaurant {
    private static Restaurant INSTANCE = null;

    public static final int MAX_WAIT_TIME = 15 * 60;

    private double dailyTakings;
    private WaiterDao waiterDao;
    private DeliveryManDao deliveryManDao;
    private ClientDao clientDao;
    private Kitchen kitchen;
    private List<Order> executedOrders;
    private Menu menu;

    private Restaurant() {
        this.executedOrders = new ArrayList<>();
    }

    public static Restaurant getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Restaurant();
        }
        return INSTANCE;
    }

    public List<DeliveryMan> findFreeDeliveryMen() {
        return this.getDeliveryManDao().getAll().stream()
                .filter(x->x.isFree())
                .collect(Collectors.toList());
    }

    private void deliverOrders(long startTime, Map<Long, DeliveryOrder> orders) {
        int deliveryTime = Kitchen.DELIVERY_TIME;
        Map.Entry<Long, DeliveryOrder> prevEntry = null;
        Iterator<Map.Entry<Long, DeliveryOrder>> iterator = orders.entrySet().iterator();
        Set<Long> keySet = orders.keySet();

        long secondsPassed = returnMinFromSet(keySet);
        while (iterator.hasNext()) {
            List<DeliveryMan> freeDeliveryMen = findFreeDeliveryMen();
            if(freeDeliveryMen.size() > 0) {
                Map.Entry<Long, DeliveryOrder> entry = iterator.next();
                entry.getValue().setDeliveryMan(freeDeliveryMen.get((int)(Math.random() * freeDeliveryMen.size())));
                entry.getValue().getDeliveryMan().setFree(false);
                entry.getValue().setDelivered(true);

            }
            secondsPassed++;
        }


    }

    private long returnMinFromSet(Set<Long> set) {
        Long[] array = set.toArray(new Long[0]);
        long min = array[0];
        for (int i = 0; i < array.length; i++) {
            if(min > array[i]){
                min = array[i];
            }
        }
        return min;
    }

    public void startWork() {
        Random random = new Random();
        int realizationTime = Kitchen.REALIZATION_TIME / this.getKitchen().getCookDao().getAll().size();
        int deliveryTime = Kitchen.DELIVERY_TIME;
        long startTime;
        Optional<StationaryOrder> optionalStationaryOrder = this.kitchen.getStationaryOrderDao().get(0);
        Optional<DeliveryOrder> optionalDeliveryOrder = this.kitchen.getDeliveryOrderDao().get(0);
        if(optionalStationaryOrder.isPresent() && optionalStationaryOrder.get().getPlaceDate().getTime() < optionalDeliveryOrder.get().getPlaceDate().getTime()) {
            startTime = optionalStationaryOrder.get().getPlaceDate().getTime();
        } else {
            startTime = optionalDeliveryOrder.get().getPlaceDate().getTime();
        }
        long secondsPassed = 0;
        int dt = 0;

        StationaryOrder prevStationaryOrder = null;
        System.out.println("Na miejscu");
        System.out.println();
        while (this.getKitchen().getStationaryOrderDao().getAll().size() > 0)
        {
            StationaryOrder stationaryOrder = this.getKitchen().getStationaryOrderDao().get(0).get();
            if(prevStationaryOrder == null || !prevStationaryOrder.equals(stationaryOrder)) {
                dt += stationaryOrder.totalNumberOfItems() * realizationTime;
                prevStationaryOrder = stationaryOrder;
            }
                if (secondsPassed > dt) {
                    stationaryOrder.setReady(true);
                    System.out.println(secondsPassed + ": Podano zamówienie " + stationaryOrder.getId());
                    if (startTime + secondsPassed > stationaryOrder.getPlaceDate().getTime() + MAX_WAIT_TIME){
                        System.out.println(secondsPassed+ ":Spóźnienie");
                        stationaryOrder.setRejected(random.nextBoolean());
                        if(stationaryOrder.isRejected) {
                            System.out.println(secondsPassed + ": Odrzucono zamówienie " + stationaryOrder.getId());
                        } else {
                            System.out.println(secondsPassed + ": Przyjęto zamówienie " + stationaryOrder.getId());
                        }
                    } else {
                        int tipSum = stationaryOrder.getWaiter().getTipSum();
                        int tip = (int)(stationaryOrder.getTotalPrice()
                                * 0.1
                                * (stationaryOrder.getPlaceDate().getTime() + MAX_WAIT_TIME - startTime + secondsPassed)
                                / (stationaryOrder.getPlaceDate().getTime() + MAX_WAIT_TIME));
                        tipSum += tip;
                        stationaryOrder.getWaiter().setTipSum(tipSum);
                        stationaryOrder.setTotalPrice(stationaryOrder.getTotalPrice()*0.8);
                    }
                    stationaryOrder.getWaiter().setNumberOfOrdersExecuted(stationaryOrder.getWaiter().getNumberOfOrdersExecuted() + 1);
                    this.executedOrders.add(stationaryOrder);
                    this.getKitchen().getStationaryOrderDao().delete(stationaryOrder);
                }

            secondsPassed++;
        }

        DeliveryOrder prevDeliveryOrder = null;
        Map<Long, DeliveryOrder> ordersToDeliver = new HashMap<>();
        System.out.println("Na wynos");
        System.out.println();
        while (this.getKitchen().getDeliveryOrderDao().getAll().size() > 0)
        {
            DeliveryOrder deliveryOrder = this.getKitchen().getDeliveryOrderDao().get(0).get();
            if(prevDeliveryOrder == null || !prevDeliveryOrder.equals(deliveryOrder)) {
                dt += deliveryOrder.totalNumberOfItems() * realizationTime;
                prevDeliveryOrder = deliveryOrder;
            }
            if (secondsPassed > dt + deliveryTime) {
                deliveryOrder.setReady(true);
                deliveryOrder.setDelivered(true);
                deliveryOrder.setDeliveryMan(deliveryManDao.get((int)(Math.random() * deliveryManDao.getAll().size())).get());
                ordersToDeliver.put(secondsPassed, deliveryOrder);
                System.out.println(secondsPassed + ": Dostarczono zamówienie " + deliveryOrder.getId());

                if (startTime + secondsPassed > deliveryOrder.getPlaceDate().getTime() + MAX_WAIT_TIME){
                    System.out.println(secondsPassed+ ":Spóźnienie");
                    deliveryOrder.setRejected(random.nextBoolean());
                    if(deliveryOrder.isRejected) {
                        System.out.println(secondsPassed + ": Odrzucono zamówienie " + deliveryOrder.getId());
                    } else {
                        System.out.println(secondsPassed + ": Przyjęto zamówienie " + deliveryOrder.getId());
                    }
                }
                else {
                    int tipSum = deliveryOrder.getDeliveryMan().getTipSum();
                    int tip = (int)(deliveryOrder.getTotalPrice()
                            * 0.1
                            * (deliveryOrder.getPlaceDate().getTime() + MAX_WAIT_TIME - startTime + secondsPassed)
                            / (deliveryOrder.getPlaceDate().getTime() + MAX_WAIT_TIME));
                    tipSum += tip;
                    deliveryOrder.getDeliveryMan().setTipSum(tipSum);
                    deliveryOrder.setTotalPrice(deliveryOrder.getTotalPrice()*0.8);
                }
                deliveryOrder.getDeliveryMan().setNumberOfOrdersDelivered(deliveryOrder.getDeliveryMan().getNumberOfOrdersDelivered() + 1);

                this.executedOrders.add(deliveryOrder);
                this.getKitchen().getDeliveryOrderDao().delete(deliveryOrder);
            }

            secondsPassed++;
        }
        //deliverOrders(startTime, ordersToDeliver);
    }




    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void addExecutedOrder(Order order) {
        this.executedOrders.add(order);
    }

    public double getDailyTakings() {
        return dailyTakings;
    }

    public void setDailyTakings(double dailyTakings) {
        this.dailyTakings = dailyTakings;
    }

    public WaiterDao getWaiterDao() {
        return waiterDao;
    }

    public void setWaiterDao(WaiterDao waiterDao) {
        this.waiterDao = waiterDao;
    }

    public DeliveryManDao getDeliveryManDao() {
        return deliveryManDao;
    }

    public void setDeliveryManDao(DeliveryManDao deliveryManDao) {
        this.deliveryManDao = deliveryManDao;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public List<Order> getExecutedOrders() {
        return executedOrders;
    }

    public void setExecutedOrders(List<Order> executedOrders) {
        this.executedOrders = executedOrders;
    }
}
