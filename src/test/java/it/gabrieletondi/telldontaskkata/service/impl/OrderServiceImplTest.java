package it.gabrieletondi.telldontaskkata.service.impl;

import it.gabrieletondi.telldontaskkata.domain.model.Category;
import it.gabrieletondi.telldontaskkata.domain.model.Product;
import it.gabrieletondi.telldontaskkata.domain.model.order.Order;
import it.gabrieletondi.telldontaskkata.domain.model.order.OrderItem;
import it.gabrieletondi.telldontaskkata.domain.model.order.status.ApprovedOrderStatus;
import it.gabrieletondi.telldontaskkata.domain.service.impl.OrderServiceImpl;
import it.gabrieletondi.telldontaskkata.repository.OrderRepository;
import it.gabrieletondi.telldontaskkata.domain.service.ShipmentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ShipmentService shipmentService;

    @Before
    public void init() {
        Order initialOrder = new Order(UUID.randomUUID().toString(), "EUR");
        doReturn(initialOrder).when(orderRepository).getById(anyString());
    }

    @Test
    public void approvedExistingOrderTest() throws Exception {

        orderService.approve(UUID.randomUUID().toString());
    }

    @Test
    public void rejectedExistingOrderTest() throws Exception {
        orderService.reject(UUID.randomUUID().toString());
    }

    @Test
    public void createOrderTest() {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem(new Product("product", BigDecimal.ONE,new Category("food",BigDecimal.valueOf(10))), 2));

        Order order = orderService.create(orderItems);

        assertNotNull(order);
        assertFalse(order.getItems().isEmpty());
    }

    @Test
    public void shipOrderTest() {
        Order shippableOrder = new Order(UUID.randomUUID().toString(),new ArrayList<>(),new ApprovedOrderStatus(),"EUR");
        doReturn(shippableOrder).when(orderRepository).getById(anyString());

        orderService.ship(UUID.randomUUID().toString());
    }
}