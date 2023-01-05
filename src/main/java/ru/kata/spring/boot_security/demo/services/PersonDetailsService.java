package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.DAO.UserDao;
import ru.kata.spring.boot_security.demo.models.Person;

@Service
public class PersonDetailsService implements UserDetailsService {
    private final UserDao userDAO;

    @Autowired
    public PersonDetailsService(UserDao userdao) {
        this.userDAO = userdao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = (Person) userDAO.findByLogin(s);
        person.getRoles().size();
        return person;
    }
}

