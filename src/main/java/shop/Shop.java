package shop;

public class Shop {
    public static Checkout checkout() {
        FeatureFlags flags = new FeatureFlags();
        return new Checkout(new ShippingCalculatorProxy(
                new LegacyShippingCalculator(), order -> 0.0, flags));
    }
}
