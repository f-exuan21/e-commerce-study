package kr.hhplus.be.server.ecommerce.infrastructure.persistence.repository;

import kr.hhplus.be.server.ecommerce.infrastructure.persistence.entity.PointHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPointHistoryRepository extends JpaRepository<PointHistoryEntity, Long> {



}
