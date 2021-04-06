package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.exception.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.domain.exception.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.domain.exception.ShippedOrdersCannotBeChangedException;
import it.gabrieletondi.telldontaskkata.domain.order.Order;
import it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.service.OrderService;

public class OrderApprovalUseCase {
    private OrderService orderService;

    public OrderApprovalUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    public void run(OrderApprovalRequest request) {

        if (request.isApproved()) {
            orderService.approve(request.getOrderId());
        } else {
            orderService.reject(request.getOrderId());
        }
    }
}
