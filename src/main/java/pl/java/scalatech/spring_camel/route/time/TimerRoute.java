package pl.java.scalatech.spring_camel.route.time;

import java.time.LocalDateTime;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("timer")
public class TimerRoute extends SpringRouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer://foo?fixedRate=true&period=10000").transform().simple("Hello timer ").process(exchange -> log.info("+++ timer {}", LocalDateTime.now()))
                .to("log:pl.java.scalatech?level=INFO");
    }

}
