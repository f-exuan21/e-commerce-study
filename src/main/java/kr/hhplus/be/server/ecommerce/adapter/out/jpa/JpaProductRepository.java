package kr.hhplus.be.server.ecommerce.adapter.out.jpa;

import kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameContaining(String keyword);

    @Query("select p.stock from ProductEntity p where p.id = :id")
    Integer getStockById(@Param("id") Long id);

    @Query("select p.price from ProductEntity p where p.id = :id")
    Long getPriceById(@Param("id") Long id);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update ProductEntity p set p.stock = p.stock - :delta where p.id = :id and p.stock >= :delta")
    int decreaseStockById(@Param("id") Long id, @Param("delta") int delta);

}
