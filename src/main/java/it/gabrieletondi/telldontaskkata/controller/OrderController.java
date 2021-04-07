package it.gabrieletondi.telldontaskkata.controller;

import it.gabrieletondi.telldontaskkata.domain.exception.UnknownProductException;
import it.gabrieletondi.telldontaskkata.domain.model.order.Order;
import it.gabrieletondi.telldontaskkata.domain.model.order.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.service.ProductService;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import it.gabrieletondi.telldontaskkata.domain.service.OrderService;
import it.gabrieletondi.telldontaskkata.controller.model.SellItemsRequest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    public Order create(SellItemsRequest request){
        List<OrderItem> orderItems = request.getRequests().stream()
                .map(itemRequest -> new OrderItem(
                        productService.getProduct(itemRequest.getProductName()),
                        itemRequest.getQuantity()))
                .collect(Collectors.toList());

        return orderService.create(orderItems);
    }

    public void approve(String orderId){
        orderService.approve(orderId);
    }

    public void reject(String orderId){
        orderService.reject(orderId);
    }

    public void ship(String orderId){
        orderService.ship(orderId);
    }
}
