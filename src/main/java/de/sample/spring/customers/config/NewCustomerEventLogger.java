package de.sample.spring.customers.config;

import de.sample.spring.customers.domain.NewCustomerEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@Slf4j
public class NewCustomerEventLogger {

    @EventListener(NewCustomerEvent.class)
    public void customerCreated(NewCustomerEvent event) {
        log.info("Customer created: " + event.customer().getUuid());
    }

}
