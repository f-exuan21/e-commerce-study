package kr.hhplus.be.server.ecommerce.application.service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.ecommerce.application.port.dto.ProductQueryResult;
import kr.hhplus.be.server.ecommerce.application.port.in.CreateProductUseCase;
import kr.hhplus.be.server.ecommerce.application.port.out.SaveProductPort;
import kr.hhplus.be.server.ecommerce.domain.model.money.Money;
import kr.hhplus.be.server.ecommerce.domain.model.product.Product;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements CreateProductUseCase {

    private final SaveProductPort saveProductPort;

    public CreateProductService(SaveProductPort saveProductPort) {
        this.saveProductPort = saveProductPort;
    }

    @Override
    @Transactional
    public ProductQueryResult create(CreateProductCommand command) {
        Product toSave = new Product(null, command.productName(), Money.of(command.price()), command.stock());

        Product saved = saveProductPort.saveNew(toSave);

        return new ProductQueryResult(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getStock()
        );
    }
}
