package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select distinct u from User u left join fetch u.roles ", User.class)
                .getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery(
                "DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }
    @Override
    public UserDetails findByLogin(String login) {

        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.name = :username", User.class).setParameter("username", login)
                .getSingleResult();
    }
}
