package view;

import controller.WarehouseController;
import controller.WarehouseWorker;

import java.util.Scanner;

public class ConsoleView {
    private WarehouseController controller;
    private Scanner sc = new Scanner(System.in);

    public ConsoleView(WarehouseController controller) {
        this.controller = controller;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n==== Warehouse Inventory Menu ====");
            System.out.println("1. Add Product");
            System.out.println("2. Receive Shipment");
            System.out.println("3. Fulfill Order");
            System.out.println("4. Show Inventory");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> addProduct();
                case 2 -> receiveShipment();
                case 3 -> fulfillOrder();
                case 4 -> controller.displayInventory();
             
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addProduct() {
        System.out.print("Enter Product ID: ");
        String id = sc.next();
        System.out.print("Name: ");
        String name = sc.next();
        System.out.print("Initial Quantity: ");
        int qty = sc.nextInt();
        System.out.print("Reorder Threshold: ");
        int threshold = sc.nextInt();

        controller.addNewProduct(id, name, qty, threshold);
    }

    private void receiveShipment() {
        System.out.print("Enter Product ID: ");
        String id = sc.next();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        controller.receiveShipment(id, qty);
    }

    private void fulfillOrder() {
        System.out.print("Enter Product ID: ");
        String id = sc.next();
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        controller.fulfillOrder(id, qty);
    }

   
}
