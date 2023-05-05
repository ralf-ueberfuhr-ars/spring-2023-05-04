package de.sample.spring.customers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("hello")
public class HelloConfigurationProperties {

    private String message = "Hallo {}!";
    private String defaultName = "Welt";

}
