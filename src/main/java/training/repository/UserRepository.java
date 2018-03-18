package training.repository;

import training.User;

import java.util.List;

public interface UserRepository {
    List<User> getUsers();
    User getUser(Long id);
    int getNumberOfUsers();
    Long createUser(String firstName,String name);
    int deleteUser(Long id);
    void updateUser(User user);
}
