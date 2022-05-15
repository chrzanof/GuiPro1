package dao;

import model.DeliveryMan;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryManDao implements Dao<DeliveryMan> {
    private List<DeliveryMan> deliveryManList;

    public DeliveryManDao() {
        this.deliveryManList = new ArrayList<>();
    }

    public DeliveryManDao(List<DeliveryMan> deliveryManList) {
        this.deliveryManList = deliveryManList;
    }

    @Override
    public Optional<DeliveryMan> get(long index) {
        return Optional.ofNullable(deliveryManList.get((int)index));
    }

    @Override
    public List<DeliveryMan> getAll() {
        return deliveryManList;
    }

    @Override
    public void save(DeliveryMan deliveryMan) {
        deliveryManList.add(deliveryMan);
    }


    @Override
    public void delete(DeliveryMan deliveryMan) {
        deliveryManList.remove(deliveryMan);
    }
}
