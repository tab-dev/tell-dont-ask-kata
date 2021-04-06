package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderCannotBeShippedException;
import it.gabrieletondi.telldontaskkata.domain.exception.OrderCannotBeShippedTwiceException;
import it.gabrieletondi.telldontaskkata.domain.order.Order;
import it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.service.OrderService;
import it.gabrieletondi.telldontaskkata.service.ShipmentService;

import static it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType.CREATED;
import static it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType.REJECTED;
import static it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType.SHIPPED;

public class OrderShipmentUseCase {
    private final OrderService orderService;

    public OrderShipmentUseCase(OrderService orderService) {
        this.orderService = orderService;
    }

    public void run(OrderShipmentRequest request) {
        orderService.ship(request.getOrderId());
    }
}
