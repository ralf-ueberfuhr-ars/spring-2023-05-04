package de.sample.spring.customers.boundary.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Schema(
  name="Customer",
  description = "Information about the customer."
)
public class CustomerDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(
      description = "The unique identifier."
    )
    private UUID uuid;

    @Schema(
      description = "The simple name.",
      example = "Tom Mayer"
    )
    @Size(min = 3)
    private String name;

    @Schema(
      description = "The date of birth."
    )
    private LocalDate birthdate;

}
