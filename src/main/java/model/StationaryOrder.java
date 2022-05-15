package model;

import java.sql.Time;
import java.util.Map;

public class StationaryOrder extends Order {
    private int tableNumber;
    private Waiter waiter;
    public StationaryOrder() {
    }

    public StationaryOrder(Map<MenuRow, Integer> orderedItems, Time placeTime, double totalPrice, Client client, int tableNumber) {
        super(orderedItems, placeTime, totalPrice, client);
        this.tableNumber = tableNumber;
    }

    @Override
    public String toString() {
        return super.toString() +
                " " + tableNumber +
                " " + waiter;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
