import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import training.domain.User;
import training.repository.UserRepository;


import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class SpringDataUserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testGetUsers() throws Exception {
        List<User> users = repository.findAll();
        assertThat(users.size(),is(3));
    }

    @Test
    public void testGetUser() throws Exception {
        User user = repository.getOne(1L);
        assertThat(user.getId(),is(1L));
        assertThat(user.getFirsName(),is("Jan"));
        assertThat(user.getName(),is("Nowak"));
    }

    @Test
    public void testGetNumberOfUsers() throws Exception {
        assertThat(repository.count(),is(3L));
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User(99L,"Aga","Jakas");
        repository.save(user);
        Long id = user.getId();
        assertThat(id,is(notNullValue()));

        User user1 = repository.getOne(id);
        assertThat(user1.getId(),is(id));
        assertThat(user1.getFirsName(),is("Aga"));
        assertThat(user1.getName(),is("Jakas"));

    }

    @Test
    public void testUpdateUser() throws  Exception {
        User user = repository.getOne(1L);
        user.setName("Inna");
        repository.save(user);

        User updatedUser = repository.getOne(1L);
        assertThat(updatedUser.getName(),is("Inna"));
    }

    @Test
    public void testDeleteUser() {
        repository.deleteAll();
        assertThat(repository.count(),is(0L));
    }

    @Test
    public void testFindUserByFirstName() {
        List<User> users = repository.findUsersByFirsName("Aleksandra");
        assertThat(users.size(),is(1));
        assertThat(users.get(0).name,is("Kowalska"));
    }
}
