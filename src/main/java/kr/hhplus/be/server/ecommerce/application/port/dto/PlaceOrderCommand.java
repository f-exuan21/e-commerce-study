package kr.hhplus.be.server.ecommerce.application.port.dto;

import kr.hhplus.be.server.ecommerce.domain.model.OrderItem;

import java.util.List;

public record PlaceOrderCommand(
     long userId,
     List<Item> orderItems,
     String shippingAddress
) {

    public record Item(long productId, int quantity) {}

}
