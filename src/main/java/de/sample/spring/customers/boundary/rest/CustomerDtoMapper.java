package de.sample.spring.customers.boundary.rest;

import de.sample.spring.customers.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {

    CustomerDto map(Customer customer);
    Customer map(CustomerDto customer);

}
