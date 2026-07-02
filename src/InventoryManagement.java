import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Inventory Management System
 * A Java command-line application for managing product inventory.
 * Data is stored in inventory.txt using file handling.
 */
public class InventoryManagement {

    private static final String FILE_NAME = "inventory.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = readInt("Enter your choice: ", 1, 7);

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    displayInventory();
                    break;
                case 5:
                    lowStockAlert();
                    break;
                case 6:
                    searchProduct();
                    break;
                case 7:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);
    }

    private static void displayMenu() {
        System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product Quantity");
        System.out.println("3. Remove Product");
        System.out.println("4. Display Inventory");
        System.out.println("5. Low Stock Alert");
        System.out.println("6. Search Product");
        System.out.println("7. Exit");
    }

    private static void addProduct() {
        List<Product> products = readProducts();

        System.out.println("\n--- Add Product ---");
        int id = readInt("Product ID: ", 1, Integer.MAX_VALUE);

        if (findProductById(products, id) != null) {
            System.out.println("A product with this ID already exists. Please use a unique ID.");
            return;
        }

        String name = readProductName("Product Name: ");
        int quantity = readInt("Quantity: ", 0, Integer.MAX_VALUE);

        products.add(new Product(id, name, quantity));

        if (saveProducts(products)) {
            System.out.println("Product added successfully.");
        }
    }

    private static void updateProduct() {
        List<Product> products = readProducts();

        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\n--- Update Product Quantity ---");
        int id = readInt("Enter Product ID to update: ", 1, Integer.MAX_VALUE);
        Product product = findProductById(products, id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Current product: " + product.getName() + " | Quantity: " + product.getQuantity());
        int newQuantity = readInt("New Quantity: ", 0, Integer.MAX_VALUE);
        product.setQuantity(newQuantity);

        if (saveProducts(products)) {
            System.out.println("Product updated successfully.");
        }
    }

    private static void removeProduct() {
        List<Product> products = readProducts();

        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\n--- Remove Product ---");
        int id = readInt("Enter Product ID to remove: ", 1, Integer.MAX_VALUE);
        Product product = findProductById(products, id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Removing: " + product.getName());
        products.remove(product);

        if (saveProducts(products)) {
            System.out.println("Product removed successfully.");
        }
    }

    private static void displayInventory() {
        List<Product> products = readProducts();

        System.out.println("\n--- Current Inventory ---");

        if (products.isEmpty()) {
            System.out.println("No items to display.");
            return;
        }

        printTableHeader();

        for (Product product : products) {
            printProductRow(product);
        }
    }

    private static void lowStockAlert() {
        List<Product> products = readProducts();

        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\n--- Low Stock Alert ---");
        int threshold = readInt("Enter low stock threshold: ", 0, Integer.MAX_VALUE);
        boolean found = false;

        printTableHeader();

        for (Product product : products) {
            if (product.getQuantity() < threshold) {
                printProductRow(product);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No products below the selected threshold.");
        }
    }

    private static void searchProduct() {
        List<Product> products = readProducts();

        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\n--- Search Product ---");
        System.out.println("1. Search by Product ID");
        System.out.println("2. Search by Product Name");

        int searchChoice = readInt("Enter your choice: ", 1, 2);
        boolean found = false;

        printTableHeader();

        if (searchChoice == 1) {
            int id = readInt("Enter Product ID: ", 1, Integer.MAX_VALUE);
            Product product = findProductById(products, id);

            if (product != null) {
                printProductRow(product);
                found = true;
            }
        } else {
            String keyword = readNonEmptyString("Enter Product Name or Keyword: ").toLowerCase();

            for (Product product : products) {
                if (product.getName().toLowerCase().contains(keyword)) {
                    printProductRow(product);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No matching product found.");
        }
    }

    private static List<Product> readProducts() {
        List<Product> products = new ArrayList<>();
        Path path = Paths.get(FILE_NAME);

        if (!Files.exists(path)) {
            return products;
        }

        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                Product product = Product.fromFileLine(line);

                if (product != null) {
                    products.add(product);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }

        return products;
    }

    private static boolean saveProducts(List<Product> products) {
        Path inventoryPath = Paths.get(FILE_NAME);
        Path tempPath = Paths.get("temp_inventory.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(tempPath)) {
            for (Product product : products) {
                writer.write(product.toFileLine());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing temporary inventory file: " + e.getMessage());
            return false;
        }

        try {
            Files.move(
                    tempPath,
                    inventoryPath,
                    StandardCopyOption.REPLACE_EXISTING,
                    StandardCopyOption.ATOMIC_MOVE
            );
        } catch (IOException atomicMoveError) {
            try {
                Files.move(tempPath, inventoryPath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException moveError) {
                System.out.println("Error saving inventory file: " + moveError.getMessage());
                return false;
            }
        }

        return true;
    }

    private static Product findProductById(List<Product> products, int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }

    private static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value < min || value > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }

                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("Input cannot be empty.");
        }
    }

    private static String readProductName(String prompt) {
        while (true) {
            String name = readNonEmptyString(prompt);

            if (name.contains(",")) {
                System.out.println("Product name cannot contain commas because commas are used in the inventory file.");
                continue;
            }

            return name;
        }
    }

    private static void printTableHeader() {
        System.out.printf("%-10s %-25s %-10s%n", "ID", "Name", "Quantity");
        System.out.println("------------------------------------------------");
    }

    private static void printProductRow(Product product) {
        System.out.printf(
                "%-10d %-25s %-10d%n",
                product.getId(),
                product.getName(),
                product.getQuantity()
        );
    }

    private static class Product {
        private final int id;
        private final String name;
        private int quantity;

        Product(int id, String name, int quantity) {
            this.id = id;
            this.name = name;
            this.quantity = quantity;
        }

        int getId() {
            return id;
        }

        String getName() {
            return name;
        }

        int getQuantity() {
            return quantity;
        }

        void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        String toFileLine() {
            return id + "," + name + "," + quantity;
        }

        static Product fromFileLine(String line) {
            String[] parts = line.split(",", -1);

            if (parts.length != 3) {
                return null;
            }

            try {
                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                int quantity = Integer.parseInt(parts[2].trim());

                if (id <= 0 || name.isEmpty() || quantity < 0) {
                    return null;
                }

                return new Product(id, name, quantity);
            } catch (NumberFormatException e) {
                return null;
            }
        }
    }
}