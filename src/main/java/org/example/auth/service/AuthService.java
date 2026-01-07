package org.example.auth.service;

import org.example.auth.entity.User;

public interface AuthService {

    User register(User user);

    User login(String username, String password);
}
