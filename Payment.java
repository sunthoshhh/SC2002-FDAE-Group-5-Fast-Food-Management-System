package fsmjdoc;

/**
 * The Payment interface defines methods related to payment processing.
 * Implementations of this interface provide specific payment processing methods for different payment types.
 */
public interface Payment {
    
    /**
     * Processes the payment for the specified order.
     * The method should handle payment processing for the given order and perform any necessary actions.
     *
     * @param order The Order object for which the payment is to be processed.
     */
    void processPayment(Order order);
    
    /**
     * Returns the name of the payment method.
     * Implementations should provide the name of the payment method they represent.
     *
     * @return The name of the payment method.
     */
    String getMethodName();
}
