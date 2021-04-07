package it.gabrieletondi.telldontaskkata.domain.service.impl;

import it.gabrieletondi.telldontaskkata.domain.exception.UnknownProductException;
import it.gabrieletondi.telldontaskkata.domain.model.Category;
import it.gabrieletondi.telldontaskkata.domain.model.Product;
import it.gabrieletondi.telldontaskkata.repository.ProductCatalog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductCatalog productCatalog;


    @Before
    public void setUp() throws Exception {
        doReturn(new Product("someProduct", BigDecimal.TEN,
                new Category("someCategory", BigDecimal.valueOf(.07))))
                .when(productCatalog).getByName(anyString());
    }

    @Test
    public void getProductByNameTest() {
        Product product = productService.getProduct("someProduct");

        assertNotNull(product);
    }

    @Test(expected = UnknownProductException.class)
    public void getUnknownProductTest() {
        doReturn(null).when(productCatalog).getByName(anyString());
        productService.getProduct("dfqwer23");
    }
}