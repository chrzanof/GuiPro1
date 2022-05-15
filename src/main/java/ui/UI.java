package ui;

import model.MenuRow;
import model.Restaurant;

import java.util.Scanner;

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

    }

    @Override
    public void newRandomOrder() {

    }

    @Override
    public void printAllOrders() {

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
