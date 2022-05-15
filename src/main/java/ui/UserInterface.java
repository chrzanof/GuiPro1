package ui;

import model.Restaurant;

import java.util.Scanner;

public interface UserInterface {
    void printOptions();
    void printAllMenu();
    void addToMenu(Scanner scanner);
    void deleteFromMenu(Scanner scanner);
    void changeAvailability(Scanner scanner);
    void newOrder(Scanner scanner);
    void newRandomOrder();
    void printAllOrders();
    void printExecutedOrders();
    void calculateAndPrintDailyTakings();
    void addNewEmployee(Scanner scanner);
    void deleteEmployee(Scanner scanner);
    void startWorking();
    void printAllEmployees();
    void printEmployee(Scanner scanner);
    void printAllClients(Scanner scanner);
}
