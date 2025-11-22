package kr.hhplus.be.server.ecommerce.application.port.out;

import kr.hhplus.be.server.ecommerce.domain.model.money.Money;
import kr.hhplus.be.server.ecommerce.domain.model.product.Product;

import java.util.List;

public interface LoadProductPort {

    Money getUnitPrice(Long productId);
    int getStock(Long productId);
    List<Product> findProductsByNameContaining(String keyword);
}
