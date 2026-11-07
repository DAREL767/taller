package shop;

public class SmartShippingCalculator implements ShippingCalculator {
    @Override
    public double calculate(Order order) {
        return order.weightKg() * 2.5;
    }
}
