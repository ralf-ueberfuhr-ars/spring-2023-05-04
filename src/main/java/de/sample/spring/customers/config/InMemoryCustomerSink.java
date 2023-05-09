package de.sample.spring.customers.config;

import de.sample.spring.customers.domain.Customer;
import de.sample.spring.customers.domain.CustomerSink;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

class InMemoryCustomerSink implements CustomerSink {

    private final Map<UUID, Customer> customers = new HashMap<>();

    @Override
    public Collection<Customer> findAll() {
        return customers.values();
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return Optional.ofNullable(customers.get(id));
    }

    @Override
    public void create(Customer customer) {
        var newId = UUID.randomUUID();
        customer.setUuid(newId);
        customers.put(newId, customer);
    }

    @Override
    public boolean delete(UUID id) {
        return null != customers.remove(id);
    }
}
