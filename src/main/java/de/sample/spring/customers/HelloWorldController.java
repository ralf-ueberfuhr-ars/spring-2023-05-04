package de.sample.spring.customers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping(
      produces = MediaType.TEXT_PLAIN_VALUE
    )
    @ResponseBody
    public String sayHello(
      @RequestParam(value = "name", defaultValue = "World")
      String name
    ) {
        return "<h1>Hello " + name + "!</h1>Welcome!";
    }

}