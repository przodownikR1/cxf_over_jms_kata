package pl.java.scalatech.spring_camel.route.ws;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PingPongRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //from("timer://my-timer?fixedRate=true&period=12500").setBody(simple("przodownik")).beanRef("personService").to("log:myCamel");
        // from("cxf:/ping?serviceClass=" + pl.java.scalatech.spring_camel.route.ws.PingService.class.getName()).convertBodyTo(String.class).process(e -> {
        //    String pingRequest = e.getIn().getBody(String.class);
        //   e.getIn().setBody(new Object[] { "Pong: " + pingRequest });
        //});

    }

}