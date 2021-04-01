package it.gabrieletondi.telldontaskkata.domain.order.status;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderCannotBeShippedTwiceException;
import it.gabrieletondi.telldontaskkata.domain.exception.ShippedOrdersCannotBeChangedException;

public class ShippedOrderStatus implements OrderStatus {


    @Override
    public OrderStatus reject() {
        throw new ShippedOrdersCannotBeChangedException();
    }

    @Override
    public OrderStatus approve() {
        throw new ShippedOrdersCannotBeChangedException();
    }

    @Override
    public OrderStatus ship() {
        throw new OrderCannotBeShippedTwiceException();
    }
}
