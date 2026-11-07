package shop;

public class SmartShippingCalculator implements ShippingCalculator {
    @Override
    public double calculate(Order order) {
        double base = order.weightKg() * 2.5;
        return "Bogotá".equals(order.city()) ? base : base + 5.0;
    }
}
