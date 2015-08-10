package pl.java.scalatech.spring_camel.route.queue;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("queue")
public class QueueRoute extends RouteBuilder {
    @Autowired
    private Supplier<LocalDateTime> currentTime;

    @Override
    public void configure() throws Exception {
        from("direct:qStart").setHeader("now", method(currentTime)).log("${header[now]} - Hello, ${body}!!").to("test-activemq:queue:test");

    }
}