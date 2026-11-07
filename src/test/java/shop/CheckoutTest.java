package shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTest {
    @Test
    void calculaElTotalConElEnvioConfigurado() {
        Order order = new Order("o1", "c1", "Cali", 100.0, 4.0);
        assertEquals(110.0, new Checkout().total(order));
    }
}
