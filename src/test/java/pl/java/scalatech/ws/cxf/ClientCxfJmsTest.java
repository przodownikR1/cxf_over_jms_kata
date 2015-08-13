package pl.java.scalatech.ws.cxf;

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
@ContextConfiguration(locations = { "classpath:jmsCxfClient.xml" })
@Slf4j
public class ClientCxfJmsTest {

    @Autowired
    private HelloWorld helloWorld;

    @Test
    public void test() {
        helloWorld.sayHi("slawek");
    }
}
