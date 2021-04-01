package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderCannotBeShippedException;
import it.gabrieletondi.telldontaskkata.domain.exception.OrderCannotBeShippedTwiceException;
import it.gabrieletondi.telldontaskkata.domain.order.Order;
import it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.service.ShipmentService;

import static it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType.CREATED;
import static it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType.REJECTED;
import static it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType.SHIPPED;

public class OrderShipmentUseCase {
    private final OrderRepository orderRepository;
    private final ShipmentService shipmentService;

    public OrderShipmentUseCase(OrderRepository orderRepository, ShipmentService shipmentService) {
        this.orderRepository = orderRepository;
        this.shipmentService = shipmentService;
    }

    public void run(OrderShipmentRequest request) {
        final Order order = orderRepository.getById(request.getOrderId());

        if (order.getStatus().equals(CREATED) || order.getStatus().equals(REJECTED)) {
            throw new OrderCannotBeShippedException();
        }

        if (order.getStatus().equals(SHIPPED)) {
            throw new OrderCannotBeShippedTwiceException();
        }

        shipmentService.ship(order);

        order.setStatus(OrderStatusType.SHIPPED);
        orderRepository.save(order);
    }
}
