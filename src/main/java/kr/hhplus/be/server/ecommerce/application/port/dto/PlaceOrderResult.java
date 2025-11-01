package kr.hhplus.be.server.ecommerce.application.port.dto;


import java.time.Instant;

public record PlaceOrderResult(
        long orderId,
        long totalAmount,
        Instant orderAt
) {
}
