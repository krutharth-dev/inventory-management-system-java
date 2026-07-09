public class Product {
    private final int id;
    private final String name;
    private int quantity;
    private final double price;
    private final String category;

    public Product(int id, String name, int quantity) {
        this(id, name, quantity, 0.0, "Uncategorised");
    }

    public Product(int id, String name, int quantity, double price, String category) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
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

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public double getTotalValue() {
        return quantity * price;
    }

    public String toFileLine() {
        return id + "," + name + "," + quantity + "," + price + "," + category;
    }

    public String toCsvLine() {
        return id + "," + escapeCsv(name) + "," + quantity + "," + price + "," + escapeCsv(category) + "," + getTotalValue();
    }

    private String escapeCsv(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }

    public static Product fromFileLine(String line) {
        String[] parts = line.split(",", -1);

        if (parts.length != 3 && parts.length != 5) {
            return null;
        }

        try {
            int id = Integer.parseInt(parts[0].trim());
            String name = parts[1].trim();
            int quantity = Integer.parseInt(parts[2].trim());
            double price = 0.0;
            String category = "Uncategorised";

            if (parts.length == 5) {
                price = Double.parseDouble(parts[3].trim());
                category = parts[4].trim();
            }

            if (id <= 0 || name.isEmpty() || quantity < 0 || price < 0 || category.isEmpty()) {
                return null;
            }

            return new Product(id, name, quantity, price, category);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
