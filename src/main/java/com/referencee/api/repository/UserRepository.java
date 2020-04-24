package com.referencee.api.repository;

import com.referencee.api.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    void createUser(String userEmail);
    void createAdmin(String userEmail);
    Boolean isAdmin(String userEmail);
}
