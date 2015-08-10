package pl.java.scalatech.spring_camel.ws;

// @WebService
public interface PingService {

    //  @WebMethod  /@WebParam(name = "ping")
    public String ping(String ping);

}