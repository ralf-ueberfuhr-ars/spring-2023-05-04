package de.sample.spring.customers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Customer {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID uuid;
    private String name;
    private LocalDate birthdate;

}
