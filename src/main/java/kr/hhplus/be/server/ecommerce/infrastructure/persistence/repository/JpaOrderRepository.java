package kr.hhplus.be.server.ecommerce.infrastructure.persistence.repository;

import kr.hhplus.be.server.ecommerce.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {



}
