package it.gabrieletondi.telldontaskkata.domain.order;


import it.gabrieletondi.telldontaskkata.domain.exception.*;
import it.gabrieletondi.telldontaskkata.domain.model.order.Order;
import it.gabrieletondi.telldontaskkata.domain.model.order.status.ApprovedOrderStatus;
import it.gabrieletondi.telldontaskkata.domain.model.order.status.OrderStatusType;
import it.gabrieletondi.telldontaskkata.domain.model.order.status.RejectedOrderStatus;
import it.gabrieletondi.telldontaskkata.domain.model.order.status.ShippedOrderStatus;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class OrderTest {


    @Test
    public void approveNewOrderTest() {
        Order order = new Order(UUID.randomUUID().toString(), "EUR");

        order.approve();

        assertEquals(OrderStatusType.APPROVED, order.getStatusType());
    }

    @Test
    public void rejectNewOrderTest() {
        Order order = new Order(UUID.randomUUID().toString(), "EUR");

        order.reject();

        assertEquals(OrderStatusType.REJECTED, order.getStatusType());
    }

    @Test
    public void shipApprovedOrderTest() {
        Order approvedOrder = new Order(UUID.randomUUID().toString(), new ArrayList<>(), new ApprovedOrderStatus(), "EUR");

        approvedOrder.ship();

        assertEquals(OrderStatusType.SHIPPED, approvedOrder.getStatusType());
    }

    @Test(expected = RejectedOrderCannotBeApprovedException.class)
    public void approveRejectedOrderTest() {
        Order rejectedOrder = new Order(UUID.randomUUID().toString(), new ArrayList<>(), new RejectedOrderStatus(), "EUR");

        rejectedOrder.approve();
    }

    @Test(expected = ApprovedOrderCannotBeRejectedException.class)
    public void rejectApprovedOrderTest() {
        Order approvedOrder = new Order(UUID.randomUUID().toString(), new ArrayList<>(), new ApprovedOrderStatus(), "EUR");

        approvedOrder.reject();
    }

    @Test(expected = OrderShipmentException.class)
    public void shipRejectedOrderTest() {
        Order rejectedOrder = new Order(UUID.randomUUID().toString(), new ArrayList<>(), new RejectedOrderStatus(), "EUR");

        rejectedOrder.ship();
    }

    @Test(expected = OrderShipmentException.class)
    public void shipShippedOrderTest() {
        Order shippedOrder = new Order(UUID.randomUUID().toString(), new ArrayList<>(), new ShippedOrderStatus(), "EUR");

        shippedOrder.ship();
    }


}