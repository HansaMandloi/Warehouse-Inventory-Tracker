package model;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import observer.StockObserver;

public class Warehouse {
    private Map<String, Product> inventory;
    private List<StockObserver> observers = new ArrayList<>();
    private final FileStorage storage;
    private final ReentrantLock lock = new ReentrantLock();

    public Warehouse(FileStorage storage) {
        this.storage = storage;
        this.inventory = storage.loadInventory();
    }

    public void addObserver(StockObserver observer) {
        observers.add(observer);
    }

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("✅ Added product: " + product.getName());
        storage.saveInventory(inventory);
    }

    public void receiveShipment(String productId, int qty) {
        lock.lock();
        try {
            Product p = inventory.get(productId);
            if (p == null) {
                System.out.println("❌ Product not found: " + productId);
                return;
            }
            p.increaseStock(qty);
            System.out.println("📦 Received shipment: +" + qty + " units of " + p.getName());
            storage.saveInventory(inventory);
        } finally {
            lock.unlock();
        }
    }

    public void fulfillOrder(String productId, int qty) {
        lock.lock();
        try {
            Product p = inventory.get(productId);
            if (p == null) {
                System.out.println("❌ Product not found: " + productId);
                return;
            }
            try {
                p.decreaseStock(qty);
                System.out.println("🛒 Fulfilled order: -" + qty + " units of " + p.getName());
                if (p.getQuantity() < p.getReorderThreshold()) notifyLowStock(p);
                storage.saveInventory(inventory);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } finally {
            lock.unlock();
        }
    }

    private void notifyLowStock(Product product) {
        for (StockObserver observer : observers) observer.onLowStock(product);
    }

    public Map<String, Product> getInventory() {
        return inventory;
    }
}
