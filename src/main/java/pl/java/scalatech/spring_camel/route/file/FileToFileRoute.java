package pl.java.scalatech.spring_camel.route.file;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("file")
public class FileToFileRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:inbox?noop=true&delay=5000").to("file:outbox?fileName=${file:onlyname.noext}.${date:now:yyyyMMdd-HH.mm.ss}");
    }

}