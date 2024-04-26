package fsmjdoc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * The Order class represents a customer's order in the system.
 * It contains information about the order such as order ID, items, order cost, status, pickup time, and more.
 */
public class Order {

    /**
     * Enum representing the various statuses an order can have.
     */
    enum OrderStatus {
        NEW,
        READY_TO_PICKUP,
        COMPLETED,
        CANCELED,
        NOTEXISTING
    }

    /**
     * The threshold in minutes after which an order should be automatically canceled if not picked up.
     */
    private static final long AUTO_CANCEL_THRESHOLD_MINUTES = 1;

    private int orderId;
    private static AtomicInteger nextId = new AtomicInteger(1);
    private ArrayList<MenuItem> items;
    private double orderCost;
    private OrderStatus status;
    private LocalDateTime pickUpTime;
    private String customization;
    // private Payment selectedPaymentMethod;
    private Branch selectedBranch;
    private boolean isTakeaway;

    /**
     * Constructor for creating a new order.
     * Initializes the order with a unique ID, an empty list of items, and default values for other fields.
     */
    public Order() {
        orderId = nextId.getAndIncrement();
        items = new ArrayList<>();
        orderCost = 0.0;
        status = OrderStatus.NEW;
        customization = null;
        isTakeaway = false;
    }

    /**
     * Adds a menu item to the order and updates the order cost.
     *
     * @param item The MenuItem object to add to the order.
     */
    public void addItem(MenuItem item) {
        items.add(item);
        orderCost += item.getPrice();
    }

    /**
     * Removes a menu item from the order and updates the order cost.
     *
     * @param item The MenuItem object to remove from the order.
     */
    public void removeItem(MenuItem item) {
        items.remove(item);
        orderCost -= item.getPrice();
    }

    /**
     * Marks the order as ready for pickup and sets the pickup time to the current time.
     */
    public void markAsReadyToPickUp() {
        status = OrderStatus.READY_TO_PICKUP;
        pickUpTime = LocalDateTime.now();
    }

    /**
     * Checks if the order should be automatically canceled based on the time since it was ready for pickup.
     */
    public void checkForAutoCancellation() {
        if (status == OrderStatus.READY_TO_PICKUP) {
            LocalDateTime currentTime = LocalDateTime.now();
            Duration duration = Duration.between(pickUpTime, currentTime);
            long minutesElapsed = duration.toMinutes();

            if (minutesElapsed >= AUTO_CANCEL_THRESHOLD_MINUTES) {
                cancelOrder();
            }
        }
    }

    /**
     * Cancels the order and updates the order status to CANCELED.
     */
    private void cancelOrder() {
        status = OrderStatus.CANCELED;
    }

    /**
     * Prints the details of the order including order ID, items, total cost, and order type (takeaway or dine-in).
     */
    public void printOrder() {
        System.out.println("Order id: " + orderId);
        System.out.println("Items:");
        for (MenuItem item : items) {
            System.out.println("- " + item.getName() + ": $" + item.getPrice());
        }
        System.out.println("Total Cost: $" + orderCost);
        System.out.println("Order Type: " + (isTakeaway ? "Takeaway" : "Dine-in"));
    }

    /**
     * Sets whether the order is for takeaway or dine-in.
     *
     * @param isTakeaway True if the order is for takeaway, false if dine-in.
     */
    public void setTakeaway(boolean isTakeaway) {
        this.isTakeaway = isTakeaway;
    }

    /**
     * Returns whether the order is for takeaway.
     *
     * @return True if the order is for takeaway, false if dine-in.
     */
    public boolean isTakeaway() {
        return isTakeaway;
    }

    /**
     * Returns the order ID.
     *
     * @return The order ID.
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Returns the list of items in the order.
     *
     * @return The list of MenuItem objects in the order.
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Returns the total cost of the order.
     *
     * @return The total cost of the order.
     */
    public double getOrderCost() {
        return orderCost;
    }

    /**
     * Returns the status of the order.
     *
     * @return The current status of the order.
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the order.
     *
     * @param status The new status of the order.
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    /**
     * Returns the pickup time of the order.
     *
     * @return The LocalDateTime object representing the pickup time of the order.
     */
    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    /**
     * Returns the customization instructions for the order.
     *
     * @return The customization instructions for the order.
     */
    public String getCustomization() {
        return customization;
    }

    /**
     * Sets the customization instructions for the order.
     *
     * @param customization The customization instructions for the order.
     */
    public void setCustomization(String customization) {
        this.customization = customization;
    }

    /*
    public Payment getSelectedPaymentMethod() {
        return selectedPaymentMethod;
    }

    public void setSelectedPaymentMethod(Payment selectedPaymentMethod) {
        this.selectedPaymentMethod = selectedPaymentMethod;
    }
    */

    /**
     * Returns the selected branch for the order.
     *
     * @return The Branch object representing the selected branch.
     */
    public Branch getSelectedBranch() {
        return selectedBranch;
    }

    /**
     * Sets the selected branch for the order.
     *
     * @param selectedBranch The Branch object representing the selected branch.
     */
    public void setSelectedBranch(Branch selectedBranch) {
        this.selectedBranch = selectedBranch;
    }

    /**
     * Prints a summary of the order including order ID, status, cost, type (takeaway or dine-in), and number of items.
     */
    public void getOrderSummary() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Status: " + status);
        if (customization != null) {
            System.out.println("Order Customization: " + customization);
        }
        System.out.println("Order Cost: $" + orderCost);
        System.out.println("Order Type: " + (isTakeaway ? "Takeaway" : "Dine-in"));
        System.out.println("Number of Items: " + items.size());
    }

    /**
     * Prints the details of the order including order ID, status, type, customization, and the details of each item in the order.
     */
    public void getOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Status: " + status);
        if (customization != null) {
            System.out.println("Order Customization: " + customization);
        }
        System.out.println("Order Type: " + (isTakeaway ? "Takeaway" : "Dine-in"));
        System.out.println("Number of Items: " + items.size());

        System.out.println("Order Details:");
        for (MenuItem item : items) {
            System.out.println("- " + item.getName() + ": $" + item.getPrice());
        }
        System.out.println("Total Order Cost: $" + orderCost);
    }
}
