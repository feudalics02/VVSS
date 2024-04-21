package pizzashop.service;

import org.junit.jupiter.api.Test;
import pizzashop.exceptions.PaymentException;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PaymentServiceIntegrationAllTest {
    private final PaymentRepository paymentRepository = new PaymentRepository("data/payment_test.txt");

    private final Payment payment = new Payment(1, PaymentType.Card, 100.5);

    private final PizzaService pizzaService = new PizzaService(new MenuRepository(), paymentRepository);

    @Test
    void testAddPaymentInvalidAmount() {
        try {
            pizzaService.addPayment(1, PaymentType.Card, payment.getAmount());
        } catch (PaymentException e) {
            assertEquals("Invalid amount", e.getMessage());
        }
    }

    @Test
    void testAddPaymentInvalidTableNumber() {
        try {
            pizzaService.addPayment(payment.getTableNumber(), PaymentType.Card, 20);
        } catch (PaymentException e) {
            assertEquals("Invalid table", e.getMessage());
        }
    }

    @Test
    void testAddPaymentSuccess() {
        int size = pizzaService.getPayments().size();

        try {
            pizzaService.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount());
        } catch (PaymentException e) {
            fail();
        }

        assertEquals(size + 1, pizzaService.getPayments().size());
    }
}
