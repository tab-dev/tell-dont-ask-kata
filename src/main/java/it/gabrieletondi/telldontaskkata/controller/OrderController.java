package it.gabrieletondi.telldontaskkata.controller;

import it.gabrieletondi.telldontaskkata.domain.exception.UnknownProductException;
import it.gabrieletondi.telldontaskkata.domain.model.order.Order;
import it.gabrieletondi.telldontaskkata.domain.model.order.OrderItem;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import it.gabrieletondi.telldontaskkata.domain.service.OrderService;
import it.gabrieletondi.telldontaskkata.controller.model.SellItemsRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderController {

    private final OrderService orderService;
    private final ProductCatalog productCatalog;

    public OrderController(OrderService orderService, ProductCatalog productCatalog) {
        this.orderService = orderService;
        this.productCatalog = productCatalog;
    }

    public Order create(SellItemsRequest request){
        List<OrderItem> orderItems = request.getRequests().stream()
                .map(itemRequest -> new OrderItem(
                        Optional.ofNullable(productCatalog.getByName(itemRequest.getProductName()))
                                .orElseThrow(() -> new UnknownProductException()),
                        itemRequest.getQuantity()))
                .collect(Collectors.toList());

        return orderService.create(orderItems);
    }

    public void approve(int orderId){
        orderService.approve(orderId);
    }

    public void reject(int orderId){
        orderService.reject(orderId);
    }

    public void ship(int orderId){
        orderService.ship(orderId);
    }
}
