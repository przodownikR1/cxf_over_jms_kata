package pl.java.scalatech.spring_camel.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WebServiceCamelI {

    @WebMethod
    public String print();

}