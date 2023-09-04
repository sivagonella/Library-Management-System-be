package com.library.management.repositories;

import com.library.management.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserEmail(String email);
}
