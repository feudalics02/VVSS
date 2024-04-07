package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.exceptions.PaymentException;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

class PizzaServiceTest {

    private double amount;
    private int table;
    private PaymentType type;

    private final MenuRepository menuRepo = new MenuRepository();
    private final PaymentRepository payRepo = new PaymentRepository();
    private final PizzaService service = new PizzaService(menuRepo, payRepo);

    private int paymentsSize = service.getPayments().size();

    @BeforeEach
    void setUp() {
        type = PaymentType.Card;
    }

    @Test
    @Tag("BoundaryValueAnalysis")
    void addPayment_BVA_valid_table() {
        amount = 2;
        table = 1;

        assert service.getPayments().size() == paymentsSize;

        try {
            service.addPayment(table, type, amount);

        } catch (PaymentException e) {
            assert false;
        }

        assert service.getPayments().size() == paymentsSize + 1;
        paymentsSize++;
    }

    @Test
    @Tag("BoundaryValueAnalysis")
    void addPayment_BVA_invalid_table() {
        amount = 3;
        table = 0;

        try {
            service.addPayment(table, type, amount);
            assert false;
        } catch (PaymentException e) {
            assert e.getMessage().equals("Invalid table");
        }
    }

    @Test
    @Tag("BoundaryValueAnalysis")
    void addPayment_BVA_valid_amount() {
        amount = 1;
        table = 3;

        assert service.getPayments().size() == paymentsSize;

        try {
            service.addPayment(table, type, amount);
        } catch (PaymentException e) {
            assert false;
        }

        assert service.getPayments().size() == paymentsSize + 1;
        paymentsSize++;
    }

    @Test
    @Tag("BoundaryValueAnalysis")
    void addPayment_BVA_invalid_amount() {
        amount = 0;
        table = 2;

        try {
            service.addPayment(table, type, amount);
            assert false;
        } catch (PaymentException e) {
            assert e.getMessage().equals("Invalid amount");
        }
    }

    @Test
    @DisplayName("Valid Amount ECP Test")
    void addPayment_ECP_valid_amount() {
        amount = 2;
        table = 3;

        assert service.getPayments().size() == paymentsSize;

        try {
            service.addPayment(table, type, amount);
        } catch (PaymentException e) {
            assert false;
        }

        assert service.getPayments().size() == paymentsSize + 1;
        paymentsSize++;
    }

    @Test
    @Timeout(5)
    void addPayment_ECP_invalid_amount() {
        amount = -5;
        table = 3;

        try {
            service.addPayment(table, type, amount);
            assert false;
        } catch (PaymentException e) {
            assert e.getMessage().equals("Invalid amount");
        }
    }

    @RepeatedTest(2)
    void addPayment_ECP_invalid_table_1() {
        amount = 2;
        table = -4;

        try {
            service.addPayment(table, type, amount);
            assert false;
        } catch (PaymentException e) {
            assert e.getMessage().equals("Invalid table");
        }
    }

    @Test
    void addPayment_ECP_invalid_table_2() {
        amount = 2;

        try {
            service.addPayment(0.5, type, amount);
            assert false;
        } catch (PaymentException e) {
            assert e.getMessage().equals("Invalid table type");
        }
    }

    @Test
    @Disabled("Pending feature implementation")
    void dummyTest() {
        assert false;
    }
}