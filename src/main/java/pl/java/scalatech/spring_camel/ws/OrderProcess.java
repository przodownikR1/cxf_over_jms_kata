package pl.java.scalatech.spring_camel.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import pl.java.scalatech.spring_camel.beans.Order;

@WebService(targetNamespace = "http://localhost:8080/cxf/jms")
public interface OrderProcess {

    @WebMethod(operationName = "processOrder")
    public String processOrder(@WebParam(name = "order") Order order);

}