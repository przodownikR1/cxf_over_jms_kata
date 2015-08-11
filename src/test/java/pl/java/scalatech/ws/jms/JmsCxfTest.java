package pl.java.scalatech.ws.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.jms.JMSConfigFeature;
import org.apache.cxf.transport.jms.JMSConfiguration;
import org.junit.Test;

import pl.java.scalatech.spring_camel.beans.Order;
import pl.java.scalatech.spring_camel.ws.OrderProcess;

public class JmsCxfTest {
    @Test
    public void shouldSendMessage() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        JMSConfiguration jmsConfig = new JMSConfiguration();
        jmsConfig.setTargetDestination("cxf.queue");
        jmsConfig.setConnectionFactory(connectionFactory);
        JMSConfigFeature jmsFeature = new JMSConfigFeature();
        jmsFeature.setJmsConfig(jmsConfig);

        JaxWsProxyFactoryBean client = new JaxWsProxyFactoryBean();
        client.setAddress("jms://");
        client.getFeatures().add(jmsFeature);

        //call service
        OrderProcess orderProcess = client.create(OrderProcess.class);
        Order order = new Order(1l, "przodownik");
        String s = orderProcess.processOrder(order);
        System.out.println(s);

        order = new Order(2l, "ala");
        s = orderProcess.processOrder(order);
        System.out.println(s);

    }
}
