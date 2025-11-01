package kr.hhplus.be.server.ecommerce.adapter.in.web;

import kr.hhplus.be.server.ecommerce.application.port.dto.ProductQueryResult;
import kr.hhplus.be.server.ecommerce.application.port.in.CreateProductUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin/product")
public class ProductAdminController {

    private final CreateProductUseCase createProductUseCase;

    public ProductAdminController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    public record CreateProductRequest(String productName, long price, int stock) {
    }

    @PostMapping
    public ProductQueryResult create(@RequestBody CreateProductRequest req) {
        var cmd = new CreateProductUseCase.CreateProductCommand(
                req.productName(),
                req.stock(),
                req.price()
        );
        return createProductUseCase.create(cmd);
    }
}
