package model;

public class Client {
    private static long nextId = 1;
    private long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    public Client() {
        this.id = nextId++;
    }

    public Client(String name, String surname, String phoneNumber, String email) {
        this.id = nextId++;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
