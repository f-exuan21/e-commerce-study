package kr.hhplus.be.server.ecommerce.adapter.out.jpa;

import kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaPointRepository extends JpaRepository<PointEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Query("""
        update PointEntity p
           set p.point = p.point + :delta
         where p.userId = :userId
    """)
    int increase(@Param("userId") Long userId, @Param("delta") int delta);

    @Modifying(clearAutomatically = true)
    @Query("""
        update PointEntity p
           set p.point = p.point - :delta
         where p.userId = :userId
           and p.point >= :delta
    """)
    int decreaseIfEnough(@Param("userId") Long userId, @Param("delta") int delta);
}
