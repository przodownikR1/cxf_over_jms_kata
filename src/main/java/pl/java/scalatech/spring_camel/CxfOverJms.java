package pl.java.scalatech.spring_camel;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CxfOverJms implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CxfOverJms.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

}
