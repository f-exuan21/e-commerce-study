package kr.hhplus.be.server.ecommerce.adapter.out.jpa;

import kr.hhplus.be.server.ecommerce.adapter.out.jpa.mapper.OrderMapper;
import kr.hhplus.be.server.ecommerce.application.port.out.SaveOrderPort;
import kr.hhplus.be.server.ecommerce.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderJpaAdaptor implements SaveOrderPort {

    private final JpaOrderRepository orderRepository;

    public OrderJpaAdaptor(JpaOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long save(Order order) {
        var entity = OrderMapper.toEntity(order);
        var saved = orderRepository.save(entity);
        return saved.getId();
    }

}
