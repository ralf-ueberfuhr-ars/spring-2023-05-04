package de.sample.spring.customers.domain;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Customer {

    private UUID uuid;

    @Size(min = 3)
    private String name;

    private LocalDate birthdate;

}
