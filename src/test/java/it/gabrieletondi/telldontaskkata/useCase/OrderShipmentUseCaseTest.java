package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.exception.OrderCannotBeShippedException;
import it.gabrieletondi.telldontaskkata.domain.exception.OrderCannotBeShippedTwiceException;
import it.gabrieletondi.telldontaskkata.domain.order.Order;
import it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import it.gabrieletondi.telldontaskkata.doubles.TestShipmentService;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class OrderShipmentUseCaseTest {
    private final TestOrderRepository orderRepository = new TestOrderRepository();
    private final TestShipmentService shipmentService = new TestShipmentService();
    private final OrderShipmentUseCase useCase = new OrderShipmentUseCase(orderRepository, shipmentService);

    @Test
    public void shipApprovedOrder() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setId(1);
        initialOrder.setStatusType(OrderStatusType.APPROVED);
        orderRepository.addOrder(initialOrder);

        OrderShipmentRequest request = new OrderShipmentRequest();
        request.setOrderId(1);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder().getStatusType(), is(OrderStatusType.SHIPPED));
        assertThat(shipmentService.getShippedOrder(), is(initialOrder));
    }

    @Test(expected = OrderCannotBeShippedException.class)
    public void createdOrdersCannotBeShipped() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setId(1);
        initialOrder.setStatusType(OrderStatusType.CREATED);
        orderRepository.addOrder(initialOrder);

        OrderShipmentRequest request = new OrderShipmentRequest();
        request.setOrderId(1);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
        assertThat(shipmentService.getShippedOrder(), is(nullValue()));
    }

    @Test(expected = OrderCannotBeShippedException.class)
    public void rejectedOrdersCannotBeShipped() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setId(1);
        initialOrder.setStatusType(OrderStatusType.REJECTED);
        orderRepository.addOrder(initialOrder);

        OrderShipmentRequest request = new OrderShipmentRequest();
        request.setOrderId(1);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
        assertThat(shipmentService.getShippedOrder(), is(nullValue()));
    }

    @Test(expected = OrderCannotBeShippedTwiceException.class)
    public void shippedOrdersCannotBeShippedAgain() throws Exception {
        Order initialOrder = new Order();
        initialOrder.setId(1);
        initialOrder.setStatusType(OrderStatusType.SHIPPED);
        orderRepository.addOrder(initialOrder);

        OrderShipmentRequest request = new OrderShipmentRequest();
        request.setOrderId(1);

        useCase.run(request);

        assertThat(orderRepository.getSavedOrder(), is(nullValue()));
        assertThat(shipmentService.getShippedOrder(), is(nullValue()));
    }
}
