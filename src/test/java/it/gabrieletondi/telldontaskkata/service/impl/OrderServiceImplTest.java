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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
        Order initialOrder = new Order(1, "EUR");
        doReturn(initialOrder).when(orderRepository).getById(anyInt());
    }

    @Test
    public void approvedExistingOrderTest() throws Exception {

        orderService.approve(1);
    }

    @Test
    public void rejectedExistingOrderTest() throws Exception {
        orderService.reject(1);
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
        Order shippableOrder = new Order(1,new ArrayList<>(),new ApprovedOrderStatus(),"EUR");
        doReturn(shippableOrder).when(orderRepository).getById(anyInt());

        orderService.ship(1);
    }
}