package shop;

import java.util.logging.Logger;

public class ShippingCalculatorProxy implements ShippingCalculator {
    private static final Logger LOGGER = Logger.getLogger(ShippingCalculatorProxy.class.getName());

    private final ShippingCalculator legacy;
    private final ShippingCalculator smart;
    private final FeatureFlags flags;

    public ShippingCalculatorProxy(ShippingCalculator legacy, ShippingCalculator smart, FeatureFlags flags) {
        this.legacy = legacy;
        this.smart = smart;
        this.flags = flags;
    }

    @Override
    public double calculate(Order order) {
        double cost = flags.isEnabled("smart-shipping") ? smart.calculate(order) : legacy.calculate(order);
        LOGGER.info(() -> "Envío del pedido " + order.id() + ": " + cost);
        return cost;
    }
}
