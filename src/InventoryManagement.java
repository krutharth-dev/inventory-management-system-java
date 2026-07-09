import java.util.List;
import java.util.Scanner;

/*
 * Inventory Management System
 * A Java command-line application for managing product inventory.
 *
 * This file handles the user interface and menu flow.
 * Product data logic is handled by InventoryService.
 * File reading/writing is handled by FileStorage.
 */
public class InventoryManagement {

    private static final String FILE_NAME = "inventory.txt";
    private static final Scanner scanner = new Scanner(System.in);
    private static final InventoryService inventoryService = new InventoryService(new FileStorage(FILE_NAME));

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
        System.out.println("\n--- Add Product ---");
        int id = readInt("Product ID: ", 1, Integer.MAX_VALUE);

        if (inventoryService.getProductById(id) != null) {
            System.out.println("A product with this ID already exists. Please use a unique ID.");
            return;
        }

        String name = readProductName("Product Name: ");
        int quantity = readInt("Quantity: ", 0, Integer.MAX_VALUE);

        Product product = new Product(id, name, quantity);

        if (inventoryService.addProduct(product)) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Unable to add product.");
        }
    }

    private static void updateProduct() {
        List<Product> products = inventoryService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\n--- Update Product Quantity ---");
        int id = readInt("Enter Product ID to update: ", 1, Integer.MAX_VALUE);
        Product product = inventoryService.getProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Current product: " + product.getName() + " | Quantity: " + product.getQuantity());
        int newQuantity = readInt("New Quantity: ", 0, Integer.MAX_VALUE);

        if (inventoryService.updateQuantity(id, newQuantity)) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Unable to update product.");
        }
    }

    private static void removeProduct() {
        List<Product> products = inventoryService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\n--- Remove Product ---");
        int id = readInt("Enter Product ID to remove: ", 1, Integer.MAX_VALUE);
        Product product = inventoryService.getProductById(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.println("Removing: " + product.getName());

        if (inventoryService.removeProduct(id)) {
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Unable to remove product.");
        }
    }

    private static void displayInventory() {
        List<Product> products = inventoryService.getAllProducts();

        System.out.println("\n--- Current Inventory ---");

        if (products.isEmpty()) {
            System.out.println("No items to display.");
            return;
        }

        printProductTable(products);
    }

    private static void lowStockAlert() {
        List<Product> products = inventoryService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\n--- Low Stock Alert ---");
        int threshold = readInt("Enter low stock threshold: ", 0, Integer.MAX_VALUE);
        List<Product> lowStockProducts = inventoryService.getLowStockProducts(threshold);

        if (lowStockProducts.isEmpty()) {
            System.out.println("No products below the selected threshold.");
            return;
        }

        printProductTable(lowStockProducts);
    }

    private static void searchProduct() {
        List<Product> products = inventoryService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\n--- Search Product ---");
        System.out.println("1. Search by Product ID");
        System.out.println("2. Search by Product Name");

        int searchChoice = readInt("Enter your choice: ", 1, 2);

        if (searchChoice == 1) {
            int id = readInt("Enter Product ID: ", 1, Integer.MAX_VALUE);
            Product product = inventoryService.getProductById(id);

            if (product == null) {
                System.out.println("No matching product found.");
                return;
            }

            printTableHeader();
            printProductRow(product);
        } else {
            String keyword = readNonEmptyString("Enter Product Name or Keyword: ");
            List<Product> results = inventoryService.searchByName(keyword);

            if (results.isEmpty()) {
                System.out.println("No matching product found.");
                return;
            }

            printProductTable(results);
        }
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

    private static void printProductTable(List<Product> products) {
        printTableHeader();

        for (Product product : products) {
            printProductRow(product);
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
}
