package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id);

    UserDetails findByLogin(String login);

    void saveUser(User user);
}