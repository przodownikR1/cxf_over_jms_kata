package pl.java.scalatech;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

@Slf4j
public class SimpleEmbeddedRouteTest extends CommonCreateCamelContext {
    @Test
    public void shouldCamleRouteWork() throws Exception {
        createContextWithGivenRoute(new MyRouteBuilder(), 2000);
    }
}

class MyRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        from("timer:fire?period=3000").setBody(constant("hello przodownik")).process(new MyProcessor()).to("log:MyRoute").process(exchange -> {
            String body = exchange.getIn().getBody(String.class);
            if (body.startsWith("Prefixed ")) {
                body = body.substring("Prefixed ".length());
                exchange.getIn().setBody(body);
            }
        }).to("log:MyRoute");
    }
}

@Slf4j
class MyProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String inBody = exchange.getIn().getBody(String.class);
        log.info("Received in message with body {}", inBody);
        log.info("Prefixing body ...");
        inBody = "Prefixed " + inBody;
        exchange.getIn().setBody(inBody);
    }
}