package kr.hhplus.be.server.ecommerce.application.port.out;

import kr.hhplus.be.server.ecommerce.domain.model.Product;

public interface SaveProductPort {

    Product saveNew(Product product);

}
