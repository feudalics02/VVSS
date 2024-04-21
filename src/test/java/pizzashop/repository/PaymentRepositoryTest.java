package pizzashop.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pizzashop.exceptions.PaymentException;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentRepositoryTest {
    private final PaymentRepository paymentRepository = new PaymentRepository("src/test/data/payments.txt");

    @Mock
    private final Payment payment = mock(Payment.class);

    @Test
    void testGetPaymentsEmpty() {
        List<Payment> payments = paymentRepository.getAll();
        assertEquals(0, payments.size());
    }

    @Test
    void testAddPayment() {
        when(payment.getTableNumber()).thenReturn(5);
        when(payment.getAmount()).thenReturn(20.0);
        when(payment.getType()).thenReturn(PaymentType.Card);

        int size = paymentRepository.getAll().size();
        try {
            paymentRepository.add(payment);
        } catch (PaymentException e) {
            fail();
        }
        assertEquals(size + 1, paymentRepository.getAll().size());
    }
}
