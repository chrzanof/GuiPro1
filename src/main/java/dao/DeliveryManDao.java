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
    public Optional<DeliveryMan> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<DeliveryMan> getAll() {
        return null;
    }

    @Override
    public void save(DeliveryMan deliveryMan) {

    }

    @Override
    public void update(DeliveryMan deliveryMan, String[] params) {

    }

    @Override
    public void delete(DeliveryMan deliveryMan) {

    }
}
