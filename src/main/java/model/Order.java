package model;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public abstract class Order {
    protected static long nextId = 1;
    protected long id;
    protected Client client;
    protected Map<MenuItem, Integer> orderedItems;
    protected Time placeTime;
    protected double totalPrice;
    protected boolean isFinished;
    protected boolean isRejected;

    private void calculatePrice() {
        double price = 0;
        for (Map.Entry<MenuItem, Integer> entry : orderedItems.entrySet()) {
            price += entry.getKey().getPrice() * entry.getValue();
        }
        this.totalPrice = price;
    }

    public Order() {
        this.id = nextId++;
        this.orderedItems = new HashMap<>();
    }

    public Order(Map<MenuItem, Integer> orderedItems, Time placeTime, double totalPrice, Client client) {
        this.id = nextId++;
        this.orderedItems = orderedItems;
        this.placeTime = placeTime;
        this.totalPrice = totalPrice;
        this.client = client;
        this.isFinished = false;
        this.isRejected = false;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
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

    public Map<MenuItem, Integer> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Map<MenuItem, Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public Time getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(Time placeTime) {
        this.placeTime = placeTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
