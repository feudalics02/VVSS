package pizzashop.service;

import pizzashop.exceptions.OrderException;
import pizzashop.exceptions.PaymentException;
import pizzashop.model.PizzaItem;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;

import java.util.List;

public class PizzaService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;

    public PizzaService(MenuRepository menuRepo, PaymentRepository payRepo) {
        this.menuRepo = menuRepo;
        this.payRepo = payRepo;
    }

    public List<PizzaItem> getMenuData() throws OrderException {
        return menuRepo.getMenu();
    }

    public List<Payment> getPayments() {
        return payRepo.getAll();
    }

    public void addPayment(Object table, PaymentType type, double amount) throws PaymentException {
        if (!(table instanceof Integer)) {
            throw new PaymentException("Invalid table type");
        }

        if ((int) table < 1 || (int) table > 8) {
            throw new PaymentException("Invalid table");
        }
        if (amount < 1 || amount > 100000) {
            throw new PaymentException("Invalid amount");
        }
        Payment payment = new Payment((int) table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type) {
        double total = 0.0f;
        List<Payment> l = getPayments();
        if (l == null) {
            return total;
        }
        if (l.size() == 0) {
            return total;
        }
        for (Payment p : l) {
            if (p.getType().equals(type)) {
                total += p.getAmount();
            }
        }
        return total;
    }

}