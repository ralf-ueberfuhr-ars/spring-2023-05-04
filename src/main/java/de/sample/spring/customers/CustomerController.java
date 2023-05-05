package de.sample.spring.customers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {

    // TODO replace later
    private final Map<UUID, Customer> customers = new HashMap<>();

    @GetMapping
    public Collection<Customer> findAllCustomers() {
        return customers.values();
    }

    @GetMapping("/{id}")
    public Customer findCustomer(@PathVariable("id") UUID id) {
        return customers.get(id);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
        var newId = UUID.randomUUID();
        newCustomer.setUuid(newId);
        customers.put(newId, newCustomer);
        URI location = linkTo(
          methodOn(CustomerController.class).findCustomer(newId)
        ).toUri();
        //URI location = URI.create("http://localhost:8082/api/v1/customers/" + newId);
        return ResponseEntity.created(location).body(newCustomer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable("id") UUID id) {
        customers.remove(id);
    }

}
