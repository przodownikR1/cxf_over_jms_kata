package pl.java.scalatech.xml.jms;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import pl.java.scalatech.spring_camel.ws.HelloWorld;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:soapOverJms.xml" })
@Slf4j
public class SpringCamelJmsClient {

    @Autowired
    private HelloWorld helloService;

    @Test
    public void testSay() {
        String sayHello = helloService.sayHi("przodownik 2");
        log.info("+++      client      {}", sayHello);
    }
}