package controller;

import model.Product;
import model.Warehouse;

public class WarehouseController {
    private Warehouse warehouse;

    public WarehouseController(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void addNewProduct(String id, String name, int qty, int threshold) {
        warehouse.addProduct(new Product(id, name, qty, threshold));
    }

    public void receiveShipment(String id, int qty) {
        warehouse.receiveShipment(id, qty);
    }

    public void fulfillOrder(String id, int qty) {
        warehouse.fulfillOrder(id, qty);
    }

    public void displayInventory() {
        warehouse.getInventory().values().forEach(p ->
                System.out.println("➡️ " + p.getName() + " | Qty: " + p.getQuantity()));
    }
}
