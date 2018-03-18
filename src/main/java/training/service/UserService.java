package training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import training.repository.UserRepository;

@Service @Transactional
public class UserService {

    @Autowired
    private UserRepository repository;

    public String getName(Long id){
        return repository.getOne(id).getName();
    }
}
