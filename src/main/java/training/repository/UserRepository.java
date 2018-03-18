package training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{

    List<User> findUsersByFirsName(String firstName);
}
