package de.sample.spring.customers.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "Customer")
@Getter
@Setter
@ToString
@Table(name = "CUSTOMERS")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Size(min = 3)
    // @Column(name = "cust_name")
    private String name;

    private LocalDate birthdate;

}
