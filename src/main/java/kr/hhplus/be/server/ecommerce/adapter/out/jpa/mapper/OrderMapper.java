package kr.hhplus.be.server.ecommerce.adapter.out.jpa.mapper;

import kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity.OrderEntity;
import kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity.OrderItemEntity;
import kr.hhplus.be.server.ecommerce.domain.model.Order;

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
