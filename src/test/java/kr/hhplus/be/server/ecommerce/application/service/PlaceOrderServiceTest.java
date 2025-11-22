package kr.hhplus.be.server.ecommerce.application.service;

import kr.hhplus.be.server.ecommerce.application.port.dto.PlaceOrderCommand;
import kr.hhplus.be.server.ecommerce.application.port.dto.PlaceOrderResult;
import kr.hhplus.be.server.ecommerce.application.port.in.PlaceOrderUseCase;
import kr.hhplus.be.server.ecommerce.application.port.out.LoadProductPort;
import kr.hhplus.be.server.ecommerce.application.port.out.PaymentPort;
import kr.hhplus.be.server.ecommerce.application.port.out.SaveOrderPort;
import kr.hhplus.be.server.ecommerce.application.port.out.TakeOutProductPort;
import kr.hhplus.be.server.ecommerce.domain.exception.OutOfStockException;
import kr.hhplus.be.server.ecommerce.domain.model.money.Money;
import kr.hhplus.be.server.ecommerce.domain.policy.PricingPolicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlaceOrderServiceTest {

    @Mock
    LoadProductPort loadProductPort;

    @Mock
    PaymentPort paymentPort;

    @Mock
    SaveOrderPort saveOrderPort;

    @Mock
    TakeOutProductPort takeOutProductPort;

    @Mock
    PricingPolicy pricingPolicy;

    PlaceOrderUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new PlaceOrderService(loadProductPort, saveOrderPort, pricingPolicy, takeOutProductPort, paymentPort);
    }

    private PlaceOrderCommand makeCmd() {
        return new PlaceOrderCommand(
                1L,
                List.of(
                        new PlaceOrderCommand.Item(1L, 100),
                        new PlaceOrderCommand.Item(2L, 100)
                ),
                "서울시 XXX"
        );
    }

    @Test
    @DisplayName("성공 : 재고 확인 -> 출고 -> 결제 -> 저장 호출 및 결과 검증")
    void placeOrder_success() {
        var cmd = makeCmd();

        // given
        // 단가
        when(loadProductPort.getUnitPrice(1L)).thenReturn(Money.of(10000));
        when(loadProductPort.getUnitPrice(2L)).thenReturn(Money.of(20000));

        // 재고
        when(loadProductPort.getStock(1L)).thenReturn(200);
        when(loadProductPort.getStock(2L)).thenReturn(200);

        // 총액 계산
        when(pricingPolicy.calculateTotal(anyList()))
                .thenReturn(Money.of(30_000L));

        // when
        PlaceOrderResult result = useCase.placeOrder(cmd);

        // then
        assertThat(result.totalAmount()).isEqualTo(30_000L);
        assertThat(result.orderAt()).isNotNull();

        verify(loadProductPort).getStock(1L);
        verify(loadProductPort).getStock(2L);
        verify(loadProductPort).getUnitPrice(1L);
        verify(loadProductPort).getUnitPrice(2L);
        verify(pricingPolicy).calculateTotal(anyList());
        verify(takeOutProductPort).takeOut(1L, 100);
        verify(takeOutProductPort).takeOut(2L, 100);
        verify(paymentPort).pay(1L, Money.of(30_000L));
        verify(saveOrderPort).save(any());

    }

    @Test
    @DisplayName("실패 : 재고 부족")
    void placeOrder_fail_stock_not_enough() {

        var cmd = makeCmd();

        // given
        // 단가
        when(loadProductPort.getUnitPrice(1L)).thenReturn(Money.of(10000));

        // 재고
        when(loadProductPort.getStock(1L)).thenReturn(10);


        // when / then
        assertThatThrownBy(() -> useCase.placeOrder(cmd))
                .isInstanceOf(OutOfStockException.class);

        // 호출되지 않아야 함
        verify(takeOutProductPort, never()).takeOut(anyLong(), anyInt());
        verify(paymentPort, never()).pay(anyLong(), any());
        verify(saveOrderPort, never()).save(any());

    }

}