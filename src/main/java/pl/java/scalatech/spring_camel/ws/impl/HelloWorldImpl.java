package pl.java.scalatech.spring_camel.ws.impl;

import javax.jws.WebService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import pl.java.scalatech.spring_camel.ws.HelloWorld;

@WebService(endpointInterface = "pl.java.scalatech.spring_camel.ws.HelloWorld")
@Slf4j
@Service
public class HelloWorldImpl implements HelloWorld {

    @Override
    public String sayHi(String text) {
        log.info("++++++++++++++++++++++++++++++++++  {}", text);
        return "Hello :  " + text;
    }

}