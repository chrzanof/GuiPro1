package dao;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu implements Dao<MenuItem> {
    List<MenuItem> itemList;

    public Menu() {
        this.itemList = new ArrayList<>();
    }

    public Menu(List<MenuItem> itemList) {
        this.itemList = itemList;
    }


    @Override
    public Optional<MenuItem> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<MenuItem> getAll() {
        return this.itemList;
    }

    @Override
    public void save(MenuItem menuItem) {

    }

    @Override
    public void update(MenuItem menuItem, String[] params) {

    }

    @Override
    public void delete(MenuItem menuItem) {

    }
}
