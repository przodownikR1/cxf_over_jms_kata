package pl.java.scalatech.xml.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(locations = { "classpath:camel-jmsserver.xml" })
public class SpringCamelJmsServer {
    @Test
    public void test() {
        try {
            Thread.sleep(1000 * 10);
        } catch (InterruptedException e) {
        }
    }
}