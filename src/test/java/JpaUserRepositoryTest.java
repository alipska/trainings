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
public class JpaUserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Test
    public void testGetUsers() throws Exception {
        List<User> users = repository.getUsers();
        assertThat(users.size(),is(3));
    }

    @Test
    public void testGetUser() throws Exception {
        User user = repository.getUser(1L);
        assertThat(user.getId(),is(1L));
        assertThat(user.getFirsName(),is("Jan"));
        assertThat(user.getName(),is("Nowak"));
    }

    @Test
    public void testGetNumberOfUsers() throws Exception {
        assertThat(repository.getNumberOfUsers(),is(3));
    }

    @Test
    public void testCreateUser() throws Exception {
        Long id = repository.createUser("Aga","Jakas");
        assertThat(id,is(notNullValue()));

        User user = repository.getUser(id);
        assertThat(user.getId(),is(id));
        assertThat(user.getFirsName(),is("Aga"));
        assertThat(user.getName(),is("Jakas"));

    }

    @Test
    public void testUpdateUser() throws  Exception {
        User user = repository.getUser(1L);
        user.setName("Inna");
        repository.updateUser(user);

        User updatedUser = repository.getUser(1L);
        assertThat(updatedUser.getName(),is("Inna"));
    }

    @Test
    public void testDeleteUser() {
        for(User user:repository.getUsers()){
            repository.deleteUser(user.getId());
        }
        assertThat(repository.getNumberOfUsers(),is(0));
    }
}
