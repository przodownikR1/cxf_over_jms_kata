package pl.java.scalatech.request_reply;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

import pl.java.scalatech.CommonCreateCamelContext;

@Slf4j
public class FirstTest extends CommonCreateCamelContext {

    @Test
    public void shouldRequestReplyWork() throws Exception {
        log.info("+++ reqreply");
        createContextWithGivenRoute(new RequestReplySimple(), 1200);
        String reply = pt.requestBody("activemq:queue:in?useMessageIDAsCorrelationID=true", "Hello World", String.class);
        log.info("++++ reply :  {}");
    }

    @Test
    public void shouldRequestReplyWork2() throws Exception {
        log.info("+++ reqreply");
        createContextWithGivenRoute(new RequestReplySimple(), 1200);
        String reply = pt.requestBody("activemq:queue:in?replyTo=bar&useMessageIDAsCorrelationID=true", "Hello World", String.class);
        log.info("++++ reply :  {}");
    }

    class RequestReplySimple extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("activemq:queue:in?useMessageIDAsCorrelationID=true").process(exchange -> {
                String id = exchange.getIn().getHeader("JMSCorrelationID", String.class);
                exchange.getOut().setBody("Bye World");
            });

        }
    }

}