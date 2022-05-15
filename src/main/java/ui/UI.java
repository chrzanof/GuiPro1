package ui;

import model.*;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

public class UI implements UserInterface{
    Restaurant restaurant;

    public UI(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    @Override
    public void printOptions() {
        System.out.println(
                "1 - Menu\n" +
                "2 - dodaj do Menu\n" +
                "3 - usuń z Menu\n" +
                "4 - zmień dostępność pozycji\n" +
                "5 - nowe zamówienie\n" +
                "6 - nowe losowe zamówienie\n" +
                "7 - wszystkie oczekujące zamówienia\n" +
                "8 - wszystkie ukończone zamówienia\n" +
                "9 - dzienny utarg\n" +
                "10 - nowy pracownik\n" +
                "11 - usuń pracownika\n" +
                "12 - wszyscy pracownicy\n" +
                "13 - info o pracowniku\n" +
                "14 - wykonaj zamówienia\n" +
                        "15 - lista klientów\n" +
                "0 - zakończ program");
        System.out.println();
    }

    @Override
    public void printAllMenu() {
        this.restaurant.getMenu().getAll().stream().forEach(menuRow -> System.out.println(menuRow));
        System.out.println();
    }

    @Override
    public void addToMenu(Scanner scanner) {
        scanner.nextLine();
        System.out.println("nazwa:");
        String name = scanner.nextLine();
        System.out.println("Opis:");
        String description = scanner.nextLine();
        System.out.println("Cena:");
        double price = scanner.nextDouble();
        System.out.println("Czy dostępny?(true/false): ");
        boolean isAvailable = scanner.nextBoolean();

        MenuRow menuRow = new MenuRow(name, description, price, isAvailable);
        restaurant.getMenu().save(menuRow);
        restaurant.getMenu().writeToCSV();
    }

    @Override
    public void deleteFromMenu(Scanner scanner) {
        System.out.println("Numer do usunięcia:");
        long index = scanner.nextLong();
        MenuRow menuRow = restaurant.getMenu().get(index - 1).get();
        restaurant.getMenu().delete(menuRow);
        restaurant.getMenu().writeToCSV();
    }


    @Override
    public void changeAvailability(Scanner scanner) {
        System.out.println("Numer do zmiany:");
        long index = scanner.nextLong();
        MenuRow menuRow = restaurant.getMenu().get(index - 1).get();
        if(menuRow.isAvailable())
            menuRow.setAvailable(false);
        else
            menuRow.setAvailable(true);
        restaurant.getMenu().writeToCSV();
    }

    @Override
    public void newOrder(Scanner scanner) {
        System.out.println("1 - na miejscu   2 - na wynos");
        int option = scanner.nextInt();
        if(option == 1){
            StationaryOrder order = new StationaryOrder();
            System.out.println("Numer Klienta:");
            long clientId = scanner.nextLong();
            Client client = restaurant.getClientDao().get(clientId).get();
            long itemNumber = -1;
            while(true) {
                System.out.println("Numer dania(0 aby zakończyć):");
                itemNumber = scanner.nextLong();
                if(itemNumber == 0) {
                    break;
                }
                System.out.println("ilość: ");
                Integer quantity = scanner.nextInt();
                order.getOrderedItems().put(restaurant.getMenu().get(itemNumber - 1).get(), quantity);
            }
            order.calculatePrice();
            Date date = Date.from(Instant.now());
            order.setPlaceDate(date);
            restaurant.getKitchen().getStationaryOrderDao().save(order);
        } else if(option == 2) {
            DeliveryOrder order = new DeliveryOrder();
            System.out.println("Numer Klienta:");
            long clientId = scanner.nextLong();
            Client client = restaurant.getClientDao().get(clientId).get();
            long itemNumber = -1;
            while(true) {
                System.out.println("Numer dania(0 aby zakończyć):");
                itemNumber = scanner.nextLong();
                if(itemNumber == 0) {
                    break;
                }
                System.out.println("ilość: ");
                Integer quantity = scanner.nextInt();
                order.getOrderedItems().put(restaurant.getMenu().get(itemNumber - 1).get(), quantity);
            }
            order.calculatePrice();
            Date date = Date.from(Instant.now());
            order.setPlaceDate(date);
            restaurant.getKitchen().getDeliveryOrderDao().save(order);
        } else {
            System.out.println("nieprawidłowa opcja");
        }
    }

    @Override
    public void newRandomOrder() {

    }

    @Override
    public void printAwaitingOrders() {
        System.out.println("Na miejscu:");
        restaurant.getKitchen().getStationaryOrderDao()
                .getAll()
                .stream()
                .forEach(order -> System.out.println(order));
        System.out.println("Na wynos:");
        restaurant.getKitchen().getDeliveryOrderDao()
                .getAll()
                .stream()
                .forEach(order -> System.out.println(order));

    }

    @Override
    public void printExecutedOrders() {

    }

    @Override
    public void calculateAndPrintDailyTakings() {

    }

    @Override
    public void addNewEmployee(Scanner scanner) {

    }

    @Override
    public void deleteEmployee(Scanner scanner) {

    }

    @Override
    public void startWorking() {

    }

    @Override
    public void printAllEmployees() {

    }

    @Override
    public void printEmployee(Scanner scanner) {

    }

    @Override
    public void printAllClients(Scanner scanner) {

    }
}
