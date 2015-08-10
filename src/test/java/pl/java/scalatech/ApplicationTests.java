package pl.java.scalatech;

import org.apache.camel.CamelContext;
import org.apache.camel.ShutdownRunningTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pl.java.scalatech.spring_camel.SpringBootCamelApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootCamelApp.class)
@WebAppConfiguration
public class ApplicationTests {

    @Autowired
    private CamelContext camelContext;

    @Test
    public void contextLoads() throws InterruptedException {
        ShutdownRunningTask s = camelContext.getShutdownRunningTask();
        Thread.sleep(2000);
        System.err.println(s);
    }

}
