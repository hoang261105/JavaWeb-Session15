package com.example.repository.user;

import com.example.model.User;

public interface UserRepository {
    boolean checkLogin(String email, String password);

    User findByEmail(String email);
}
