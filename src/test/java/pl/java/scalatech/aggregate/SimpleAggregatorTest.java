package pl.java.scalatech.aggregate;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

import pl.java.scalatech.CommonCreateCamelContext;

@Slf4j
public class SimpleAggregatorTest extends CommonCreateCamelContext {
    @Test
    public void shouldAggregatorCompletionSizeWork() throws Exception {
        log.info("+++ completion");
        createContextWithGivenRoute(new MyRouteBuilderCompletion(), 3000);
    }

    @Test
    public void shouldAggregatorTimeOutWork() throws Exception {
        log.info("+++ timeout");
        createContextWithGivenRoute(new MyRouteBuilderTimeout(), 1500);
    }

    class MyRouteBuilderCompletion extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("timer:fire?period=500").convertBodyTo(String.class).setBody(simple("Hello przodownik")).setHeader("id", simple("YA"))
                    .aggregate(header("id"), new StringAggregator()).completionSize(3).to("log:myCamel?level=INFO&showAll=false&multiline=true");
        }
    }

    class MyRouteBuilderTimeout extends RouteBuilder {
        @Override
        public void configure() throws Exception {
            from("timer:fire?period=500").convertBodyTo(String.class).setBody(simple("Hello przodownik")).setHeader("id", simple("YA"))
                    .aggregate(constant(false), new StringAggregator()).completionTimeout(1000).to("log:myCamel?level=INFO&showAll=false&multiline=true")
                    .to("file:fileName=agg.text");
        }
    }
}
