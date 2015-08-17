package pl.java.scalatech.xml.jaxb;

import java.math.BigDecimal;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.junit.Test;

import pl.java.scalatech.CommonCreateCamelContext;
import pl.java.scalatech.spring_camel.beans.Customer;

public class JaxbTest extends CommonCreateCamelContext {
    @Test
    public void shouldCamleRouteWork() throws Exception {
        createContextWithGivenRoute(new MyRouteBuilder(), 2000);

    }
}

class MyRouteBuilder extends RouteBuilder {
    @Override
    public void configure() {
        DataFormat myJaxb = new JaxbDataFormat("pl.java.scalatech.spring_camel.beans");
        //TODO : direct problem !
        from("seda:xml").marshal().jaxb().to("stream:out");
        Customer cust = new Customer("slawek", "borowiec", "przodownik", new BigDecimal(20000));
        getContext().createProducerTemplate().sendBody("seda:xml", cust);

    }
}
