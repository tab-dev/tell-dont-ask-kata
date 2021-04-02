package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.Product;
import it.gabrieletondi.telldontaskkata.domain.exception.UnknownProductException;
import it.gabrieletondi.telldontaskkata.domain.order.Order;
import it.gabrieletondi.telldontaskkata.domain.order.OrderItem;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;

import java.util.Optional;

public class OrderCreationUseCase {
    private final OrderRepository orderRepository;
    private final ProductCatalog productCatalog;

    public OrderCreationUseCase(OrderRepository orderRepository, ProductCatalog productCatalog) {
        this.orderRepository = orderRepository;
        this.productCatalog = productCatalog;
    }

    public void run(SellItemsRequest request) {
        Order order = new Order(0, "EUR");

        request.getRequests().stream()
                .map(itemRequest -> new OrderItem(
                        Optional.ofNullable(productCatalog.getByName(itemRequest.getProductName()))
                                .orElseThrow(() -> new UnknownProductException()),
                        itemRequest.getQuantity()))
                .forEach(orderItem -> order.addOrderItem(orderItem));

        orderRepository.save(order);
    }
}
