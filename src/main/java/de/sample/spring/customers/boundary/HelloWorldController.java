package de.sample.spring.customers.boundary;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static java.util.Objects.requireNonNullElseGet;

@Controller
@RequestMapping("/hello")
@RequiredArgsConstructor
@Profile("dev")
public class HelloWorldController {

    private final HelloConfigurationProperties config;

    @GetMapping(
      produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String sayHello(
      @RequestParam(value = "name", required = false)
      String name
    ) {
        return config.getMessage()
          .replace("{}", requireNonNullElseGet(name, config::getDefaultName));
    }

}
