package dao;

import model.Cook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookDao implements Dao<Cook>{
    private List<Cook> cooks;

    public CookDao() {
        this.cooks = new ArrayList<>();
    }

    public CookDao(List<Cook> cooks) {
        this.cooks = cooks;
    }

    @Override
    public Optional<Cook> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Cook> getAll() {
        return null;
    }

    @Override
    public void save(Cook cook) {

    }

    @Override
    public void update(Cook cook, String[] params) {

    }

    @Override
    public void delete(Cook cook) {

    }
}
