package pl.java.scalatech.request_reply;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

import pl.java.scalatech.CommonCreateCamelContext;

@Slf4j
public class RequestReplyTest extends CommonCreateCamelContext {

    @Test
    public void shouldRequestReplyWork() throws Exception {
        log.info("+++ reqreply");
        createContextWithGivenRoute(new RequestReplySimple(), 1200);
        createContextWithGivenRoute(new RequestReplySimpleAnswer(), 1200);
    }

    class RequestReplySimple extends RouteBuilder {
        Random r = new Random();

        @Override
        public void configure() throws Exception {
            int num = r.nextInt(100);
            from("file://input?noop=true&fileName=simple.txt").split().tokenize("\\n").setHeader("JMSMessageID", constant("ID : " + num))
                    .setHeader("JMSReplyTo", constant("myQueue.queue"))
                    .to(ExchangePattern.InOut, "activeMq:simple?replyTo=Q2&useMessageIDAsCorrelationID=true").to("stream:out").routeId("askRoute")
                    .startupOrder(10);

        }
    }

    class RequestReplySimpleAnswer extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("activeMq:simple?&useMessageIDAsCorrelationID=true").transform(simple("Word: ${in.body}")).routeId("answerRoute").startupOrder(20);

        }
    }
}