package pl.java.scalatech.spring_camel.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@CsvRecord(separator = ",", crlf = "\n")
public class Person {

    @DataField(pos = 1)
    private String clientNr;

    @DataField(pos = 2)
    private String firstName;

    @DataField(pos = 3)
    private String lastName;

    @DataField(pos = 4, pattern = "dd-MM-yyyy")
    private Date orderDate;
}