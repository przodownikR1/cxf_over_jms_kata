package pl.java.scalatech;

import lombok.extern.slf4j.Slf4j;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.model.ModelCamelContext;
import org.apache.camel.processor.interceptor.Tracer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.spring_camel.config.SimpleActiveMqConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SimpleActiveMqConfig.class)
@Slf4j
public abstract class CommonCreateCamelContext {
    protected CamelContext camelContext;
    protected ProducerTemplate pt;
    protected ConsumerTemplate ct;
    @Autowired
    private ActiveMQComponent activeMq;

    protected void createContextWithGivenRoute(RouteBuilder route, int timeWork) throws Exception, InterruptedException {
        SimpleRegistry registry = new SimpleRegistry();
        ModelCamelContext context = new DefaultCamelContext(registry);
        Tracer tracer = new Tracer();
        tracer.setLogName("MyTracerLog");
        tracer.getDefaultTraceFormatter().setShowProperties(false);
        tracer.getDefaultTraceFormatter().setShowHeaders(false);
        tracer.getDefaultTraceFormatter().setShowBody(true);
        context.addInterceptStrategy(tracer);
        context.addRoutes(route);
        context.addComponent("activeMq", activeMq);

        this.camelContext = context;
        this.ct = context.createConsumerTemplate();
        this.pt = context.createProducerTemplate();

        context.start();
        context.setTracing(false);
        Thread.sleep(timeWork);
        context.stop();
    }
}
