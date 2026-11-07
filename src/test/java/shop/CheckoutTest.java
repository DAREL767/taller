package shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTest {
    @Test
    void calculaElTotalConElEnvioConfigurado() {
        Order order = new Order("o1", "c1", "Cali", 100.0, 4.0);
        assertEquals(115.0, Shop.checkout().total(order));
    }

    @Test
    void checkoutFuncionaConCualquierCalculador() {
        Order order = new Order("o1", "c1", "Cali", 100.0, 4.0);
        Checkout checkout = new Checkout(ignored -> 7.8);
        assertEquals(107.8, checkout.total(order), 0.000001);
    }
}
