package it.gabrieletondi.telldontaskkata.domain.order.status;

import it.gabrieletondi.telldontaskkata.domain.order.Order;

public interface OrderStatus {

     OrderStatus reject();
     
     OrderStatus approve();
     
     OrderStatus ship();
}
