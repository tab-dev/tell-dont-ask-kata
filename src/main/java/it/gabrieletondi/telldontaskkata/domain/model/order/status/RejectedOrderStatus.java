package it.gabrieletondi.telldontaskkata.domain.model.order.status;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderApprovalException;
import it.gabrieletondi.telldontaskkata.domain.exception.OrderShipmentException;

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
        throw new OrderApprovalException("Rejected orders cannot be approved.");
    }

    @Override
    public OrderStatus ship() {
        throw new OrderShipmentException("Order cannot be shipped due to rejected status.");
    }
}
