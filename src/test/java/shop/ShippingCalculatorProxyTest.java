package shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShippingCalculatorProxyTest {
    private final Order order = new Order("o1", "c1", "Cali", 100.0, 3.0);
    private final FeatureFlags flags = new FeatureFlags();
    private final ShippingCalculator proxy = new ShippingCalculatorProxy(
            new LegacyShippingCalculator(), new SmartShippingCalculator(), flags);

    @Test
    void usaLegacyConFlagApagado() {
        assertEquals(10.0, proxy.calculate(order));
    }

    @Test
    void usaSmartConFlagEncendido() {
        flags.enable("smart-shipping");
        assertEquals(7.5, proxy.calculate(order));
    }
}
