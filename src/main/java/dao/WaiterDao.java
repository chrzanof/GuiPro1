package dao;

import model.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WaiterDao implements Dao<Waiter>{
    private List<Waiter> waiters;

    public WaiterDao() {
        this.waiters = new ArrayList<>();
    }

    public WaiterDao(List<Waiter> waiters) {
        this.waiters = waiters;
    }

    @Override
    public Optional<Waiter> get(long index) {
        return Optional.ofNullable(waiters.get((int)index));
    }

    @Override
    public List<Waiter> getAll() {
        return waiters;
    }

    @Override
    public void save(Waiter waiter) {
        waiters.add(waiter);
    }

    @Override
    public void delete(Waiter waiter) {
        waiters.remove(waiter);
    }
}
