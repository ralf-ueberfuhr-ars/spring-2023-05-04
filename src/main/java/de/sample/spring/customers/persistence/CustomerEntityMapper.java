package de.sample.spring.customers.persistence;

import de.sample.spring.customers.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntity map(Customer source);

    Customer map(CustomerEntity source);

    void copy(CustomerEntity entity, @MappingTarget Customer customer);

}
