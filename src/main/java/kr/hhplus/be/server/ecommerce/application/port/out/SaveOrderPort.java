package kr.hhplus.be.server.ecommerce.application.port.out;

import kr.hhplus.be.server.ecommerce.domain.model.Order;

public interface SaveOrderPort {
    
    Long save(Order order);
    
}
