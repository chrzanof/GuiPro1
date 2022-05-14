package model;

public class MenuItem {
    private static long nextId = 1;
    private long id;
    private String name;
    private String description;
    private double price;
    private boolean isAvailable;

    public MenuItem() {
        this.id = nextId++;
    }

    public MenuItem(String name, String description, double price, boolean isAvailable) {
        this.id = nextId++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
