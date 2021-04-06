package it.gabrieletondi.telldontaskkata.domain.model.order.status;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderApprovalException;
import it.gabrieletondi.telldontaskkata.domain.exception.OrderRejectionException;
import it.gabrieletondi.telldontaskkata.domain.exception.OrderShipmentException;

public class ShippedOrderStatus implements OrderStatus {


    @Override
    public OrderStatusType getStatusType() {
        return OrderStatusType.SHIPPED;
    }

    @Override
    public OrderStatus reject() {
        throw new OrderRejectionException("Shipped orders cannot be rejected.");
    }

    @Override
    public OrderStatus approve() {
        throw new OrderApprovalException("Shipped orders cannot be approved.");
    }

    @Override
    public OrderStatus ship() {
        throw new OrderShipmentException("Shipped orders cannot be shipped again.");
    }
}
