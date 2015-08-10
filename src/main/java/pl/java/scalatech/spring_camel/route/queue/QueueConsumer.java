package pl.java.scalatech.spring_camel.route.queue;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("queue")
public class QueueConsumer extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("test-activemq:queue:test").to("log:pl.java.scalatech?level=INFO");
    }

}
