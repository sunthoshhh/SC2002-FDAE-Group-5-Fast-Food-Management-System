package fsmjdoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fsmjdoc.Order.OrderStatus;

/**
 * The Branch class represents a branch of a restaurant or retail location.
 * It manages a list of menu items and orders associated with the branch.
 */
public class Branch {

    private String name;
    private List<MenuItem> branchItems;
    private List<Order> branchOrders;

    /**
     * Constructs a new Branch with the specified name.
     *
     * @param branchName The name of the branch.
     */
    public Branch(String branchName) {
        this.name = branchName;
        this.branchItems = new ArrayList<>(); // Initialize branchItems list
        this.branchOrders = new ArrayList<>(); // Initialize branchOrders list
    }

    /**
     * Returns the name of the branch.
     *
     * @return The name of the branch.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the branch.
     *
     * @param name The new name for the branch.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the list of menu items for the branch.
     *
     * @return A list of MenuItem objects representing the menu items for the branch.
     */
    public List<MenuItem> getBranchItems() {
        return branchItems;
    }

    /**
     * Sets the list of menu items for the branch.
     *
     * @param branchItems The list of menu items to set for the branch.
     */
    public void setBranchItems(List<MenuItem> branchItems) {
        this.branchItems = branchItems;
    }

    /**
     * Adds an order to the branch's list of orders.
     *
     * @param order The Order object to be added.
     */
    public void addOrderToBranch(Order order) {
        branchOrders.add(order);
    }

    /**
     * Retrieves the status of an order with the specified ID.
     *
     * @param orderId The ID of the order.
     * @return The status of the order, or OrderStatus.NOTEXISTING if the order is not found.
     */
    public OrderStatus getOrderStatus(int orderId) {
        for (Order order : branchOrders) {
            if (order.getOrderId() == orderId) {
                return order.getStatus();
            }
        }
        return OrderStatus.NOTEXISTING;
    }

    /**
     * Returns the list of orders associated with the branch.
     *
     * @return A list of Order objects representing the orders associated with the branch.
     */
    public List<Order> getListOfOrders() {
        return this.branchOrders;
    }

    /**
     * Returns the list of menu items available at the branch.
     *
     * @return A list of MenuItem objects representing the menu items available at the branch.
     */
    public List<MenuItem> getMenuItems() {
        return branchItems;
    }

    /**
     * Adds a new menu item to the branch's list of menu items.
     *
     * @param menuItem The MenuItem object to be added.
     */
    public void addMenuItem(MenuItem menuItem) {
        branchItems.add(menuItem);
    }

    /**
     * Removes a menu item from the branch's list of menu items.
     */
    public void removeMenuItem() {
        System.out.println("Existing Menu Items:");
        for (int i = 0; i < branchItems.size(); i++) {
            MenuItem item = branchItems.get(i);
            System.out.println((i + 1) + ". " + item.getName()); // Assuming MenuItem has a getName() method
        }

        System.out.println("Enter the number of the item you want to remove:");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= branchItems.size()) {
            // Adjusting the index for user input (index starts from 1)
            MenuItem menuItemToRemove = branchItems.get(choice - 1);
            branchItems.remove(menuItemToRemove);
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Invalid choice. No item removed.");
        }
    }

    /**
     * Displays all orders associated with the branch.
     */
    public void viewAllOrders() {
        if (branchOrders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("All orders for " + this.name + " branch:");
            for (Order order : branchOrders) {
                order.getOrderSummary(); // Use the summary method of each order, to be implemented
                System.out.println();
            }
        }
    }

    /**
     * Retrieves an order with the specified ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The Order object with the specified ID, or null if not found.
     */
    public Order getOrder(int orderId) {
        for (Order order : branchOrders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null; // Order not found
    }
}
