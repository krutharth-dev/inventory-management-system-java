# Inventory Management System in Java

A Java-based command-line inventory management system that allows users to manage product records using object-oriented programming, file handling, data validation, persistent text-file storage, inventory analytics, sorting, and CSV export.

This project is designed as a beginner-to-intermediate Java portfolio project that demonstrates CRUD operations, search, validation, low-stock detection, layered code structure, safe inventory file updates, report generation, and basic inventory valuation.

---

## Project Overview

The Inventory Management System helps users maintain a simple product inventory from the terminal. Users can add new products, update stock quantities, remove products, search existing items, display all inventory records, identify products below a custom low-stock threshold, sort inventory, view inventory analytics, and export records to CSV.

The application stores product data in a local `inventory.txt` file, allowing inventory records to persist after the program exits.

---

## Key Features

- Add new products with product ID, name, quantity, price, and category
- Prevent duplicate product IDs
- Update product quantities
- Remove products from inventory
- Display all inventory records in a table format
- Search products by ID
- Search products by name or keyword
- Show low-stock products using a custom threshold
- Sort inventory by ID, name, quantity, price, category, or total value
- Calculate total product types
- Calculate total stock units
- Calculate total inventory value
- Export inventory data to CSV
- Validate integer and decimal inputs
- Prevent empty product names and categories
- Prevent invalid negative quantities or prices
- Store inventory data using file handling
- Save data safely using a temporary file before replacing the main inventory file
- Uses separate classes for UI, product model, business logic, and file storage

---

## Technologies Used

- Java
- Object-Oriented Programming
- File handling
- Collections / ArrayList
- Comparators and sorting
- Command-line interface
- Input validation
- Persistent storage using text files
- CSV export
- Layered application structure

---

## Project Structure

```text
.
├── src/
│   ├── InventoryManagement.java
│   ├── InventoryService.java
│   ├── FileStorage.java
│   └── Product.java
├── inventory.txt
├── README.md
├── UPGRADE_PLAN.md
└── .gitignore
```

---

## Class Responsibilities

| Class | Responsibility |
|---|---|
| `InventoryManagement` | Handles menu display, user input, command-line interface flow, and result printing |
| `InventoryService` | Handles inventory business logic, sorting, analytics, search, low-stock filtering, and CSV export |
| `FileStorage` | Handles reading products from `inventory.txt` and saving products safely |
| `Product` | Represents one inventory item with ID, name, quantity, price, category, and total value |

---

## How to Compile

From the project root directory, run:

```bash
javac -d out src/*.java
```

---

## How to Run

```bash
java -cp out InventoryManagement
```

---

## Menu Options

```text
===== INVENTORY MANAGEMENT SYSTEM =====
1. Add Product
2. Update Product Quantity
3. Remove Product
4. Display Inventory
5. Low Stock Alert
6. Search Product
7. Inventory Summary
8. Sort Inventory
9. Export Inventory to CSV
10. Exit
```

---

## Data Storage Format

Inventory data is stored in `inventory.txt` using comma-separated values.

Example:

```text
101,Laptop,15,65000.00,Electronics
102,Keyboard,40,1200.00,Accessories
103,Mouse,25,800.00,Accessories
```

Each line contains:

```text
Product ID, Product Name, Quantity, Unit Price, Category
```

The parser also supports the older 3-column format:

```text
Product ID, Product Name, Quantity
```

Old records are loaded with price `0.0` and category `Uncategorised`.

---

## Sample Demo Flow

### Add Product

```text
Product ID: 101
Product Name: Laptop
Quantity: 15
Unit Price: 65000
Category: Electronics
Product added successfully.
```

### Display Inventory

```text
ID       Name                   Quantity   Price      Category           Value
--------------------------------------------------------------------------------
101      Laptop                 15         65000.00   Electronics        975000.00
102      Keyboard               40         1200.00    Accessories        48000.00
103      Mouse                  25         800.00     Accessories        20000.00
```

### Inventory Summary

```text
Total product types: 3
Total stock units: 80
Total inventory value: 1043000.00
```

### CSV Export

```text
Enter CSV file name, e.g. inventory_export.csv: inventory_export.csv
Inventory exported successfully to inventory_export.csv
```

---

## Concepts Demonstrated

| Concept | How It Is Used |
|---|---|
| Object-Oriented Programming | Product, service, storage, and UI responsibilities are separated |
| File Handling | Inventory records are read from and written to `inventory.txt` |
| CRUD Operations | Add, update, remove, and display products |
| Input Validation | Invalid IDs, quantities, prices, and empty fields are handled |
| Search | Products can be searched by ID or name |
| Sorting | Products can be sorted by ID, name, quantity, price, category, or value |
| Data Persistence | Inventory data remains saved after program exit |
| Inventory Analytics | Total stock units and total inventory value are calculated |
| CSV Export | Product records can be exported into a CSV report |
| Safe File Update | Temporary file is used before replacing the inventory file |
| Layered Design | UI, business logic, storage, and model code are separated |

---

## Why This Project Is Useful

This project demonstrates how a real-world inventory problem can be solved using core Java concepts. It shows the ability to build a complete terminal-based application with structured data, validation, persistent storage, modular class design, analytics, CSV export, and user-friendly menu navigation.

It is useful for demonstrating:

- Java fundamentals
- Object-oriented design
- Data handling
- File-based persistence
- Basic software application development
- User input validation
- Clean code organisation
- Inventory analytics and report export

---

## Limitations

- The project currently uses text-file storage instead of a database.
- It is terminal-based and does not include a graphical user interface.
- It does not include login or role-based access control.
- It does not currently track sales history or purchase history.
- CSV import is not yet implemented.

---

## Future Enhancements

- Add SQLite or MySQL database support.
- Add supplier field and reorder-level field.
- Add Java Swing or JavaFX graphical user interface.
- Add Spring Boot backend version.
- Add React frontend for a full-stack version.
- Add login system with admin and staff roles.
- Add sales and purchase history.
- Add CSV import.
- Add automated unit tests.
- Add dashboard analytics such as category-wise value and low-stock count.

---

## Resume Summary

Built a Java-based inventory management system implementing CRUD operations, product search, low-stock detection, input validation, object-oriented class separation, price/category tracking, inventory valuation, sorting, CSV export, and persistent file-based storage through a command-line interface.
