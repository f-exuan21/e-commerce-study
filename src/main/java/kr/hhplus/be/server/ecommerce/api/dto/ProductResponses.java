package kr.hhplus.be.server.ecommerce.api.dto;

import java.util.List;

public record ProductResponses(
        List<ProductResponseItem> products
) {

    public static ProductResponses of(List<ProductResponseItem> products) {
        return new ProductResponses(products);
    }

    public record ProductResponseItem(
            Long productId,
            String productName,
            Long price,
            Integer stock
    ) {}

}
