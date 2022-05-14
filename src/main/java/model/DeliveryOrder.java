package model;

import java.sql.Time;
import java.util.Map;

public class DeliveryOrder extends Order {
    private String address;
    private boolean isDelivered;
    public DeliveryOrder() {
    }

    public DeliveryOrder(Map<MenuRow, Integer> orderedItems, Time placeTime, double totalPrice, Client client, String address) {
        super(orderedItems, placeTime, totalPrice,client);
        this.address = address;
        this.isDelivered = false;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }
}
