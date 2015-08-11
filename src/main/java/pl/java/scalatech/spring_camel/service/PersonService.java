package pl.java.scalatech.spring_camel.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import pl.java.scalatech.spring_camel.beans.Person;

@WebService
public interface PersonService {

    public Person search(@WebParam(name = "login") String login);

}