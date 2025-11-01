package kr.hhplus.be.server.ecommerce.application.port.dto;

import kr.hhplus.be.server.ecommerce.domain.model.Money;

public record ProductQueryResult(
        Long productId,
        String productName,
        Money price,
        int stock
) {
}
