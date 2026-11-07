package shop;

public class Shop {
    public static Checkout checkout() {
        return new Checkout(new LegacyShippingCalculator());
    }
}
