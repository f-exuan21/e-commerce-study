package kr.hhplus.be.server.ecommerce.application.service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.ecommerce.application.port.dto.ProductQueryResult;
import kr.hhplus.be.server.ecommerce.application.port.in.ProductQueryUseCase;
import kr.hhplus.be.server.ecommerce.application.port.out.LoadProductPort;
import kr.hhplus.be.server.ecommerce.domain.model.product.Product;

import java.util.List;

@Transactional
public class ProductQueryService implements ProductQueryUseCase {

    private final LoadProductPort loadProductPort;

    public ProductQueryService(LoadProductPort loadProductPort) {
        this.loadProductPort = loadProductPort;
    }

    @Override
    public List<ProductQueryResult> searchProductsByName(String keyword) {
         return convert(loadProductPort.findProductsByNameContaining(keyword));
    }

    private List<ProductQueryResult> convert(List<Product> products) {
        return products.stream()
                .map(p -> new ProductQueryResult(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getStock()
                )).toList();
    }
}
