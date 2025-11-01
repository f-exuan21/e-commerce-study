package kr.hhplus.be.server.ecommerce.adapter.in.web;

import kr.hhplus.be.server.ecommerce.adapter.in.web.dto.ProductResponses;
import kr.hhplus.be.server.ecommerce.application.port.dto.ProductQueryResult;
import kr.hhplus.be.server.ecommerce.application.port.in.ProductQueryUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductQueryController {

    private final ProductQueryUseCase productQueryUseCase;

    public ProductQueryController(ProductQueryUseCase productQueryUseCase) {
        this.productQueryUseCase = productQueryUseCase;
    }

    /**
     * 상품명으로 검색
     * GET /v1/product/search?name={name}
     * @param name
     * @return List<ProductQueryResult>
     */
    @GetMapping("/search")
    public ProductResponses searchByName(@RequestParam String name) {
        String keyword = name.trim();

        List<ProductQueryResult> results = productQueryUseCase.searchProductsByName(keyword);

        List<ProductResponses.ProductResponseItem> items = results.stream()
                .map(r -> new ProductResponses.ProductResponseItem(
                        r.productId(),
                        r.productName(),
                        r.price().toLong(),
                        r.stock()
                )).toList();

        return ProductResponses.of(items);
    }
}
