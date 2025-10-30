package model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileStorage {
	private final String filePath;

	public FileStorage(String filePath) {
		this.filePath = filePath;
	}

	public void saveInventory(Map<String, Product> inventory) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
			for (Product p : inventory.values()) {
				writer.println(p.toString());
			}
			System.out.println("💾 Inventory saved to file!");
		} catch (IOException e) {
			System.out.println("❌ Error saving inventory: " + e.getMessage());
		}
	}

	public Map<String, Product> loadInventory() {
		Map<String, Product> inventory = new HashMap<>();
		File file = new File(filePath);
		if (!file.exists())
			return inventory;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				Product p = Product.fromString(line);
				if (p != null)
					inventory.put(p.getProductId(), p);
			}
			System.out.println("📂 Inventory loaded from file!");
		} catch (IOException e) {
			System.out.println("❌ Error loading inventory: " + e.getMessage());
		}
		return inventory;
	}
}
