package model;

public class Waiter extends Employee {
    private int tipSum;
    private int numberOfOrdersExecuted;
    public Waiter() {
    }

    public Waiter(String name, String surname, String phoneNumber) {
        super(name, surname, phoneNumber);
    }

    @Override
    public String toString() {
        return super.toString() +
                " " + tipSum + "zł" +
                " " + numberOfOrdersExecuted;
    }

    public int getNumberOfOrdersExecuted() {
        return numberOfOrdersExecuted;
    }

    public void setNumberOfOrdersExecuted(int numberOfOrdersExecuted) {
        this.numberOfOrdersExecuted = numberOfOrdersExecuted;
    }

    public int getTipSum() {
        return tipSum;
    }

    public void setTipSum(int tipSum) {
        this.tipSum = tipSum;
    }
}
