package com.example.service.user;

import com.example.model.User;

public interface UserService {
    boolean checkLogin(String email, String password);

    User findByEmail(String email);
}
