package model;

import java.sql.Time;
import java.util.Map;

public class StationaryOrder extends Order {
    private int tableNumber;
    public StationaryOrder() {
    }

    public StationaryOrder(Map<MenuRow, Integer> orderedItems, Time placeTime, double totalPrice, Client client, int tableNumber) {
        super(orderedItems, placeTime, totalPrice, client);
        this.tableNumber = tableNumber;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
