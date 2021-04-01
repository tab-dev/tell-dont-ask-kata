package it.gabrieletondi.telldontaskkata.domain.order.status;

import it.gabrieletondi.telldontaskkata.domain.exception.ApprovedOrderCannotBeRejectedException;

public class ApprovedOrderStatus implements OrderStatus {
    @Override
    public OrderStatusType getStatusType() {
        return OrderStatusType.APPROVED;
    }

    @Override
    public OrderStatus reject() {
        throw new ApprovedOrderCannotBeRejectedException();
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
