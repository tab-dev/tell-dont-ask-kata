package it.gabrieletondi.telldontaskkata.domain.order.status;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderCannotBeShippedException;
import it.gabrieletondi.telldontaskkata.domain.exception.RejectedOrderCannotBeApprovedException;

public class RejectedOrderStatus implements OrderStatus {
    @Override
    public OrderStatus reject() {
        return this;
    }

    @Override
    public OrderStatus approve() {
        throw new RejectedOrderCannotBeApprovedException();
    }

    @Override
    public OrderStatus ship() {
        throw new OrderCannotBeShippedException();
    }
}
