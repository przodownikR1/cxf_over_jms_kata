package pl.java.scalatech.spring_camel.config;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.component.jms.JmsConfiguration;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.jms.JMSConfigFeature;
import org.apache.cxf.transport.jms.JMSConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import pl.java.scalatech.spring_camel.ws.OrderProcess;
import pl.java.scalatech.spring_camel.ws.impl.OrderProcessImpl;

@Configuration
public class CamelJMSConfig {

    @Bean(name = "activeMq")
    public ActiveMQComponent activeMq() {
        ActiveMQComponent activeMQComponent = new ActiveMQComponent();
        activeMQComponent.setUseMessageIDAsCorrelationID(true);
        activeMQComponent.setConnectionFactory(activeMqConnectionFactory());
        return activeMQComponent;
    }

    @Bean(name = "activeMqConnectionFactory")
    public ConnectionFactory activeMqConnectionFactory() {
        return new ActiveMQConnectionFactory("tcp://localhost:61616");
    }

    @Bean(name = "jmsConfig")
    @DependsOn(value = { "activeMqConnectionFactory", "jmsTransactionManager" })
    public JmsConfiguration jmsConfiguration() {
        JmsConfiguration jmsConfiguration = new JmsConfiguration(activeMqConnectionFactory());
        jmsConfiguration.setTransacted(true);
        jmsConfiguration.setTransactionManager(jmsTransactionManager());
        jmsConfiguration.setCacheLevelName("CACHE_CONNECTION");
        jmsConfiguration.setUseMessageIDAsCorrelationID(true);
        return jmsConfiguration;
    }

    @Bean(name = "jmsTransactionManager")
    @DependsOn(value = { "activeMqConnectionFactory" })
    public PlatformTransactionManager jmsTransactionManager() {
        JmsTransactionManager transactionManager = new JmsTransactionManager();
        transactionManager.setConnectionFactory(activeMqConnectionFactory());
        return transactionManager;
    }

    @Bean
    public JaxWsServerFactoryBean jwfb() {
        JaxWsServerFactoryBean jwfb = new JaxWsServerFactoryBean();
        jwfb.setServiceClass(OrderProcess.class);
        jwfb.setServiceBean(new OrderProcessImpl());
        jwfb.setAddress("jms://"); //specify jms transport
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");

        //set target destination queue
        JMSConfiguration jmsConfig = new JMSConfiguration();
        jmsConfig.setTargetDestination("cxf.queue");
        jmsConfig.setConnectionFactory(connectionFactory);

        //add feature
        JMSConfigFeature jmsFeature = new JMSConfigFeature();
        jmsFeature.setJmsConfig(jmsConfig);
        jwfb.getFeatures().add(jmsFeature);

        //create
        jwfb.create();
        //create
        jwfb.create();
        return jwfb;
    }

}