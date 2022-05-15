package model;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class Order {
    protected static long nextId = 1;
    protected long id;
    protected Client client;
    protected Map<MenuRow, Integer> orderedItems;
    protected Date placeDate;
    protected double totalPrice;
    protected boolean isReady;
    protected boolean isRejected;

    @Override
    public String toString() {
        return
                id +
                " " + client +
                " " + orderedItems.toString() +
                " " + placeDate +
                " " + totalPrice +
                " " + isReady +
                " " + isRejected;
    }

    public void calculatePrice() {
        double price = 0;
        for (Map.Entry<MenuRow, Integer> entry : orderedItems.entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        this.totalPrice = price;
    }

    public Order() {
        this.id = nextId++;
        this.orderedItems = new HashMap<>();
    }

    public Order(Map<MenuRow, Integer> orderedItems, Date placeDate, double totalPrice, Client client) {
        this.id = nextId++;
        this.orderedItems = orderedItems;
        this.placeDate = placeDate;
        this.totalPrice = totalPrice;
        this.client = client;
        this.isReady = false;
        this.isRejected = false;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }

    public boolean isRejected() {
        return isRejected;
    }

    public void setRejected(boolean rejected) {
        isRejected = rejected;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Map<MenuRow, Integer> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Map<MenuRow, Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Date getPlaceTime() {
        return placeDate;
    }

    public void setPlaceDate(Date placeDate) {
        this.placeDate = placeDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
