package it.gabrieletondi.telldontaskkata.domain.model.order.status;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderRejectionException;

public class ApprovedOrderStatus implements OrderStatus {
    @Override
    public OrderStatusType getStatusType() {
        return OrderStatusType.APPROVED;
    }

    @Override
    public OrderStatus reject() {
        throw new OrderRejectionException("Approved orders cannot be rejected.");
    }

    @Override
    public OrderStatus approve() {
        return this;
    }

    @Override
    public OrderStatus ship() {
        return new ShippedOrderStatus();
    }
}
