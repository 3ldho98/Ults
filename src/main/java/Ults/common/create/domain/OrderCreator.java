package Ults.common.create.domain;

import Ults.common.command.domain.OrderRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@FunctionalInterface
public interface OrderCreator {
    void create();

    class UltsOrderCreator implements OrderCreator {
        private final Order order;

        public UltsOrderCreator(final Order order) {
            this.order = order;
        }

        @Override
        public void create() {
            final UUID uniqueID = UUID.randomUUID();
            final OrderRequest request = get(OrderRequest.class);
            final Integer quantity = request.getQuantity();
            final BigDecimal unitPrice = request.getUnitPrice();
            final OrderDao dao = get(OrderDao.class);

            final BigDecimal totalCost = unitPrice.multiply(BigDecimal.valueOf(quantity));
            order.setStatus(OrderStatus.CREATED);
            order.setTotalAmount(totalCost);
            order.setCreatedAt(LocalDateTime.now());
            order.setOrderNumber(String.valueOf(uniqueID));

            order.getItems(request.getIteams());
            dao.save(order);
        }
    }

}
