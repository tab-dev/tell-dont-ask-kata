package it.gabrieletondi.telldontaskkata.domain.order.status;

public interface OrderStatus {

     void reject();
     
     void approve();
     
     void ship();
}
