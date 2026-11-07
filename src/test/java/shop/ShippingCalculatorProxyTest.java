package shop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShippingCalculatorProxyTest {
    @Test
    void delegaYRegistraElCostoDelEnvio() {
        Order order = new Order("o1", "c1", "Cali", 100.0, 4.0);
        Logger logger = Logger.getLogger(ShippingCalculatorProxy.class.getName());
        List<String> messages = new ArrayList<>();
        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                messages.add(record.getMessage());
            }

            @Override
            public void flush() {}

            @Override
            public void close() {}
        };

        logger.addHandler(handler);
        try {
            ShippingCalculator proxy = new ShippingCalculatorProxy(received -> {
                assertEquals(order, received);
                return 7.5;
            });
            assertEquals(7.5, proxy.calculate(order));
            assertEquals(List.of("Envío del pedido o1: 7.5"), messages);
        } finally {
            logger.removeHandler(handler);
        }
    }
}
