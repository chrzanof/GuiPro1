package model;

public abstract class Employee {
    protected static long nextId = 1;
    protected long id;
    protected String name;
    protected String surname;
    protected String phoneNumber;



    public Employee() {
    }

    public Employee(String name, String surname, String phoneNumber) {
        this.id = nextId++;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return
                 id +
                " " + name +
                " " + surname +
                " " + phoneNumber;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
