package ui;

import model.*;

import java.sql.Time;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class UI implements UserInterface{
    Restaurant restaurant;

    public UI(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    @Override
    public void printOptions() {
        System.out.println();
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
            order.setClient(client);
            System.out.println("Numer stolika:");
            int tableNumber = scanner.nextInt();
            order.setTableNumber(tableNumber);
            while(true) {
                System.out.println("Numer dania(0 aby zakończyć):");
                long itemNumber = scanner.nextLong();
                if(itemNumber == 0) {
                    break;
                }
                MenuRow item = restaurant.getMenu().get(itemNumber - 1).get();
                if(item.isAvailable()){
                    System.out.println("ilość: ");
                    Integer quantity = scanner.nextInt();
                    order.getOrderedItems().put(item, quantity);
                } else {
                    System.out.println("Produkt nie dostępny");
                }
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
            order.setClient(client);
            scanner.nextLine();
            System.out.println("Adres:");
            String address = scanner.nextLine();
            order.setAddress(address);
            while(true) {
                System.out.println("Numer dania(0 aby zakończyć):");
                long itemNumber = scanner.nextLong();
                if(itemNumber == 0) {
                    break;
                }
                MenuRow item = restaurant.getMenu().get(itemNumber - 1).get();
                if(item.isAvailable()){
                    System.out.println("ilość: ");
                    Integer quantity = scanner.nextInt();
                    order.getOrderedItems().put(item, quantity);
                } else {
                    System.out.println("Produkt nie dostępny");
                }
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

        int clientNumber =(int) (Math.random() * restaurant.getClientDao().getAll().size());
        Client client = restaurant.getClientDao().getByIndex(clientNumber).get();
        int itemCount = (int) (Math.random() * 9  + 1);
        int type = (int)(Math.random() * 2);
        Map<MenuRow, Integer> casket = new HashMap<>();
        for (int i = 0; i < itemCount; i++) {
            int itemIndex = (int) (Math.random() * restaurant.getMenu().getAll().size());
            Integer itemQuantity = (int)(Math.random() * 5 + 1);
            MenuRow row = restaurant.getMenu().get(itemIndex).get();
            if(row.isAvailable())
                casket.put(row,itemQuantity);
            else {
                --i;
            }
        }
        if(type > 0){
            DeliveryOrder order = new DeliveryOrder();
            order.setOrderedItems(casket);
            order.setClient(client);
            order.setPlaceDate(Date.from(Instant.now()));
            order.calculatePrice();
            restaurant.getKitchen().getDeliveryOrderDao().save(order);
        } else {
            StationaryOrder order = new StationaryOrder();
            order.setOrderedItems(casket);
            order.setClient(client);
            order.calculatePrice();
            order.setTableNumber((int)(Math.random() * 9 + 1));
            restaurant.getKitchen().getStationaryOrderDao().save(order);
        }
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
        System.out.println("zakończone zamówienia:");
        restaurant.getExecutedOrders().stream().forEach(order -> System.out.println(order));
    }

    @Override
    public void calculateAndPrintDailyTakings() {
        double sum = 0;
        for (Order order :
                restaurant.getExecutedOrders()) {
            sum += order.getTotalPrice();
        }
        restaurant.setDailyTakings(sum);
        System.out.println("Utarg: "+ restaurant.getDailyTakings()+"zł");
    }

    @Override
    public void addNewEmployee(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Imie:");
        String name = scanner.nextLine();
        System.out.println("Nazwisko:");
        String surname = scanner.nextLine();
        System.out.println("Numer telefonu:");
        String phoneNumber = scanner.nextLine();
        System.out.println("1 - Kucharz   2 - Kelner   3 - Dostawca");
        int option = scanner.nextInt();

        switch (option) {
            case(1):
                restaurant.getKitchen().getCookDao().save(new Cook(name, surname, phoneNumber));
                break;
            case(2):
                restaurant.getWaiterDao().save(new Waiter(name, surname, phoneNumber));
                break;
            case(3):
                restaurant.getDeliveryManDao().save(new DeliveryMan(name,surname,phoneNumber));
                break;
            default:
                System.out.println("Błąd. Zła opcja");
                break;
        }
    }

    @Override
    public void deleteEmployee(Scanner scanner) {
        System.out.println("Podaj id pracownika");
        long id = scanner.nextLong();
        List<Cook> cooks = restaurant.getKitchen().getCookDao().getAll();
        List<Waiter> waiters = restaurant.getWaiterDao().getAll();
        List<DeliveryMan> deliveryMEN = restaurant.getDeliveryManDao().getAll();
        cooks = cooks.stream()
                .filter(x->x.getId() == id)
                .collect(Collectors.toList());
        waiters = waiters.stream()
                .filter(x->x.getId() == id)
                .collect(Collectors.toList());
        deliveryMEN = deliveryMEN.stream()
                .filter(x-> x.getId() == id)
                .collect(Collectors.toList());
        for(Cook cook : cooks){
            restaurant.getKitchen().getCookDao().delete(cook);
        }
        for(Waiter waiter : waiters) {
            restaurant.getWaiterDao().delete(waiter);
        }
        for(DeliveryMan deliveryMan: deliveryMEN) {
            restaurant.getDeliveryManDao().delete(deliveryMan);
        }
    }

    @Override
    public void startWorking() {
        restaurant.startWork();
    }

    @Override
    public void printAllEmployees() {
        System.out.println("Kucharze:");
        restaurant.getKitchen().getCookDao().getAll()
                .stream()
                .forEach(cook-> System.out.println(cook));
        System.out.println("Kelnerzy:");
        restaurant.getWaiterDao().getAll()
                .stream()
                .forEach(waiter -> System.out.println(waiter));
        System.out.println("Dostawcy:");
        restaurant.getDeliveryManDao().getAll()
                .stream()
                .forEach(deliveryMan -> System.out.println(deliveryMan));


    }

    @Override
    public void printEmployee(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Imie:");
        List<Cook> cooks = restaurant.getKitchen().getCookDao().getAll();
        List<Waiter> waiters = restaurant.getWaiterDao().getAll();
        List<DeliveryMan> deliveryMEN = restaurant.getDeliveryManDao().getAll();
        String name = scanner.nextLine();
        cooks = cooks.stream()
                .filter(cook -> cook.getName().equals(name))
                .collect(Collectors.toList());
        waiters = waiters.stream()
                .filter(waiter -> waiter.getName().equals(name))
                .collect(Collectors.toList());
        deliveryMEN = deliveryMEN.stream()
                .filter(deliveryMan -> deliveryMan.getName().equals(name))
                .collect(Collectors.toList());
        if(cooks.size() + waiters.size() + deliveryMEN.size() > 1){
            System.out.println("Nazwisko:");
            String surname = scanner.nextLine();
            cooks = cooks.stream()
                    .filter(cook -> cook.getSurname().equals(surname))
                    .collect(Collectors.toList());
            waiters = waiters.stream()
                    .filter(waiter -> waiter.getSurname().equals(surname))
                    .collect(Collectors.toList());
            deliveryMEN = deliveryMEN.stream()
                    .filter(deliveryMan -> deliveryMan.getSurname().equals(surname))
                    .collect(Collectors.toList());
        }

        cooks.stream().forEach(x -> System.out.println(x));
        waiters.stream().forEach(x -> System.out.println(x));
        deliveryMEN.stream().forEach(x -> System.out.println(x));
    }

    @Override
    public void printAllClients(Scanner scanner) {
        System.out.println("Klienci:");
        restaurant.getClientDao().getAll()
                .stream()
                .forEach(client -> System.out.println(client));
    }
}
