package pl.java.scalatech.spring_camel.beans;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@Builder
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {
    @XmlAttribute
    private String firstName;

    @XmlAttribute
    private String lastName;

    @XmlAttribute
    private String login;

    @XmlAttribute
    private BigDecimal salary;

}