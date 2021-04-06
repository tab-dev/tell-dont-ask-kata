package it.gabrieletondi.telldontaskkata.controller;

import it.gabrieletondi.telldontaskkata.domain.model.Category;
import it.gabrieletondi.telldontaskkata.domain.model.Product;
import it.gabrieletondi.telldontaskkata.domain.exception.UnknownProductException;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import it.gabrieletondi.telldontaskkata.domain.service.OrderService;
import it.gabrieletondi.telldontaskkata.controller.model.SellItemRequest;
import it.gabrieletondi.telldontaskkata.controller.model.SellItemsRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderService orderService;

    @Mock
    private ProductCatalog productCatalog;

    @Before
    public void setUp() throws Exception {
        Category food = new Category("food", BigDecimal.valueOf(10));
        doReturn(new Product("salad",
                new BigDecimal("3.56"),
                food)).when(productCatalog).getByName("salad");
        doReturn(new Product("tomato",
                new BigDecimal("4.65"),
                food)).when(productCatalog).getByName("tomato");
    }

    @Test
    public void sellMultipleItems() throws Exception {
        SellItemRequest saladRequest = new SellItemRequest();
        saladRequest.setProductName("salad");
        saladRequest.setQuantity(2);

        SellItemRequest tomatoRequest = new SellItemRequest();
        tomatoRequest.setProductName("tomato");
        tomatoRequest.setQuantity(3);

        final SellItemsRequest request = new SellItemsRequest();
        request.setRequests(new ArrayList<>());
        request.getRequests().add(saladRequest);
        request.getRequests().add(tomatoRequest);

        orderController.create(request);

    }

    @Test(expected = UnknownProductException.class)
    public void unknownProduct() throws Exception {
        SellItemsRequest request = new SellItemsRequest();
        request.setRequests(new ArrayList<>());
        SellItemRequest unknownProductRequest = new SellItemRequest();
        unknownProductRequest.setProductName("unknown product");
        request.getRequests().add(unknownProductRequest);

        orderController.create(request);
    }

    @Test
    public void approvedExistingOrder() throws Exception {
        orderController.approve(1);
    }

    @Test
    public void rejectedExistingOrder() throws Exception {
        orderController.reject(1);
    }

    @Test
    public void shipApprovedOrder() throws Exception {
        orderController.ship(1);
    }
}