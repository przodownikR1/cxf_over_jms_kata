package pl.java.scalatech.spring_camel.ws.impl;

import lombok.extern.slf4j.Slf4j;
import pl.java.scalatech.spring_camel.beans.Order;
import pl.java.scalatech.spring_camel.ws.OrderProcess;

@Slf4j
public class OrderProcessImpl implements OrderProcess {

    @Override
    public String processOrder(Order order) {
        log.info("Processing order   {}", order.getId());
        return order.getId() + " processed";
    }

}