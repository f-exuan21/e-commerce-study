package kr.hhplus.be.server.ecommerce.infrastructure.persistence.mapper;

import kr.hhplus.be.server.ecommerce.infrastructure.persistence.entity.OrderEntity;
import kr.hhplus.be.server.ecommerce.infrastructure.persistence.entity.OrderItemEntity;
import kr.hhplus.be.server.ecommerce.domain.model.order.Order;

public final class OrderMapper {

    private OrderMapper() {

    }

    public static OrderEntity toEntity(Order order) {

        var orderEntity = new OrderEntity(
                order.getUserId(),
                order.getShippingAddress(),
                order.getTotalPrice().value().longValue(),
                order.getOrderDate()
        );

        for (var it : order.getOrderItems()) {
            var orderItemEntity = new OrderItemEntity(
                    it.productId(),
                    it.quantity(),
                    it.unitPrice()
            );
            orderEntity.addItem(orderItemEntity);
        }

        return orderEntity;

    }

}
