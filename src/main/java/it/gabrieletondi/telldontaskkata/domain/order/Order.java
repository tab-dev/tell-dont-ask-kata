package it.gabrieletondi.telldontaskkata.domain.order;

import it.gabrieletondi.telldontaskkata.domain.order.status.CreatedOrderStatus;
import it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatus;
import it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private BigDecimal total;
    private String currency;
    private List<OrderItem> items;
    private BigDecimal tax;
    private OrderStatus orderStatus;
    private int id;

    public Order() {
        orderStatus = new CreatedOrderStatus();
        this.items = new ArrayList<>();
        this.total = BigDecimal.ZERO;
    }

    public Order(BigDecimal total, List<OrderItem> items, OrderStatus orderStatus) {
        this.total = total;
        this.items = items;
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
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

    public void addOrderItem(OrderItem orderItem){
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
