package de.sample.spring.customers.persistence;

import de.sample.spring.customers.domain.Customer;
import de.sample.spring.customers.domain.CustomerSink;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

//@Component
public class DatabaseCustomerSink implements CustomerSink {
    @Override
    public Collection<Customer> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Customer customer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(UUID id) {
        throw new UnsupportedOperationException();
    }
}
