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
    public Optional<StationaryOrder> get(long index) {
        return Optional.ofNullable(stationaryOrders.get((int)index));
    }

    @Override
    public List<StationaryOrder> getAll() {
        return stationaryOrders;
    }

    @Override
    public void save(StationaryOrder stationaryOrder) {
        stationaryOrders.add(stationaryOrder);
    }


    @Override
    public void delete(StationaryOrder stationaryOrder) {
        stationaryOrders.remove(stationaryOrder);
    }
}
