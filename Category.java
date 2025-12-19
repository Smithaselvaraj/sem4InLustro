import java.util.Scanner;

public class Category {

    private static String[] productNames = new String[50];
    private static String[] productCategories = new String[50];
    private static int[] productQuantities = new int[50];
    private static double[] productPrices = new double[50];
    private static String[] productStatuses = new String[50];

    private static int productCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        printWelcome();
        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    viewProducts();
                    break;
                case "3":
                    searchProduct();
                    break;
                case "4":
                    updateProductStatus();
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting Inventory Management System...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.\n");
            }
        }
        scanner.close();
    }

    private static void printWelcome() {
        System.out.println("Welcome to Inventory Management System\n");
    }

    private static void displayMenu() {
        System.out.println("INVENTORY MANAGEMENT MENU");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product Status");
        System.out.println("5. Exit");
        System.out.println("=========================");
    }

    /* ---------------- ADD PRODUCT ---------------- */

    private static void addProduct() {
        if (productCount >= 50) {
            System.out.println("Inventory is full!\n");
            return;
        }

        System.out.print("Enter product name: ");
        productNames[productCount] = scanner.nextLine();

        System.out.print("Enter category: ");
        productCategories[productCount] = scanner.nextLine();

        System.out.print("Enter quantity: ");
        productQuantities[productCount] = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter price: ");
        productPrices[productCount] = Double.parseDouble(scanner.nextLine());

        productStatuses[productCount] = "Available";
        productCount++;

        System.out.println("Product added successfully!\n");
    }

    // Method Overloading – Version 1
    private static String addProduct(String name, int quantity) {
        if (productCount >= 50) {
            return "Error: Inventory full";
        }
        productNames[productCount] = name;
        productCategories[productCount] = "General";
        productQuantities[productCount] = quantity;
        productPrices[productCount] = 0.0;
        productStatuses[productCount] = "Available";
        productCount++;
        return "Product added successfully";
    }

    // Method Overloading – Version 2
    private static String addProduct(String name, String category, int quantity, double price) {
        if (productCount >= 50) {
            return "Error: Inventory full";
        }
        productNames[productCount] = name;
        productCategories[productCount] = category;
        productQuantities[productCount] = quantity;
        productPrices[productCount] = price;
        productStatuses[productCount] = "Available";
        productCount++;
        return "Product added successfully with full details";
    }

    /* ---------------- VIEW PRODUCTS ---------------- */

    private static void viewProducts() {
        System.out.println("\n--- Product List ---");

        if (productCount == 0) {
            System.out.println("No products available.\n");
            return;
        }

        for (int i = 0; i < productCount; i++) {
            System.out.println(
                (i + 1) + ". " + productNames[i] +
                " | Category: " + productCategories[i] +
                " | Qty: " + productQuantities[i] +
                " | Price: ₹" + productPrices[i] +
                " | Status: " + productStatuses[i]
            );
        }
        System.out.println();
    }

    /* ---------------- SEARCH PRODUCT ---------------- */

    private static void searchProduct() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();

        int found = 0;

        for (int i = 0; i < productCount; i++) {
            if (productNames[i].toLowerCase().contains(keyword) ||
                productCategories[i].toLowerCase().contains(keyword)) {

                System.out.println(
                    (i + 1) + ". " + productNames[i] +
                    " | Qty: " + productQuantities[i] +
                    " | Price: ₹" + productPrices[i] +
                    " | Status: " + productStatuses[i]
                );
                found++;
            }
        }

        if (found == 0) {
            System.out.println("No matching products found.\n");
        } else {
            System.out.println("Found " + found + " product(s).\n");
        }
    }

    /* ---------------- UPDATE PRODUCT STATUS ---------------- */

    private static void updateProductStatus() {
        if (productCount == 0) {
            System.out.println("No products to update.\n");
            return;
        }

        viewProducts();
        System.out.print("Enter product number to update: ");
        int productNum = Integer.parseInt(scanner.nextLine());

        if (productNum < 1 || productNum > productCount) {
            System.out.println("Invalid product number!\n");
            return;
        }

        System.out.println("Select new status:");
        System.out.println("1. Available");
        System.out.println("2. Low Stock");
        System.out.println("3. Out of Stock");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                productStatuses[productNum - 1] = "Available";
                break;
            case "2":
                productStatuses[productNum - 1] = "Low Stock";
                break;
            case "3":
                productStatuses[productNum - 1] = "Out of Stock";
                break;
            default:
                System.out.println("Invalid status choice!\n");
                return;
        }

        System.out.println("Product status updated successfully!\n");
    }
}
