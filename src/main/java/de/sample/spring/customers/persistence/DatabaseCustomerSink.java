package de.sample.spring.customers.persistence;

import de.sample.spring.customers.domain.Customer;
import de.sample.spring.customers.domain.CustomerSink;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Profile("!nodb")
@RequiredArgsConstructor
public class DatabaseCustomerSink implements CustomerSink {

    private final CustomerRepository repo;
    private final CustomerEntityMapper mapper;

    @Override
    public Collection<Customer> findAll() {
        return repo.findAll()
          .stream()
          .map(this.mapper::map)
          .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return repo.findById(id)
          .map(this.mapper::map);
    }

    @Override
    public void create(Customer customer) {
        final var entity = this.mapper.map(customer);
        final var result = repo.save(entity);
        //customer.setUuid(result.getUuid());
        this.mapper.copy(result, customer);
    }

    @Override
    public void delete(UUID id) {
        // repo.existsById(id)
        repo.deleteById(id);
    }
}
