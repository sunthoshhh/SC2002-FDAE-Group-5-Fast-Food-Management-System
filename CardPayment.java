package fsmjdoc;

import java.util.Scanner;

/**
 * The CardPayment class implements the Payment interface and represents a payment method using bank cards.
 */
public class CardPayment implements Payment {

    /**
     * Processes the payment for a given order using card payment.
     *
     * @param order The order for which payment is to be processed.
     */
    public void processPayment(Order order) {
        Branch branch = order.getSelectedBranch();
        double orderCost = order.getOrderCost();
        boolean paymentSuccess = processCardPayment(orderCost); // Simulated card payment process
        if (paymentSuccess) {
            branch.getListOfOrders().add(order);
            System.out.println("Payment successful!");
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }

    /**
     * Simulates the card payment process by prompting the user to choose a bank card type.
     *
     * @param orderCost The total cost of the order to be paid.
     * @return true if the payment is successful, false otherwise.
     */
    private boolean processCardPayment(double orderCost) {
        System.out.println();
        String formattedChange = String.format("%.2f", orderCost);
        System.out.println("Amount to be paid: " + formattedChange);
        System.out.println("Choose Bank Card:");
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. ATM Card");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice >= 1 && choice <= 3;
    }

    /**
     * Returns the name of the payment method.
     *
     * @return The name of the payment method as a string.
     */
    @Override
    public String getMethodName() {
        return "Card Payment";
    }
}
