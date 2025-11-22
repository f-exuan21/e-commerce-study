package kr.hhplus.be.server.ecommerce.infrastructure.persistence.adapter;

import kr.hhplus.be.server.ecommerce.infrastructure.persistence.entity.ProductEntity;
import kr.hhplus.be.server.ecommerce.application.port.out.LoadProductPort;
import kr.hhplus.be.server.ecommerce.application.port.out.SaveProductPort;
import kr.hhplus.be.server.ecommerce.application.port.out.TakeOutProductPort;
import kr.hhplus.be.server.ecommerce.domain.model.money.Money;
import kr.hhplus.be.server.ecommerce.infrastructure.persistence.repository.JpaProductRepository;
import kr.hhplus.be.server.ecommerce.domain.model.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductJpaAdapter implements LoadProductPort, TakeOutProductPort, SaveProductPort {

    private final JpaProductRepository repository;

    public ProductJpaAdapter(JpaProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product saveNew(Product product) {
        ProductEntity e = new ProductEntity(
                product.getName(),
                product.getPrice().toLong(),
                product.getStock()
        );
        ProductEntity saved = repository.save(e);
        return toDomain(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Money getUnitPrice(Long productId) {
        return Money.of(repository.getPriceById(productId));
    }

    @Override
    @Transactional(readOnly = true)
    public int getStock(Long productId) {
        return repository.getStockById(productId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findProductsByNameContaining(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return repository.findAll().stream().map(this::toDomain).toList();
        }
        return repository.findByNameContaining(keyword).stream().map(this::toDomain).toList();
    }

    @Override
    @Transactional
    public void takeOut(Long productId, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be greater than 0");

        repository.decreaseStockById(productId, quantity);
    }

    private Product toDomain(ProductEntity e) {
        return new Product(e.getId(), e.getName(), Money.of(e.getPrice()), e.getStock());
    }
}
