package it.gabrieletondi.telldontaskkata.domain.service;

import it.gabrieletondi.telldontaskkata.domain.model.order.Order;
import it.gabrieletondi.telldontaskkata.domain.model.order.OrderItem;

import java.util.List;

public interface OrderService {

    void approve(String id);

    Order create(List<OrderItem> orderItems);

    void reject(String id);

    void ship(String id);
}
