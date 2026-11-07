package shop;

public class Shop {
    public static Checkout checkout() {
        return new Checkout(new ShippingCalculatorProxy(new SmartShippingCalculator()));
    }
}
