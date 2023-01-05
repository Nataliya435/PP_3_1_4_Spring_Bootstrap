package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.models.Person;
import java.util.List;

public interface UserDao {

    List<Person> getAllPersons();

    Person getPersonById(Long id);

    void deletePerson(Long id);

    UserDetails findByLogin(String login);

    void savePerson(Person person);
}