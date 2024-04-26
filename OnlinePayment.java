package fsmjdoc;

import java.util.Scanner;

/**
 * The OnlinePayment class implements the Payment interface and provides methods for processing online payments for orders.
 */
public class OnlinePayment implements Payment {

    /**
     * Processes an online payment for a given order.
     *
     * @param order The Order object for which the payment is to be processed.
     */
    public void processPayment(Order order) {
        double orderCost = order.getOrderCost();
        Branch branch = order.getSelectedBranch();
        boolean paymentSuccess = processOnlinePayment(orderCost);
        
        if (paymentSuccess) {
            branch.getListOfOrders().add(order);
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }

    /**
     * Processes the online payment for the specified order cost.
     *
     * @param orderCost The cost of the order to be paid online.
     * @return true if the payment is successful, false otherwise.
     */
    private boolean processOnlinePayment(double orderCost) {
        System.out.println();
        String formattedChange = String.format("%.2f", orderCost);
        System.out.println("Amount to be paid: " + formattedChange);
        System.out.println("Choose Online Payment Method");
        System.out.println("1. PayPal");
        System.out.println("2. PayNow");
        System.out.println("3. Google Pay");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        
        if (choice >= 1 && choice <= 3) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the name of the payment method.
     *
     * @return A string representing the name of the payment method ("Online Payment").
     */
    @Override
    public String getMethodName() {
        return "Online Payment";
    }
}
