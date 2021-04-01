package it.gabrieletondi.telldontaskkata.domain.order.status;

public class ApprovedOrderStatus implements OrderStatus {
    @Override
    public OrderStatus reject() {
        return null;
    }

    @Override
    public OrderStatus approve() {
        return null;
    }

    @Override
    public OrderStatus ship() {
        return null;
    }
}
