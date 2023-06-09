package de.sample.spring.customers.domain;

import de.sample.spring.customers.domain.aspects.PublishEvent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Validated
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerSink sink;

    public Collection<Customer> findAll() {
        return sink.findAll();
    }

    public Optional<Customer> findById(@NotNull UUID id) {
        return sink.findById(id);
    }

    @PublishEvent(NewCustomerEvent.class)
    public void create(@Valid Customer customer) {
        sink.create(customer);
    }

    public boolean delete(UUID id) {
        return sink.delete(id);
    }
}
