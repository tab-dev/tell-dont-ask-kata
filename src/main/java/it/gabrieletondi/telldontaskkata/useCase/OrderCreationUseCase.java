package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.exception.UnknownProductException;
import it.gabrieletondi.telldontaskkata.domain.order.Order;
import it.gabrieletondi.telldontaskkata.domain.order.OrderItem;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import it.gabrieletondi.telldontaskkata.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderCreationUseCase {
    private final OrderService orderService;
    private final ProductCatalog productCatalog;

    public OrderCreationUseCase(OrderService orderService, ProductCatalog productCatalog) {
        this.orderService = orderService;
        this.productCatalog = productCatalog;
    }

    public void run(SellItemsRequest request) {
        List<OrderItem> orderItems = request.getRequests().stream()
                .map(itemRequest -> new OrderItem(
                        Optional.ofNullable(productCatalog.getByName(itemRequest.getProductName()))
                                .orElseThrow(() -> new UnknownProductException()),
                        itemRequest.getQuantity()))
                .collect(Collectors.toList());

        orderService.create(orderItems);
    }
}
