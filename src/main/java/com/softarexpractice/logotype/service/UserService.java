package com.softarexpractice.logotype.service;

import com.softarexpractice.logotype.model.User;
import com.softarexpractice.logotype.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(Long id) {
        return repository.getOne(id);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User saveUser(User course) {
        return repository.save(course);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
