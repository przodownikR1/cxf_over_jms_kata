package pl.java.scalatech.spring_camel.route.ws;

import java.io.ByteArrayOutputStream;

import lombok.extern.slf4j.Slf4j;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import pl.java.scalatech.spring_camel.service.FileContainer;
import pl.java.scalatech.spring_camel.service.UploadWebService;

@Slf4j
public class UploadRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        String cxfEndpoint = "cxf:/uploadService?serviceClass=" + UploadWebService.class.getName();

        from(cxfEndpoint).to("log:input").process(new UploadProcessor()).log("Creating ${file:name} to disk").to("file:target/messages/inputdir");
    }

    private class UploadProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            FileContainer fileContainer = exchange.getIn().getBody(FileContainer.class);
            if (fileContainer.getData() != null) {
                // convert DataHandler to String
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                fileContainer.getData().writeTo(output);
                String body = new String(output.toByteArray());

                // set data (as String) to the file
                exchange.getOut().setBody(body);
                // set output filename
                exchange.getOut().setHeader(Exchange.FILE_NAME, fileContainer.getFileName() + "." + fileContainer.getFileExtension());
            } else {
                log.warn("No data found");
            }
        }
    }
}