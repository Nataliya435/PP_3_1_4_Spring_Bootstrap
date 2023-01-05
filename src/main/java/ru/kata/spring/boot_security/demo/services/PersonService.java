package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Person;
import java.util.List;

public interface PersonService {
    List<Person> getAllPersons();
    Person getPersonById(Long id);
    void savePerson(Person person);
    void deletePerson(Long id);
}



