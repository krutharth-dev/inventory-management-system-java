# Inventory Management System in Java

A Java-based command-line inventory management system that allows users to add, update, remove, search, and display inventory records using file handling.

## Features

- Add new products
- Prevent duplicate product IDs
- Update product quantities
- Remove products from inventory
- Display all inventory records in table format
- Search products by ID or name
- Show low-stock products using a custom threshold
- Store inventory data in a text file

## Technologies Used

- Java
- File handling
- Object-oriented programming
- Command-line interface
- Basic data validation

## Project Structure

.
├── src/
│   └── InventoryManagement.java
├── inventory.txt
├── README.md
└── .gitignore

## How to Compile

```bash
javac -d out src/InventoryManagement.java