package dao;

import model.StationaryOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StationaryOrderDao implements Dao<StationaryOrder>{
    private List<StationaryOrder> stationaryOrders;

    public StationaryOrderDao() {
        this.stationaryOrders = new ArrayList<>();
    }

    public StationaryOrderDao(List<StationaryOrder> stationaryOrders) {
        this.stationaryOrders = stationaryOrders;
    }

    @Override
    public Optional<StationaryOrder> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<StationaryOrder> getAll() {
        return null;
    }

    @Override
    public void save(StationaryOrder stationaryOrder) {

    }

    @Override
    public void update(StationaryOrder stationaryOrder, String[] params) {

    }

    @Override
    public void delete(StationaryOrder stationaryOrder) {

    }
}
