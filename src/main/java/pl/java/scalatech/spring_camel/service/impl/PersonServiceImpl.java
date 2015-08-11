package pl.java.scalatech.spring_camel.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pl.java.scalatech.spring_camel.beans.Person;
import pl.java.scalatech.spring_camel.service.PersonService;

import com.google.common.collect.Lists;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private List<Person> persons = Lists.newArrayList(new Person("1", "przodownik", "borowiec", new Date()), new Person("2", "agnieszka", "borowiec",
            new Date()), new Person("3", "bak", "borowiec", new Date()));

    @Override
    public Person search(String login) {
        Optional<Person> person = persons.stream().findFirst().filter(p -> login.equals(p.getFirstName()));
        return person.orElseThrow(() -> new IllegalArgumentException("person with login :" + login + " not exists..."));

    }
}
