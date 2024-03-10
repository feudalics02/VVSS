package pizzashop.model;


public class Payment {
    private int tableNumber;
    private PaymentType type;
    private double amount;
    private int orderId;

    public Payment(int tableNumber, PaymentType type, double amount, int orderId) {
        this.tableNumber = tableNumber;
        this.type = type;
        this.amount = amount;
        this.orderId = orderId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return tableNumber + ","+type +"," + amount;
    }
}
