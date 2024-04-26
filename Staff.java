package fsmjdoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Staff class represents an employee with specific attributes and methods
 * related to the employee's duties within a branch.
 */
public class Staff {
    // Attributes
    private String Name;
    private String userName;
    private String password;
    private char gender;
    private int age;
    private char designation; // can use enum to store different designations
    private String branchName;
    private Branch staffBranch; // Create a branch list class and store the specific instance of the branch the staff belong to

    /**
     * Constructs a new Staff instance with the specified attributes.
     *
     * @param Name The name of the staff member.
     * @param userName The username of the staff member.
     * @param gender The gender of the staff member.
     * @param designation The designation of the staff member.
     * @param age The age of the staff member.
     * @param branchName The name of the branch the staff member is assigned to.
     * @param existingBranches A list of existing branches.
     */
    public Staff(String Name, String userName, char gender, char designation, int age, String branchName,
                 List<Branch> existingBranches) {
        this.Name = Name;
        this.userName = userName;
        this.gender = gender;
        this.designation = designation;
        this.age = age;
        this.password = "password"; // Default password
        for (Branch branch : existingBranches) {
            if (branch.getName().equals(branchName)) {
                this.staffBranch = branch;
                this.branchName = branchName;
                break;
            }
        }
    }

    // Getter methods
    public String getUserName() {
        return userName;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return password;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public char getDesignation() {
        return designation;
    }

    public String getBranchName() {
        return branchName;
    }

    public Branch getStaffBranch() {
        return staffBranch;
    }

    // Setter methods
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setDesignation(char designation) {
        this.designation = designation;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setStaffBranch(Branch staffBranch) {
        this.staffBranch = staffBranch;
    }

    // Methods to carry on the duties
    /**
     * Retrieves the menu items for the branch the staff member belongs to.
     *
     * @return A list of menu items for the staff member's branch.
     */
    public List<MenuItem> viewMenu() {
        return staffBranch.getMenuItems();
    }

    /**
     * Prints and retrieves a list of orders for the staff member's branch.
     *
     * @return A list of orders for the staff member's branch.
     */
    public List<Order> viewOrders() {
        staffBranch.viewAllOrders();
        return staffBranch.getListOfOrders();
    }

    /**
     * Allows the staff member to view individual orders based on order ID.
     * Prompts the user to enter the order ID and displays order details if found.
     */
    public void viewIndOrders() {
        List<Order> orders = this.getStaffBranch().getListOfOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders available for this branch.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        int orderId;
        boolean found = false;

        System.out.println("Available Orders:");
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderId());
            // Additional details of the order can be printed here
        }

        do {
            System.out.println("Enter the order ID of the order you want to view:");
            orderId = scanner.nextInt();

            // Check if the entered order ID is valid
            for (Order order : orders) {
                if (order.getOrderId() == orderId) {
                    found = true;
                    List<MenuItem> items = order.getItems();
                    System.out.println("Order Details:");
                    System.out.println("Order ID: " + order.getOrderId());
                    System.out.println("Takeaway/Dine-in: " + (order.isTakeaway() ? "Takeaway" : "Dine-in"));
                    System.out.println("Total Cost: " + order.getOrderCost());
                    System.out.println("Items:");
                    if (order.getCustomization() != null) {
                        System.out.println("Customisation: " + order.getCustomization());
                    }

                    for (MenuItem item : items) {
                        System.out.println("  - " + item.getName() + ": $" + item.getPrice());
                    }
                    System.out.println(); // Empty line to separate orders
                    break;
                }
            }

            if (!found) {
                System.out.println("Invalid order ID. Please enter a valid ID.");
            }
        } while (!found);
    }

    /**
     * Allows the staff member to mark a new order as ready for pick up.
     * Displays the available new orders and prompts the user to select one.
     */
    public void statusToReady() {
        List<Order> orders = this.getStaffBranch().getListOfOrders();
        Scanner scanner = new Scanner(System.in);

        // Filter orders with NEW status
        List<Order> newOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatus() == Order.OrderStatus.NEW) {
                newOrders.add(order);
            }
        }

        if (newOrders.isEmpty()) {
            System.out.println("No new orders available for processing.");
            return;
        }

        // Display available new orders
        System.out.println("Available New Orders:");
        for (Order order : newOrders) {
            System.out.println("Order ID: " + order.getOrderId());
            // Additional details of the order can be printed here
        }

        // Prompt staff to choose an order to mark as ready
        boolean found = false;
        do {
            System.out.println("Enter the order ID of the order you want to mark as ready:");
            int orderId = scanner.nextInt();

            // Check if the entered order ID is valid
            for (Order order : newOrders) {
                if (order.getOrderId() == orderId) {
                    found = true;
                    // Mark the selected order as ready to pick up
                    order.markAsReadyToPickUp();
                    System.out.println("Order with ID " + orderId + " has been marked as ready to pick up.");
                    break;
                }
            }

            if (!found) {
                System.out.println("Invalid order ID. Please enter a valid ID.");
            }
        } while (!found);
    }
}
