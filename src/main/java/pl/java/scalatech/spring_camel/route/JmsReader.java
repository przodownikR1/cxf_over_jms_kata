package pl.java.scalatech.spring_camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JmsReader extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("activeMq:cxf.queue").to("log:myCamel?level=INFO");

    }

}
