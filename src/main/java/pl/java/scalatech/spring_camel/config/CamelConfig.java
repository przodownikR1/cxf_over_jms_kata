package pl.java.scalatech.spring_camel.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import pl.java.scalatech.spring_camel.ws.HelloWorld;

@Configuration
@Slf4j
@ComponentScan(basePackages = { "pl.java.scalatech.spring_camel.route", "pl.java.scalatech.spring_camel.processor", "pl.java.scalatech.spring_camel.eip_beans" }, includeFilters = { @Filter(Component.class) })
@PropertySource("classpath:camel.properties")
public class CamelConfig extends CamelConfiguration {

    @Value("${camel.traceFlag}")
    private Boolean traceFlag;

    @Value("${camel.trace.mdc}")
    private Boolean useMDC;

    @Value("${camel.trace.useBreadcrumb}")
    private Boolean useBreadcrumb;

    @Autowired
    private CamelContext camelContext;

    @PostConstruct
    public void init() {
        camelContext.setTracing(true);
    }

    @Bean
    ProducerTemplate producerTemplate() throws Exception {
        return this.camelContext().createProducerTemplate();
    }

    @Bean
    ConsumerTemplate consumerTemplate() throws Exception {
        return this.camelContext().createConsumerTemplate();
    }

    @Override
    protected void setupCamelContext(CamelContext camelContext) throws Exception {
        PropertiesComponent pc = new PropertiesComponent();
        pc.setLocation("classpath:application.properties");
        camelContext.addComponent("properties", pc);
        //camelContext.addRoutePolicyFactory(new MetricsRoutePolicyFactory());
        camelContext.setUseMDCLogging(useMDC);
        camelContext.setUseBreadcrumb(useBreadcrumb);
        camelContext.setTracing(traceFlag);
        super.setupCamelContext(camelContext);
    }

    @Bean(name = "wsEndpoint")
    public CxfEndpoint wsEndpoint() throws ClassNotFoundException {
        CxfEndpoint cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setAddress("http://localhost:8888/services/person");
        cxfEndpoint.setServiceClass("pl.java.scalatech.spring_camel.service.impl.PersonServiceImpl");
        cxfEndpoint.setLoggingFeatureEnabled(true);
        return cxfEndpoint;
    }

    @Bean(name = "helloEndpoint")
    public CxfEndpoint userServiceEndpoint() {
        CxfEndpoint cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setAddress("http://localhost:8888/services/hello");
        cxfEndpoint.setServiceClass(HelloWorld.class);
        LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor();
        loggingInInterceptor.setPrettyLogging(true);
        LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor();
        loggingOutInterceptor.setPrettyLogging(true);
        cxfEndpoint.getOutInterceptors().add(loggingOutInterceptor);
        cxfEndpoint.getInInterceptors().add(loggingInInterceptor);
        cxfEndpoint.setDataFormat(DataFormat.POJO);
        final Map<String, Object> properties = new HashMap<>();
        properties.put("schema-validation-enabled", "true");
        properties.put("bus.jmx.enabled", "true");

        //properties.setProperty("faultStackTraceEnabled", "true");
        //properties.setProperty("exceptionMessageCauseEnabled", "true");
        //properties.setProperty("schema-validation-enabled", "false");

        //properties.put("allowStreaming", true);
        cxfEndpoint.configureProperties(properties);
        return cxfEndpoint;
    }

    @Bean(name = "dummy")
    public CxfEndpoint dummyEndpoint() {
        CxfEndpoint cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setAddress("http://localhost:8888/services/dummy");
        return cxfEndpoint;
    }

}
