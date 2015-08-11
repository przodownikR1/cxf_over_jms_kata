package pl.java.scalatech.spring_camel.eip_beans;

import org.apache.camel.Body;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld {

    public String sayHello(@Body String message) {
        return ">> Hello " + message + " user.";
    }
}