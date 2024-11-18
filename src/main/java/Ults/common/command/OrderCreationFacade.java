package Ults.common.command;

import Ults.common.command.domain.OrderRequest;
import Ults.common.create.OrderCreateContext;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class OrderCreationFacade extends AbstractDciFacade {
    @Inject
    public OrderCreationFacade(final ContextFactoryProvider factoryProvider,
                      final AuthenticatedUserProvider userProvider) {
        super(factoryProvider, userProvider);
    }

    public Supplier<Void> createOrder(final OrderRequest request) {
        return DeferredContext.create(OrderCreateContext.class)
                .withFactoryProvider(factoryProvider())
                .withUser(user())
                .transactionManager("orderCreationProvider")
                .put(request);
    }

}
