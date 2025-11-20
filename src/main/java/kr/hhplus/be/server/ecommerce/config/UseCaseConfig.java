package kr.hhplus.be.server.ecommerce.config;

import kr.hhplus.be.server.ecommerce.application.port.in.PlaceOrderUseCase;
import kr.hhplus.be.server.ecommerce.application.port.in.ProductQueryUseCase;
import kr.hhplus.be.server.ecommerce.application.port.out.LoadProductPort;
import kr.hhplus.be.server.ecommerce.application.port.out.PaymentPort;
import kr.hhplus.be.server.ecommerce.application.port.out.SaveOrderPort;
import kr.hhplus.be.server.ecommerce.application.port.out.TakeOutProductPort;
import kr.hhplus.be.server.ecommerce.application.service.PlaceOrderService;
import kr.hhplus.be.server.ecommerce.application.service.ProductQueryService;
import kr.hhplus.be.server.ecommerce.domain.policy.impl.DefaultPricingPolicy;
import kr.hhplus.be.server.ecommerce.domain.policy.PricingPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public PricingPolicy pricingPolicy() {
        return new DefaultPricingPolicy();
    }

    @Bean
    public PlaceOrderUseCase placeOrderService(LoadProductPort loadProductPort, SaveOrderPort saveOrderPort, PricingPolicy pricingPolicy, TakeOutProductPort takeOutProductPort, PaymentPort paymentPort) {
        return new PlaceOrderService(loadProductPort, saveOrderPort, pricingPolicy, takeOutProductPort, paymentPort);
    }

    @Bean
    public ProductQueryUseCase productQueryService(LoadProductPort loadProductPort) {
        return new ProductQueryService(loadProductPort);
    }


}
