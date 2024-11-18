package Ults.common.command;

import Ults.common.command.domain.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class OrderCreationController extends AbstractNioController {
    private final OrderCreationFacade orderCreationFacade;

    @Inject
    public OrderCreationController(OrderCreationFacade orderCreationFacade) {
        this.orderCreationFacade = orderCreationFacade;
    }

    @PreAuthorize("#oauth2.hasScope('write') and hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/api/v1/orders", method = RequestMethod.POST)
    public DeferredResult<ResponseEntity<Void>> createOrder(@RequestBody OrderRequest orderRequest) {
        return executeDeferred(orderCreationFacade.createOrder(orderRequest));
    }

}
