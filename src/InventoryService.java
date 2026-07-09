import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InventoryService {
    private final FileStorage storage;

    public InventoryService(FileStorage storage) {
        this.storage = storage;
    }

    public boolean addProduct(Product newProduct) {
        List<Product> products = storage.readProducts();

        if (findProductById(products, newProduct.getId()) != null) {
            return false;
        }

        products.add(newProduct);
        return storage.saveProducts(products);
    }

    public boolean updateQuantity(int productId, int newQuantity) {
        List<Product> products = storage.readProducts();
        Product product = findProductById(products, productId);

        if (product == null) {
            return false;
        }

        product.setQuantity(newQuantity);
        return storage.saveProducts(products);
    }

    public boolean removeProduct(int productId) {
        List<Product> products = storage.readProducts();
        Product product = findProductById(products, productId);

        if (product == null) {
            return false;
        }

        products.remove(product);
        return storage.saveProducts(products);
    }

    public List<Product> getAllProducts() {
        return storage.readProducts();
    }

    public Product getProductById(int productId) {
        return findProductById(storage.readProducts(), productId);
    }

    public List<Product> searchByName(String keyword) {
        List<Product> products = storage.readProducts();
        List<Product> results = new ArrayList<>();
        String normalisedKeyword = keyword.toLowerCase();

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(normalisedKeyword)) {
                results.add(product);
            }
        }

        return results;
    }

    public List<Product> getLowStockProducts(int threshold) {
        List<Product> products = storage.readProducts();
        List<Product> lowStockProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getQuantity() < threshold) {
                lowStockProducts.add(product);
            }
        }

        return lowStockProducts;
    }

    public List<Product> sortProducts(String sortOption) {
        List<Product> products = storage.readProducts();

        switch (sortOption.toLowerCase()) {
            case "id":
                products.sort(Comparator.comparingInt(Product::getId));
                break;
            case "name":
                products.sort(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER));
                break;
            case "quantity":
                products.sort(Comparator.comparingInt(Product::getQuantity));
                break;
            case "price":
                products.sort(Comparator.comparingDouble(Product::getPrice));
                break;
            case "category":
                products.sort(Comparator.comparing(Product::getCategory, String.CASE_INSENSITIVE_ORDER));
                break;
            case "value":
                products.sort(Comparator.comparingDouble(Product::getTotalValue).reversed());
                break;
            default:
                products.sort(Comparator.comparingInt(Product::getId));
        }

        return products;
    }

    public int getTotalProductTypes() {
        return storage.readProducts().size();
    }

    public int getTotalStockUnits() {
        int totalUnits = 0;

        for (Product product : storage.readProducts()) {
            totalUnits += product.getQuantity();
        }

        return totalUnits;
    }

    public double getTotalInventoryValue() {
        double totalValue = 0.0;

        for (Product product : storage.readProducts()) {
            totalValue += product.getTotalValue();
        }

        return totalValue;
    }

    public boolean exportToCsv(String fileName) {
        List<Product> products = storage.readProducts();
        Path outputPath = Paths.get(fileName);

        try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
            writer.write("ID,Name,Quantity,Price,Category,Total Value");
            writer.newLine();

            for (Product product : products) {
                writer.write(product.toCsvLine());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error exporting CSV file: " + e.getMessage());
            return false;
        }

        return true;
    }

    private Product findProductById(List<Product> products, int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }
}
