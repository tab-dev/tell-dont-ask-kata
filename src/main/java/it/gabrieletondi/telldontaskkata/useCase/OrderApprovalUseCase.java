package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.exception.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.domain.exception.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.domain.exception.ShippedOrdersCannotBeChangedException;
import it.gabrieletondi.telldontaskkata.domain.order.Order;
import it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;

public class OrderApprovalUseCase {
    private final OrderRepository orderRepository;

    public OrderApprovalUseCase(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void run(OrderApprovalRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());

        if (order.getStatus().equals(OrderStatusType.SHIPPED)) {
            throw new ShippedOrdersCannotBeChangedException();
        }

        if (request.isApproved() && order.getStatus().equals(OrderStatusType.REJECTED)) {
            throw new RejectedOrderCannotBeApprovedException();
        }

        if (!request.isApproved() && order.getStatus().equals(OrderStatusType.APPROVED)) {
            throw new ApprovedOrderCannotBeRejectedException();
        }

        order.setStatus(request.isApproved() ? OrderStatusType.APPROVED : OrderStatusType.REJECTED);
        orderRepository.save(order);
    }
}
