package com.library.management.services;

import com.library.management.domain.User;

public interface UserAuthenticationService {
    public User addUser(User user);

    public User getUserByEmailID(String email);
}
