import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private final String fileName;

    public FileStorage(String fileName) {
        this.fileName = fileName;
    }

    public List<Product> readProducts() {
        List<Product> products = new ArrayList<>();
        Path path = Paths.get(fileName);

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

    public boolean saveProducts(List<Product> products) {
        Path inventoryPath = Paths.get(fileName);
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
}
