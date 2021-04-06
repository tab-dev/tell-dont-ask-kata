package it.gabrieletondi.telldontaskkata.domain.model.order;

import it.gabrieletondi.telldontaskkata.domain.model.order.status.CreatedOrderStatus;
import it.gabrieletondi.telldontaskkata.domain.model.order.status.OrderStatus;
import it.gabrieletondi.telldontaskkata.domain.model.order.status.OrderStatusType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private String id;
    private List<OrderItem> items;
    private OrderStatus orderStatus;
    private String currency;

    public Order(String id, String currency) {
        this.id = id;
        this.currency = currency;
        this.orderStatus = new CreatedOrderStatus();
        this.items = new ArrayList<>();
    }

    public Order(String id, List<OrderItem> items, OrderStatus orderStatus, String currency) {
        this.id = id;
        this.items = items;
        this.orderStatus = orderStatus;
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public String getCurrency() {
        return currency;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public OrderStatusType getStatusType() {
        return this.orderStatus.getStatusType();
    }

    public BigDecimal getTotal() {
        return this.items.stream()
                .map(orderItem -> orderItem.getTaxedAmount())
                .reduce((taxedAmount1, taxedAmount2) -> taxedAmount1.add(taxedAmount2))
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal getTax() {
        return this.items.stream()
                .map(orderItem -> orderItem.getTax())
                .reduce((tax1, tax2) -> tax1.add(tax2))
                .orElse(BigDecimal.ZERO);
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
