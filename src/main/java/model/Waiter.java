package model;

public class Waiter extends Employee {
    private double tipSum;
    public Waiter() {
    }

    public Waiter(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
    }

    public double getTipSum() {
        return tipSum;
    }

    public void setTipSum(double tipSum) {
        this.tipSum = tipSum;
    }
}
