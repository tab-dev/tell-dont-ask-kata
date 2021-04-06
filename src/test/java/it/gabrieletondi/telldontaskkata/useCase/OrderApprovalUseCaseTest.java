package it.gabrieletondi.telldontaskkata.useCase;

import it.gabrieletondi.telldontaskkata.domain.exception.ApprovedOrderCannotBeRejectedException;
import it.gabrieletondi.telldontaskkata.domain.exception.RejectedOrderCannotBeApprovedException;
import it.gabrieletondi.telldontaskkata.domain.exception.ShippedOrdersCannotBeChangedException;
import it.gabrieletondi.telldontaskkata.domain.order.Order;
import it.gabrieletondi.telldontaskkata.domain.order.status.OrderStatusType;
import it.gabrieletondi.telldontaskkata.doubles.TestOrderRepository;
import it.gabrieletondi.telldontaskkata.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OrderApprovalUseCaseTest {
    @InjectMocks
    private OrderApprovalUseCase useCase;

    @Mock
    private OrderService orderService;

    @Test
    public void approvedExistingOrder() throws Exception {
        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(true);

        useCase.run(request);
    }

    @Test
    public void rejectedExistingOrder() throws Exception {
        OrderApprovalRequest request = new OrderApprovalRequest();
        request.setOrderId(1);
        request.setApproved(false);

        useCase.run(request);
    }

}
