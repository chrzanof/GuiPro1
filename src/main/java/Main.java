import dao.*;
import model.*;
import ui.UI;
import ui.UserInterface;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientDao clientDao = new ClientDao();
        clientDao.save(new Client("Filip","Chrzanowski","678-122-123","email@email.com"));
        clientDao.save(new Client("Jan","Kowalski","679-555-143","email1@email.com"));
        clientDao.save(new Client("Jakub","Kaczmarek","879-555-243","email2@email.com"));
        clientDao.save(new Client("Michał","Kowalski","669-515-113","email3@email.com"));
        clientDao.save(new Client("Sofia","Urbaniak","979-156-243","email4@email.com"));
        CookDao cookDao = new CookDao();
        cookDao.save(new Cook("imie1","Nazwisko1","111-111-111"));
        cookDao.save(new Cook("imie2","Nazwisko2","222-222-222"));
        WaiterDao waiterDao = new WaiterDao();
        waiterDao.save(new Waiter("Kelner","Kelnerski","333-333-333"));
        DeliveryManDao deliveryManDao = new DeliveryManDao();
        deliveryManDao.save(new DeliveryMan("Dostawca","Dostawski","444-444-444"));
        Kitchen kitchen = Kitchen.getInstance();
        Restaurant restaurant = Restaurant.getInstance();
        kitchen.setCookDao(cookDao);
        kitchen.setDeliveryOrderDao(new DeliveryOrderDao());
        kitchen.setStationaryOrderDao(new StationaryOrderDao());
        restaurant.setClientDao(clientDao);
        restaurant.setDeliveryManDao(deliveryManDao);
        restaurant.setWaiterDao(waiterDao);
        restaurant.setKitchen(kitchen);
        Menu menu = new Menu();
        menu.setFile("menu.csv");
        menu.readFromCSV();
        restaurant.setMenu(menu);
        UI ui = new UI(restaurant);


        Scanner scanner = new Scanner(System.in);
        int option = -1;
        do {
            ui.printOptions();
            option = scanner.nextInt();

            switch (option) {
                case(1):
                    ui.printAllMenu();
                    break;
                case(2):
                    ui.addToMenu(scanner);
                    break;
                case(3):
                    ui.deleteFromMenu(scanner);
                    break;
                case(4):
                    ui.changeAvailability(scanner);
                    break;
                case(5):
                    ui.newOrder(scanner);
                    break;
                case(6):
                    ui.newRandomOrder();
                    break;
                case(7):
                    ui.printAwaitingOrders();
                    break;
                case(8):
                    ui.printExecutedOrders();
                    break;
                case(9):
                    ui.calculateAndPrintDailyTakings();
                    break;
                case(10):
                    ui.addNewEmployee(scanner);
                    break;
                case(11):
                    ui.deleteEmployee(scanner);
                    break;
                case(12):
                    ui.printAllEmployees();
                    break;
                case(13):
                    ui.printEmployee(scanner);
                    break;
                case(14):
                    ui.startWorking();
                    break;
                case(15):
                    ui.printAllClients(scanner);
                    break;

                default:
                    break;
            }

        } while(option != 0);

        scanner.close();






    }
}
