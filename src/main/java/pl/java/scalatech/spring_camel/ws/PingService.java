package pl.java.scalatech.spring_camel.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface PingService {

    @WebMethod
    public String ping(@WebParam(name = "ping") String ping);

}