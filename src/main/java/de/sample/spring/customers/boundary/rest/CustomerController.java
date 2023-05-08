package de.sample.spring.customers.boundary.rest;

import de.sample.spring.customers.boundary.NotFoundException;
import de.sample.spring.customers.domain.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("api/v1/customers")
@Tag(name = "customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;
    private final CustomerDtoMapper mapper;

    @GetMapping(
      produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Read all customers")
    @ApiResponse(
      responseCode = "200",
      description = "Customers were read successfully."
    )
    public Collection<CustomerDto> findAllCustomers() {
        return service.findAll()
          .stream()
          .map(mapper::map)
          .collect(Collectors.toList());
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
    public CustomerDto findCustomer(
      @PathVariable("id")
      @Parameter(
        description = "The unique identifier",
        example = "8fd1c43c-3aa6-47e3-a1c6-bda0d7f32409"
      )
      UUID id
    ) {
        return service.findById(id)
          .map(mapper::map)
          .orElseThrow(NotFoundException::new);
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
    public ResponseEntity<CustomerDto> createCustomer(
      @Valid
      @RequestBody
      CustomerDto newCustomerDto
    ) {
        var customer = mapper.map(newCustomerDto);
        service.create(customer);
        var newId = customer.getUuid();
        var responseDto = mapper.map(customer);
        //customers.put(newId, newCustomer);
        URI location = linkTo(
          methodOn(CustomerController.class).findCustomer(newId)
        ).toUri();
        //URI location = URI.create("http://localhost:8082/api/v1/customers/" + newId);
        return ResponseEntity.created(location).body(responseDto);
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
        service.delete(id);
    }

}
