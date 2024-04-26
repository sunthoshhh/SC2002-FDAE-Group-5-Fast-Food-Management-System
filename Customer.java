package fsmjdoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a customer in the system who can browse menus, select branches, and manage their cart.
 */
public class Customer {

    private Scanner sc = new Scanner(System.in);
    private Order cart;

    /**
     * Constructs a new Customer with an empty cart.
     */
    public Customer() {
        this.cart = new Order();
    }

    /**
     * Displays the menu for the given branch.
     *
     * @param branch The branch whose menu is to be displayed.
     */
    public void browseMenu(Branch branch) {
        Branch selectedBranch = branch;
        List<MenuItem> menuItems = branch.getBranchItems();
        System.out.println("Menu for " + selectedBranch.getName() + ":");

        for (int i = 0; i < menuItems.size(); i++) {
            MenuItem menuItem = menuItems.get(i);
            System.out.println((i + 1) + ": " + menuItem.getName() + ", " + menuItem.getCategory() + ", $" + menuItem.getPrice());
        }
    }

    /**
     * Allows the customer to select a branch from a list of existing branches.
     *
     * @param existingBranches The list of existing branches.
     */
    public void selectBranch(List<Branch> existingBranches) {
        int choice;
        do {
            System.out.println("Which branch would you like to purchase from?");
            for (int i = 0; i < existingBranches.size(); i++) {
                System.out.println((i + 1) + ". " + existingBranches.get(i).getName());
            }
            choice = sc.nextInt();
            if (choice < 1 || choice > existingBranches.size()) {
                System.out.println("Invalid choice. Please select a valid branch.");
            }
        } while (choice < 1 || choice > existingBranches.size());

        this.cart.setSelectedBranch(existingBranches.get(choice - 1));
        System.out.println("Selected Branch: " + this.cart.getSelectedBranch().getName());
    }

    /**
     * Adds an item to the customer's cart.
     *
     * @param item The item to be added to the cart.
     */
    public void addToCart(MenuItem item) {
        this.cart.addItem(item);
    }

    /**
     * Removes an item from the customer's cart.
     *
     * @param item The item to be removed from the cart.
     */
    public void removeFromCart(MenuItem item) {
        this.cart.removeItem(item);
    }

    /**
     * Customizes the order with a given customization string.
     *
     * @param customization The customization to be added to the order.
     */
    public void customizeOrder(String customization) {
        this.cart.setCustomization(customization);
    }

    /**
     * Returns the order for the customer.
     *
     * @return The order for the customer.
     */
    public Order getOrder() {
        return cart;
    }
}
