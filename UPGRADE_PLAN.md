# Upgrade Plan

This file lists practical improvements that can make the Inventory Management System stronger for GitHub, resume, and academic presentation.

---

## Completed Recent Upgrades

- Refactored the original single-file program into multiple Java classes.
- Added `Product.java` as the model class.
- Added `InventoryService.java` for business logic.
- Added `FileStorage.java` for file reading and writing.
- Kept `InventoryManagement.java` as the command-line interface entry point.
- Added product price and category fields.
- Added total value calculation per product.
- Added inventory summary analytics.
- Added sorting by ID, name, quantity, price, category, and total value.
- Added CSV export.
- Updated sample inventory data to include price and category.
- Updated README with new features and examples.

---

## Priority 1: Documentation and Demo Improvements

- Add terminal screenshots.
- Add sample input/output examples.
- Add a demo video or GIF.
- Add a features table to show completed and planned features.
- Add screenshots of CSV export output.

---

## Priority 2: Code Quality Improvements

- Add automated unit tests.
- Add JavaDoc-style comments for important classes and methods.
- Add stricter validation for unusual product names.
- Add cleaner error handling for file access problems.
- Add a constants/config class for file names and formatting values.

Current structure:

```text
.
├── src/
│   ├── InventoryManagement.java
│   ├── InventoryService.java
│   ├── FileStorage.java
│   └── Product.java
├── tests/
├── inventory.txt
├── README.md
└── UPGRADE_PLAN.md
```

---

## Priority 3: Functional Enhancements

- Add supplier name field.
- Add reorder-level field.
- Add category-wise inventory value summary.
- Add export low-stock report to CSV.
- Add import inventory from CSV.
- Add stock-in and stock-out transaction history.
- Add date/time tracking for updates.

---

## Priority 4: Database Upgrade

Current version stores data in `inventory.txt`. A stronger version can use a database.

Suggested path:

1. SQLite for local database storage.
2. MySQL or PostgreSQL for a more advanced version.
3. JDBC for Java database connectivity.

Possible database tables:

```text
products
- id
- name
- quantity
- price
- category
- supplier
- reorder_level
- created_at
- updated_at

transactions
- transaction_id
- product_id
- transaction_type
- quantity_changed
- timestamp
```

---

## Priority 5: UI Upgrade

Possible UI upgrade paths:

### Option 1: Java GUI

- Java Swing
- JavaFX

### Option 2: Full-Stack Version

- Spring Boot backend
- MySQL/PostgreSQL database
- React frontend
- REST API endpoints

Suggested full-stack modules:

- Product dashboard
- Add/update product form
- Search and filter table
- Low-stock alert page
- CSV/PDF export report button
- Category-wise analytics
- Admin login

---

## Priority 6: Testing

- Add unit tests for product validation.
- Add tests for add, update, delete, and search operations.
- Add tests for duplicate product IDs.
- Add tests for invalid quantities and prices.
- Add tests for file read/write behaviour.
- Add tests for CSV export.

---

## Best Next Step

The best immediate upgrade is:

1. Add unit tests.
2. Add supplier and reorder-level fields.
3. Add SQLite database support.
4. Add CSV import.
5. Add screenshots and sample outputs to the README.
