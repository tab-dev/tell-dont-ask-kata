package it.gabrieletondi.telldontaskkata.domain.model.order.status;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderShipmentException;

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
        throw new OrderShipmentException("Orders cannot be shipped until they've been approved.");
    }
}
