package it.gabrieletondi.telldontaskkata.domain.model.order.status;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderShipmentException;
import it.gabrieletondi.telldontaskkata.domain.exception.RejectedOrderCannotBeApprovedException;

public class RejectedOrderStatus implements OrderStatus {
    @Override
    public OrderStatusType getStatusType() {
        return OrderStatusType.REJECTED;
    }

    @Override
    public OrderStatus reject() {
        return this;
    }

    @Override
    public OrderStatus approve() {
        throw new RejectedOrderCannotBeApprovedException();
    }

    @Override
    public OrderStatus ship() {
        throw new OrderShipmentException("Order cannot be shipped due to rejected status.");
    }
}
