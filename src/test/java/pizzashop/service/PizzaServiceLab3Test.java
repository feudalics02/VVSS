package pizzashop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PizzaServiceLab3Test {
    private double amount;

    private PaymentType type;

    private final MenuRepository menuRepo = new MenuRepository();
    @Mock
    private final PaymentRepository payRepo = mock(PaymentRepository.class);
    private final PizzaService service = new PizzaService(menuRepo, payRepo);

    @BeforeEach
    void setUp() {
        type = PaymentType.Cash;
    }

    @Test
    void F02_TC01() {
        when(payRepo.getAll()).thenReturn(null);
        assert (payRepo.getAll() == null);
        amount = service.getTotalAmount(type);
        assert (amount == 0);
    }

    @Test
    void F02_TC02() {
        when(payRepo.getAll()).thenReturn(new ArrayList<Payment>());
        assert (payRepo.getAll().size() == 0);
        amount = service.getTotalAmount(type);
        assert (amount == 0);
    }

    @Test
    void F02_TC04() {
        Payment payment = new Payment(1, PaymentType.Card, 20);
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        when(payRepo.getAll()).thenReturn(paymentList);
        assert (payRepo.getAll().size() == 1);
        amount = service.getTotalAmount(type);
        assert (amount == 0);
    }

    @Test
    void F02_TC05() {
        Payment payment = new Payment(1, PaymentType.Cash, 10);
        Payment payment2 = new Payment(1, PaymentType.Cash, 5);
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);
        paymentList.add(payment2);
        when(payRepo.getAll()).thenReturn(paymentList);
        assert (payRepo.getAll().size() == 2);
        amount = service.getTotalAmount(type);
        assert (amount == 15);
    }
}