package pl.java.scalatech.spring_camel.route.ws;

import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CxfCamelRoute extends RouteBuilder {
    @Autowired
    private Endpoint wsEndpoint;

    @Autowired
    private Endpoint helloEndpoint;

    @Autowired
    private Endpoint dummy;

    @Override
    public void configure() throws Exception {
        log.info(" +++  wsEndpoint   {}", wsEndpoint);
        log.info(" +++ helloEndpoint {}", helloEndpoint);
        log.info(" +++ dummy  {}", dummy);

        //from("cxf:bean:helloEndpoint").setBody().simple("Bean Injected").beanRef("helloWorld", "sayHello").log(">> Response : ${body}");
        //from("cxf:bean:dummy").setBody().simple("Bean Injected").log(">> Response : ${body}");

    }

}