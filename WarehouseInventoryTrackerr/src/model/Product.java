package model;

import java.io.Serializable;

public class Product implements Serializable {
	private String productId;
	private String name;
	private int quantity;
	private int reorderThreshold;

	public Product(String productId, String name, int quantity, int reorderThreshold) {
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.reorderThreshold = reorderThreshold;
	}

	public String getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getReorderThreshold() {
		return reorderThreshold;
	}

	public void increaseStock(int qty) {
		if (qty > 0)
			this.quantity += qty;
	}

	public void decreaseStock(int qty) {
		if (qty > 0 && qty <= quantity) {
			this.quantity -= qty;
		} else {
			throw new IllegalArgumentException(" Insufficient stock for " + name);
		}
	}

	@Override
	public String toString() {
		return productId + "," + name + "," + quantity + "," + reorderThreshold;
	}

	public static Product fromString(String line) {
		String[] parts = line.split(",");
		if (parts.length != 4)
			return null;
		return new Product(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
	}
}
