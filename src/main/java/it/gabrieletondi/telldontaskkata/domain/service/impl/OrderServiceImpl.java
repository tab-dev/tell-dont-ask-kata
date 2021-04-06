package it.gabrieletondi.telldontaskkata.domain.service.impl;

import it.gabrieletondi.telldontaskkata.domain.model.order.Order;
import it.gabrieletondi.telldontaskkata.domain.model.order.OrderItem;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.domain.service.OrderService;
import it.gabrieletondi.telldontaskkata.domain.service.ShipmentService;

import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShipmentService shipmentService;

    public OrderServiceImpl(OrderRepository orderRepository, ShipmentService shipmentService) {
        this.orderRepository = orderRepository;
        this.shipmentService = shipmentService;
    }

    @Override
    public void approve(String id) {
        final Order order = orderRepository.getById(id);

        order.approve();

        orderRepository.save(order);
    }

    @Override
    public Order create(List<OrderItem> orderItems) {
        Order order = new Order(UUID.randomUUID().toString(), "EUR");

        orderItems.stream()
                .forEach(orderItem -> order.addOrderItem(orderItem));

        orderRepository.save(order);

        return order;
    }

    @Override
    public void reject(String id) {
        final Order order = orderRepository.getById(id);

        order.reject();

        orderRepository.save(order);
    }

    @Override
    public void ship(String id) {
        final Order order = orderRepository.getById(id);

        shipmentService.ship(order);

        order.ship();

        orderRepository.save(order);
    }
}
