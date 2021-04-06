package it.gabrieletondi.telldontaskkata.domain.model.order.status;

public interface OrderStatus {

     OrderStatusType getStatusType();

     OrderStatus reject();
     
     OrderStatus approve();
     
     OrderStatus ship();
}
