package pl.java.scalatech.spring_camel.eip_beans;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.Body;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HelloWorldBean {

    public String sayHello(@Body String message) {
        log.info("+++ helloBeanInvoke...");
        return ">> Hello " + message + " user.";
    }
}