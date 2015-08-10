package pl.java.scalatech.spring_camel.config;

import lombok.extern.slf4j.Slf4j;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cxf")
@Slf4j
public class CxfConfig {
    @Bean
    public ServletRegistrationBean soapServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CXFServlet(), "/services/*");
        registration.setName("CXFServlet");
        return registration;
    }

    @Bean
    Bus cxf() {
        return BusFactory.newInstance().createBus();
    }
}
