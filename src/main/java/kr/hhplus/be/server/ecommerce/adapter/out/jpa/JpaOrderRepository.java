package kr.hhplus.be.server.ecommerce.adapter.out.jpa;

import kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {



}
