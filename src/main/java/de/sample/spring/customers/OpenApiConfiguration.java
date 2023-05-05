package de.sample.spring.customers;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    OpenAPI api() {
        return new OpenAPI()
          .info(
            new Info()
              .title("Online Shop API")
              .description("Manage customers and products and sell the products to the customers")
              .version("1.0.0")
          )
          .addTagsItem(
            new Tag()
              .name("customers")
              .description("Manage Customers")
          );
    }

}
