package de.sample.spring.customers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@Tag(name = "customers")
public class CustomerController {

    // TODO replace later
    private final Map<UUID, Customer> customers = new HashMap<>();

    @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Read all customers")
    @ApiResponse(
      responseCode = "200",
      description = "Customers were read successfully."
    )
    public Collection<Customer> findAllCustomers() {
        return customers.values();
    }

    @GetMapping(
      value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Read a single customers")
    @ApiResponse(
      responseCode = "200",
      description = "Customer was found."
    )
    @ApiResponse(
      responseCode = "404",
      description = "Customer could not be found.",
      content = @Content(schema = @Schema)
    )
    public Customer findCustomer(
      @PathVariable("id")
      @Parameter(
        description = "The unique identifier",
        example = "8fd1c43c-3aa6-47e3-a1c6-bda0d7f32409"
      )
      UUID id
    ) {
        final var result =  customers.get(id);
        if(result != null) {
            return result;
        } else {
            throw new NotFoundException();
        }
    }

    @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    /*
     * Beschreibungen
     * Statuscode 201, Location Header
     * Statuscode 400
     */
    @Operation(summary = "Create a customer")
    @ApiResponse(
      responseCode = "201",
      description = "Customer was created successfully",
      headers = @Header(
        name = "Location",
        description = "URL to the newly created customer"
      )
    )
    @ApiResponse(
      responseCode = "400",
      description = "The customer is invalid.",
      content = @Content(schema = @Schema)
    )
    public ResponseEntity<Customer> createCustomer(
      @Valid
      @RequestBody
      Customer newCustomer
    ) {
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
    @Operation(summary = "Delete a single customers")
    @ApiResponse(
      responseCode = "204",
      description = "Customer was deleted successfully."
    )
    @ApiResponse(
      responseCode = "404",
      description = "Customer could not be found."
    )
    public void deleteCustomer(
      @PathVariable("id")
      @Parameter(
        description = "The unique identifier",
        example = "8fd1c43c-3aa6-47e3-a1c6-bda0d7f32409"
      )
      UUID id
    ) {
        customers.remove(id);
    }

}
