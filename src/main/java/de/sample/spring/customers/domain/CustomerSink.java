package de.sample.spring.customers.domain;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface CustomerSink {

    Collection<Customer> findAll();

    Optional<Customer> findById(UUID id);

    // Übergabe ohne ID, nach Aufruf ID enthalten
    void create(Customer customer);

    boolean delete(UUID id); // return true, if the id existed before

}
