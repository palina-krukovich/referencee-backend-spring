package com.referencee.api.repository;

import com.referencee.api.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findOneByEmail(String email);
    List<User> findAllByAdminTrue();
}
