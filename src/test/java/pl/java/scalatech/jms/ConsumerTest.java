package pl.java.scalatech.jms;

import java.util.Random;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.CommonCreateCamelContext;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ContextConfig.class)
public class ConsumerTest extends CommonCreateCamelContext {

    @Test
    public void shouldProduceAndConsumeMessage() throws Exception {
        log.info("+++ ");
        createContextWithGivenRoute(new SimpleProduceRoute(), 1200);

    }

    class SimpleProduceRoute extends RouteBuilder {
        Random r = new Random();

        @Override
        public void configure() throws Exception {
            int num = r.nextInt(100);
            from("file://input?noop=true&fileName=simple.txt").split().tokenize("\\n").convertBodyTo(String.class)
                    .to(ExchangePattern.InOnly, "activeMq:consumer").routeId("askRoute");

        }
    }
}
