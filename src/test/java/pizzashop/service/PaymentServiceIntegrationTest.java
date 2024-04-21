package pizzashop.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pizzashop.exceptions.PaymentException;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentServiceIntegrationTest {
    private final PaymentRepository paymentRepository = new PaymentRepository("src/test/data/payments.txt");

    @Mock
    private final Payment payment = mock(Payment.class);

    private final PizzaService pizzaService = new PizzaService(new MenuRepository(), paymentRepository);

    @Test
    void testAddPaymentInvalidAmount() {
        when(payment.getAmount()).thenReturn(0.0);

        try {
            pizzaService.addPayment(1, PaymentType.Card, payment.getAmount());
        } catch (PaymentException e) {
            assertEquals("Invalid amount", e.getMessage());
        }
    }

    @Test
    void testAddPaymentInvalidTableNumber() {
        when(payment.getTableNumber()).thenReturn(20);

        try {
            pizzaService.addPayment(payment.getTableNumber(), PaymentType.Card, 20);
        } catch (PaymentException e) {
            assertEquals("Invalid table", e.getMessage());
        }
    }

    @Test
    void testAddPaymentSuccess() {
        when(payment.getTableNumber()).thenReturn(5);
        when(payment.getAmount()).thenReturn(20.0);
        when(payment.getType()).thenReturn(PaymentType.Card);

        int size = pizzaService.getPayments().size();

        try {
            pizzaService.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount());
        } catch (PaymentException e) {
            fail();
        }

        assertEquals(size + 1, pizzaService.getPayments().size());
    }
}
