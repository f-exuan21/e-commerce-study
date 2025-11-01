package kr.hhplus.be.server.ecommerce.application.service;

import kr.hhplus.be.server.ecommerce.application.port.dto.ProductQueryResult;
import kr.hhplus.be.server.ecommerce.application.port.in.ProductQueryUseCase;
import kr.hhplus.be.server.ecommerce.application.port.out.LoadProductPort;
import kr.hhplus.be.server.ecommerce.domain.model.Money;
import kr.hhplus.be.server.ecommerce.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductQueryServiceTest {

    @Mock
    LoadProductPort loadProductPort;

    ProductQueryUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ProductQueryService(loadProductPort);
    }

    @Test
    @DisplayName("성공 : 이름으로 검색 시 keyword를 넘겨 결과를 매핑")
    void searchProductsByName_success() {
        // given
        var p = new Product(3L, "배 5kg", Money.of(41200L), 30);
        when(loadProductPort.findProductsByNameContaining("배"))
                .thenReturn(List.of(p));

        // when
        List<ProductQueryResult> results = useCase.searchProductsByName("배");

        // then
        assertThat(results).hasSize(1);
        assertThat(results.get(0).productId()).isEqualTo(p.getId());
        assertThat(results.get(0).productName()).isEqualTo(p.getName());
        assertThat(results.get(0).price()).isEqualTo(p.getPrice());
        assertThat(results.get(0).stock()).isEqualTo(p.getStock());
    }

}
