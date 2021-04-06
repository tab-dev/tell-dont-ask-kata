package it.gabrieletondi.telldontaskkata.domain.service;

import it.gabrieletondi.telldontaskkata.domain.model.order.Order;

public interface ShipmentService {
    void ship(Order order);
}
