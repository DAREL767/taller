package shop;

public class Checkout {
    private final ShippingCalculator calculator;

    public Checkout(ShippingCalculator calculator) {
        this.calculator = calculator;
    }

    public double total(Order order) {
        return order.subtotal() + calculator.calculate(order);
    }
}
