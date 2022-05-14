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
    public Optional<Waiter> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Waiter> getAll() {
        return null;
    }

    @Override
    public void save(Waiter waiter) {

    }

    @Override
    public void update(Waiter waiter, String[] params) {

    }

    @Override
    public void delete(Waiter waiter) {

    }
}
