package pl.java.scalatech.spring_camel.route.ws;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CxfCamelRoute extends RouteBuilder {
    @Autowired
    private Endpoint wsEndpoint;

    @Override
    public void configure() throws Exception {
        log.info("+++++++++++++++++++++++++++++++++++++++++++++++++  {}", wsEndpoint);
        /*
         * from(wsEndpoint).log(">> Received from CXF Java Endpoint : ${body}").setBody().simple("Bean Injected").beanRef("helloWorld", "sayHello")
         * .log(">> Response : ${body}");
         */

    }

}