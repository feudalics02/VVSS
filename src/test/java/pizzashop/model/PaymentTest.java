package pizzashop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {
    private final Payment payment = new Payment(3, PaymentType.Card, 100);

    @Test
    void testGetTableNumber() {
        assertEquals(3, payment.getTableNumber());
    }

    @Test
    void testGetPaymentType() {
        assertEquals(PaymentType.Card, payment.getType());
    }

    @Test
    void testGetAmount() {
        assertEquals(100, payment.getAmount());
    }

    @Test
    void testSetTableNumber() {
        payment.setTableNumber(6);
        assertEquals(6, payment.getTableNumber());
    }

    @Test
    void testSetPaymentType() {
        payment.setType(PaymentType.Cash);
        assertEquals(PaymentType.Cash, payment.getType());
    }

    @Test
    void testSetAmount() {
        payment.setAmount(200);
        assertEquals(200, payment.getAmount());
    }
}
