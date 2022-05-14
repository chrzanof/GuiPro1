package model;

import dao.ClientDao;
import dao.DeliveryManDao;
import dao.WaiterDao;

import java.util.*;

public class Restaurant {
    private static Restaurant INSTANCE = null;

    private Map<Date, Double> dailyTakings;
    private WaiterDao waiterDao;
    private DeliveryManDao deliveryManDao;
    private ClientDao clientDao;
    private Kitchen kitchen;
    private List<Order> executedOrders;

    private Restaurant() {
        this.dailyTakings = new HashMap<>();
        this.executedOrders = new ArrayList<>();
    }

    public static Restaurant getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Restaurant();
        }
        return INSTANCE;
    }

    public Map<Date, Double> getDailyTakings() {
        return dailyTakings;
    }

    public void setDailyTakings(Map<Date, Double> dailyTakings) {
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
