# Inventory Management System in Java

A Java-based command-line inventory management system that allows users to manage product records using object-oriented programming, file handling, data validation, and persistent text-file storage.

This project is designed as a beginner-to-intermediate Java portfolio project that demonstrates CRUD operations, search, validation, low-stock detection, and safe inventory file updates.

---

## Project Overview

The Inventory Management System helps users maintain a simple product inventory from the terminal. Users can add new products, update stock quantities, remove products, search existing items, display all inventory records, and identify products below a custom low-stock threshold.

The application stores product data in a local `inventory.txt` file, allowing inventory records to persist after the program exits.

---

## Key Features

- Add new products with product ID, name, and quantity
- Prevent duplicate product IDs
- Update product quantities
- Remove products from inventory
- Display all inventory records in a table format
- Search products by ID
- Search products by name or keyword
- Show low-stock products using a custom threshold
- Validate numeric inputs
- Prevent empty product names
- Prevent invalid negative quantities
- Store inventory data using file handling
- Save data safely using a temporary file before replacing the main inventory file

---

## Technologies Used

- Java
- Object-Oriented Programming
- File handling
- Collections / ArrayList
- Command-line interface
- Input validation
- Persistent storage using text files

---

## Project Structure

```text
.
├── src/
│   └── InventoryManagement.java
├── inventory.txt
├── README.md
└── .gitignore
```

---

## How to Compile

From the project root directory, run:

```bash
javac -d out src/InventoryManagement.java
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
7. Exit
```

---

## Data Storage Format

Inventory data is stored in `inventory.txt` using comma-separated values.

Example:

```text
101,Laptop,15
102,Keyboard,40
103,Mouse,25
```

Each line contains:

```text
Product ID, Product Name, Quantity
```

---

## Sample Demo Flow

### Add Product

```text
Product ID: 101
Product Name: Laptop
Quantity: 15
Product added successfully.
```

### Display Inventory

```text
ID         Name                      Quantity
------------------------------------------------
101        Laptop                    15
102        Keyboard                  40
103        Mouse                     25
```

### Low Stock Alert

```text
Enter low stock threshold: 20
ID         Name                      Quantity
------------------------------------------------
101        Laptop                    15
```

### Search Product

```text
1. Search by Product ID
2. Search by Product Name
```

The program can search using either the exact product ID or a name keyword.

---

## Concepts Demonstrated

| Concept | How It Is Used |
|---|---|
| Object-Oriented Programming | Product class stores product details |
| File Handling | Inventory records are read from and written to `inventory.txt` |
| CRUD Operations | Add, update, remove, and display products |
| Input Validation | Invalid IDs, quantities, and empty names are handled |
| Search | Products can be searched by ID or name |
| Data Persistence | Inventory data remains saved after program exit |
| Safe File Update | Temporary file is used before replacing the inventory file |

---

## Why This Project Is Useful

This project demonstrates how a real-world inventory problem can be solved using core Java concepts. It shows the ability to build a complete terminal-based application with structured data, validation, persistent storage, and user-friendly menu navigation.

It is useful for demonstrating:

- Java fundamentals
- Object-oriented design
- Data handling
- File-based persistence
- Basic software application development
- User input validation

---

## Limitations

- The project currently uses text-file storage instead of a database.
- It is terminal-based and does not include a graphical user interface.
- It does not include login or role-based access control.
- It does not currently track sales history or purchase history.
- It does not generate CSV or PDF reports.

---

## Future Enhancements

- Add SQLite or MySQL database support.
- Add Java Swing or JavaFX graphical user interface.
- Add Spring Boot backend version.
- Add React frontend for a full-stack version.
- Add login system with admin and staff roles.
- Add product categories and supplier details.
- Add sales and purchase history.
- Add CSV/PDF export.
- Add automated unit tests.
- Add dashboard analytics such as total stock value and low-stock count.

---

## Resume Summary

Built a Java-based inventory management system implementing CRUD operations, product search, low-stock detection, input validation, object-oriented design, and persistent file-based storage through a command-line interface.
