package de.sample.spring.customers;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class ActuatorConfiguration {

    @Bean
    HealthIndicator random() {
        return (
          ThreadLocalRandom.current().nextDouble() > 0.9 ? Health.down() : Health.up()
        )::build;
    }

}
