public class Product {
    private final int id;
    private final String name;
    private int quantity;

    public Product(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toFileLine() {
        return id + "," + name + "," + quantity;
    }

    public static Product fromFileLine(String line) {
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
