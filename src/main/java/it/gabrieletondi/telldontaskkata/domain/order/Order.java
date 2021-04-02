package it.gabrieletondi.telldontaskkata.domain.order;

import it.gabrieletondi.telldontaskkata.domain.order.status.CreatedOrderStatus;
import it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatus;
import it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private String currency;
    private List<OrderItem> items;
    private OrderStatus orderStatus;
    private int id;

    public Order() {
        orderStatus = new CreatedOrderStatus();
        this.items = new ArrayList<>();
    }

    public Order(List<OrderItem> items, OrderStatus orderStatus) {
        this.items = items;
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotal() {
        return this.items.stream()
                .map(orderItem -> orderItem.getTaxedAmount())
                .reduce((taxedAmount1, taxedAmount2) -> taxedAmount1.add(taxedAmount2))
                .orElse(BigDecimal.ZERO);
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getTax() {
        return this.items.stream()
                .map(orderItem -> orderItem.getTax())
                .reduce((tax1,tax2) -> tax1.add(tax2))
                .orElse(BigDecimal.ZERO);
    }

    public OrderStatusType getStatusType() {
        return this.orderStatus.getStatusType();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.items.add(orderItem);
    }

    public void approve() {
        this.orderStatus = this.orderStatus.approve();
    }

    public void reject() {
        this.orderStatus = this.orderStatus.reject();
    }

    public void ship() {
        this.orderStatus = this.orderStatus.ship();
    }
}
