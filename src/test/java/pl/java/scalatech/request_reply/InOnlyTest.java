package pl.java.scalatech.request_reply;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

import pl.java.scalatech.CommonCreateCamelContext;
import pl.java.scalatech.spring_camel.eip_beans.HelloWorldBean;

@Slf4j
public class InOnlyTest extends CommonCreateCamelContext {

    @Test
    public void shouldRequestReplyWork() throws Exception {

        log.info("+++ inOnly");
        createContextWithGivenRoute(new InOutSimple(), 1200);
        log.info("++++ send...............");
        pt.sendBody("activeMq:InOnly", "inOnly test");
    }

    class InOutSimple extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("activeMq:InOnly").bean(HelloWorldBean.class).to("stream:out").routeId("askRoute");
        }
    }

}
