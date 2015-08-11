package pl.java.scalatech.spring_camel.config;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.CamelContext;
import org.apache.camel.component.cxf.transport.CamelTransportFactory;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.Feature;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.management.InstrumentationManager;
import org.apache.cxf.management.counters.CounterRepository;
import org.apache.cxf.management.jmx.InstrumentationManagerImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import pl.java.scalatech.spring_camel.eip_beans.HelloWorld;

@Configuration
@ComponentScan(basePackages = "pl.java.scalatech.service")
@Slf4j
public class CxfConfig extends SpringBootServletInitializer {

    @Autowired
    SpringBus cxf;

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private HelloWorld helloWorld;

    @Bean
    public ServletRegistrationBean soapServletRegistrationBean() {
        log.info("+++  soapServletRegistration");
        ServletRegistrationBean registration = new ServletRegistrationBean(new CXFServlet(), "/services/*");
        registration.setLoadOnStartup(1);
        registration.setName("CXFServlet");
        return registration;
    }

    @Bean(name = "loggingFeature")
    LoggingFeature loggingFeature() {
        LoggingFeature loggingFeature = new LoggingFeature();
        loggingFeature.setPrettyLogging(true);
        return loggingFeature;
    }

    @Bean
    public CounterRepository counterRepository(SpringBus cxf) {
        CounterRepository repository = new CounterRepository();
        repository.setBus(cxf);
        return repository;
    }

    @Bean
    public InstrumentationManager instrumentationManager(SpringBus cxf) {
        InstrumentationManagerImpl impl = new InstrumentationManagerImpl();
        impl.setEnabled(true);
        impl.setBus(cxf);
        impl.setUsePlatformMBeanServer(true);
        return impl;
    }

    @Bean(name = "cxf", destroyMethod = "shutdown")
    public SpringBus configureCxfBus() {
        final SpringBus bus = new SpringBus();
        List<Feature> features = new ArrayList<>();
        features.add(loggingFeature());
        bus.setFeatures(features);
        bus.setId("cxf");
        bus.getExtension(CamelTransportFactory.class).setCamelContext(camelContext);
        return bus;
    }

    @Bean(name = "helloWorldProviderBean")
    public EndpointImpl helloWorldEndpoint() {
        log.info("++++ {} jaxwsEndpoint hello", cxf);
        EndpointImpl endpoint = new EndpointImpl(cxf, helloWorld);
        endpoint.setAddress("/helloworld");
        endpoint.setBus(cxf);
        endpoint.publish();
        return endpoint;
    }

    /*
     * @Bean(name = "personService")
     * public CxfEndpoint getPersonService() throws ClassNotFoundException {
     * CxfEndpoint cxfEndpoint = new CxfEndpoint();
     * cxfEndpoint.setAddress("http://localhost:8888/services/person");
     * cxfEndpoint.setServiceClass("pl.java.scalatech.spring_camel.service.impl.PersonServiceImpl");
     * LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
     * loggingOutInterceptor.setPrettyLogging(true);
     * cxfEndpoint.getOutInterceptors().add(new LoggingOutInterceptor());
     * cxfEndpoint.setLoggingFeatureEnabled(true);
     * return cxfEndpoint;
     * }
     */
}
