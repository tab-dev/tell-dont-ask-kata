package it.gabrieletondi.telldontaskkata.domain.service.impl;

import it.gabrieletondi.telldontaskkata.domain.order.Order;
import it.gabrieletondi.telldontaskkata.domain.order.OrderItem;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.domain.service.OrderService;
import it.gabrieletondi.telldontaskkata.domain.service.ShipmentService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ShipmentService shipmentService;

    public OrderServiceImpl(OrderRepository orderRepository, ShipmentService shipmentService) {
        this.orderRepository = orderRepository;
        this.shipmentService = shipmentService;
    }

    @Override
    public void approve(int id) {
        final Order order = orderRepository.getById(id);

        order.approve();

        orderRepository.save(order);
    }

    @Override
    public Order create(List<OrderItem> orderItems) {
        Order order = new Order(0, "EUR");

        orderItems.stream()
                .forEach(orderItem -> order.addOrderItem(orderItem));

        orderRepository.save(order);

        return order;
    }

    @Override
    public void reject(int id) {
        final Order order = orderRepository.getById(id);

        order.reject();

        orderRepository.save(order);
    }

    @Override
    public void ship(int id) {
        final Order order = orderRepository.getById(id);

        shipmentService.ship(order);

        order.ship();

        orderRepository.save(order);
    }
}