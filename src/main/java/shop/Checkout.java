package shop;

public class Checkout {
    private final LegacyShippingCalculator calculator = new LegacyShippingCalculator();

    public double total(Order order) {
        return order.subtotal() + calculator.calculate(order);
    }
}
