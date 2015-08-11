package pl.java.scalatech.spring_camel.ws;

import javax.jws.WebService;

@WebService
public interface HelloWorld {

    String sayHi(String text);

}