package pizzashop.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final int id;
    private List<PizzaItem> items = new ArrayList<>();
    private OrderStatus status;

    public Order(int id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public List<PizzaItem> getItems() {
        return items;
    }

    public void setItems(List<PizzaItem> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
