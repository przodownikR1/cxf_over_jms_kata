package pl.java.scalatech.jms;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.Consume;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JmsReceiver {

    @PostConstruct
    public void init() {
        log.info("+++     JMS RECEIVER +");
    }

    @Consume(uri = "activeMq:consumer")
    public String myMethod(String message) {
        log.info("+++             consume message : {} ", message);
        return message;

    }
}