package shop;

import java.util.logging.Logger;

public class ShippingCalculatorProxy implements ShippingCalculator {
    private static final Logger LOGGER = Logger.getLogger(ShippingCalculatorProxy.class.getName());

    private final ShippingCalculator delegate;

    public ShippingCalculatorProxy(ShippingCalculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public double calculate(Order order) {
        double cost = delegate.calculate(order);
        LOGGER.info(() -> "Envío del pedido " + order.id() + ": " + cost);
        return cost;
    }
}
