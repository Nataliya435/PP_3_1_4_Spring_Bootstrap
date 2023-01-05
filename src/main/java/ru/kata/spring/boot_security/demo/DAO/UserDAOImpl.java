package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Person;
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
    public List<Person> getAllPersons() {
        return entityManager.createQuery("select distinct u from Person u left join fetch u.roles ", Person.class)
                .getResultList();
    }

    @Override
    public Person getPersonById(Long id) {
        return entityManager.find(Person.class, id);
    }
    @Override
    public void deletePerson(Long id) {
        entityManager.createQuery(
                "DELETE Person WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void savePerson(Person person) {
        entityManager.merge(person);
    }
    @Override
    public UserDetails findByLogin(String login) {

        return entityManager.createQuery(
                        "SELECT u FROM Person u WHERE u.name = :username", Person.class).setParameter("username", login)
                .getSingleResult();
    }
}
