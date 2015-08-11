package pl.java.scalatech.spring_camel.route.ws;

import org.apache.camel.builder.RouteBuilder;

// @Component
public class WsFirst extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        /*
         * from("cxf://http://localhost:8888/services/webServiceCamelWS?" + "serviceClass=pl.java.scalatech.spring_camel.ws.HelloWorld").process(
         * arg0 -> System.out.println("Code run here"));
         */

    }
}
