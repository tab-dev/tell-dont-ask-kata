package it.gabrieletondi.telldontaskkata.domain.service;

import it.gabrieletondi.telldontaskkata.domain.order.Order;

public interface ShipmentService {
    void ship(Order order);
}
