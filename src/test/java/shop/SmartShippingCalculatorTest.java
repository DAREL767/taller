package shop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SmartShippingCalculatorTest {
    private final ShippingCalculator calculator = new SmartShippingCalculator();

    @Test
    void cobraDosYPuntoCincoPorKiloEnBogota() {
        Order order = new Order("o1", "c1", "Bogotá", 100.0, 3.0);
        assertEquals(7.5, calculator.calculate(order));
    }

    @Test
    void sumaCincoFueraDeBogota() {
        Order order = new Order("o1", "c1", "Cali", 100.0, 4.0);
        assertEquals(15.0, calculator.calculate(order));
    }
}
