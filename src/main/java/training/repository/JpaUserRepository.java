package training.repository;

import org.springframework.stereotype.Repository;
import training.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaUserRepository implements UserRepository{

    private long nextId = 4;

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u",User.class).getResultList();
    }

    public User getUser(Long id) {
        return entityManager.find(User.class,id);
    }

    public int getNumberOfUsers() {
        String jpaTxt = "select count(u.id) from User u";
        Long result = (Long)entityManager.createQuery(jpaTxt).getSingleResult();
        return result.intValue();
    }

    public Long createUser(String firstName, String name) {
        long id = nextId++;
        entityManager.persist(new User(id,firstName,name));
        return id;
    }

    public int deleteUser(Long id) {
        entityManager.remove(getUser(id));
        return 1;
    }

    public void updateUser(User user) {
        entityManager.merge(user);
    }
}
