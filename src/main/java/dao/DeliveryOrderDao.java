package dao;

import model.DeliveryOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryOrderDao implements Dao<DeliveryOrder>{
    private List<DeliveryOrder> deliveryOrders;

    public DeliveryOrderDao() {
        this.deliveryOrders = new ArrayList<>();
    }

    public DeliveryOrderDao(List<DeliveryOrder> deliveryOrders) {
        this.deliveryOrders = deliveryOrders;
    }

    @Override
    public Optional<DeliveryOrder> get(long index) {
        return Optional.ofNullable(deliveryOrders.get((int)index));
    }

    @Override
    public List<DeliveryOrder> getAll() {
        return deliveryOrders;
    }

    @Override
    public void save(DeliveryOrder deliveryOrder) {
        deliveryOrders.add(deliveryOrder);
    }

    @Override
    public void update(DeliveryOrder deliveryOrder, String[] params) {

    }

    @Override
    public void delete(DeliveryOrder deliveryOrder) {
        deliveryOrders.remove(deliveryOrder);
    }
}
