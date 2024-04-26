package fsmjdoc;

import java.util.Scanner;

/**
 * Represents a cash payment method for processing orders.
 */
public class CashPayment implements Payment {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Processes a cash payment for the given order.
     *
     * @param order The order for which payment is being processed.
     */
    public void processPayment(Order order) {
        double orderCost = order.getOrderCost();
        Branch branch = order.getSelectedBranch();
        String format = String.format("%.2f", orderCost);
        System.out.println("Amount to be paid: " + format);

        double paymentAmount = getPaymentAmount();
        while (paymentAmount >= orderCost) {
            double change = paymentAmount - orderCost;
            String formattedChange = String.format("%.2f", change);
            System.out.println("Change: " + formattedChange);
            
            // Add the order to the list of orders for the branch
            branch.getListOfOrders().add(order);
            break;
        }
    }

    /**
     * Prompts the user for a payment amount and returns it.
     *
     * @return The payment amount entered by the user.
     */
    private double getPaymentAmount() {
        System.out.print("Enter payment amount: ");
        return scanner.nextDouble();
    }

    /**
     * Returns the name of the payment method.
     *
     * @return The name of the payment method.
     */
    public String getMethodName() {
        return "Cash Payment";
    }
}
