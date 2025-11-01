package kr.hhplus.be.server.ecommerce.application.port.in;


import kr.hhplus.be.server.ecommerce.application.port.dto.ProductQueryResult;

import java.util.List;

public interface ProductQueryUseCase {

    // 상품조회
    List<ProductQueryResult> searchProductsByName(String keyword);

}
