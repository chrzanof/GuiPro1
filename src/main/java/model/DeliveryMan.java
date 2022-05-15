package model;

public class DeliveryMan extends Employee {
    private double tipSum;
    public DeliveryMan() {
    }

    public DeliveryMan(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
    }

    @Override
    public String toString() {
        return super.toString() +
                " " + tipSum;
    }

    public double getTipSum() {
        return tipSum;
    }

    public void setTipSum(double tipSum) {
        this.tipSum = tipSum;
    }
}
