import java.util.ArrayList;
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

    private Product findProductById(List<Product> products, int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }
}
