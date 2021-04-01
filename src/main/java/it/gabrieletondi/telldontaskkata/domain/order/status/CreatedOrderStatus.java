package it.gabrieletondi.telldontaskkata.domain.order.status;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderCannotBeShippedException;

public class CreatedOrderStatus implements OrderStatus {

    @Override
    public OrderStatusType getStatusType() {
        return OrderStatusType.CREATED;
    }

    @Override
    public OrderStatus reject() {
        return new RejectedOrderStatus();
    }

    @Override
    public OrderStatus approve() {
        return new ApprovedOrderStatus();
    }

    @Override
    public OrderStatus ship() {
        throw new OrderCannotBeShippedException();
    }
}
