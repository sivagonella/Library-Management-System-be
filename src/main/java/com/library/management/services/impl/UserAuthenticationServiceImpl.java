package com.library.management.services.impl;

import com.library.management.domain.entity.User;
import com.library.management.repositories.UserRepository;
import com.library.management.services.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmailID(String email) {
        return userRepository.findByEmail(email);
    }
}
