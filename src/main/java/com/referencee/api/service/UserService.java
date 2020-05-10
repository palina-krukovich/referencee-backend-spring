package com.referencee.api.service;

import com.referencee.api.model.User;
import com.referencee.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void save(String email, Boolean admin) {
        User user = new User(email, admin);
        repository.save(user);
    }

    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    public List<User> findAllIsAdmin() {
        return repository.findAllByAdminTrue();
    }

    public User findOneByEmail(String email) {
        return repository.findOneByEmail(email);
    }

    public boolean isAdmin(String email) {
        User user = repository.findOneByEmail(email);
        if (null != user) {
            return user.getAdmin();
        }
        return false;
    }

    public void deleteUser(User user) {
        repository.delete(user);
    }
}
