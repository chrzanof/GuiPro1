package model;

import java.sql.Time;
import java.util.Map;

public class DeliveryOrder extends Order {
    private String address;
    private boolean isDelivered;
    private DeliveryMan deliveryMan;
    public DeliveryOrder() {
    }

    public DeliveryOrder(Map<MenuRow, Integer> orderedItems, Time placeTime, double totalPrice, Client client, String address) {
        super(orderedItems, placeTime, totalPrice,client);
        this.address = address;
        this.isDelivered = false;
    }

    @Override
    public String toString() {
        return super.toString() +
                " " + address +
                " " + isDelivered +
                " " + deliveryMan;
    }

    public DeliveryMan getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(DeliveryMan deliveryMan) {
        this.deliveryMan = deliveryMan;
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
