package model;

public class DeliveryMan extends Employee {
    private double tipSum;
    private int numberOfOrdersDelivered;
    private boolean isFree;
    public DeliveryMan() {
        this.isFree = true;
    }

    public DeliveryMan(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
        this.isFree = true;
    }

    @Override
    public String toString() {
        return super.toString() +
                " " + tipSum + "zł" +
                " " + numberOfOrdersDelivered;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public int getNumberOfOrdersDelivered() {
        return numberOfOrdersDelivered;
    }

    public void setNumberOfOrdersDelivered(int numberOfOrdersDelivered) {
        this.numberOfOrdersDelivered = numberOfOrdersDelivered;
    }

    public double getTipSum() {
        return tipSum;
    }

    public void setTipSum(double tipSum) {
        this.tipSum = tipSum;
    }
}
