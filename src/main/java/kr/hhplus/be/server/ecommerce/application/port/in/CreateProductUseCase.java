package kr.hhplus.be.server.ecommerce.application.port.in;

import kr.hhplus.be.server.ecommerce.application.port.dto.ProductQueryResult;

public interface CreateProductUseCase {

    ProductQueryResult create(CreateProductCommand command);

    record CreateProductCommand(String productName, int stock, long price) {}

}
