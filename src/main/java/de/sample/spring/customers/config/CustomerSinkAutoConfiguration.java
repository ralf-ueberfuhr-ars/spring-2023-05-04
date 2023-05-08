package de.sample.spring.customers.config;

import de.sample.spring.customers.domain.CustomerSink;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerSinkAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    CustomerSink customerSinkFallback() {
        return new InMemoryCustomerSink();
    }

}
