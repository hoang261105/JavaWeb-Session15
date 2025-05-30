package com.example.repository.user;

import com.example.config.ConnectionDB;
import com.example.model.User;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

@Repository
public class UserRepositoryImp implements UserRepository {
    @Override
    public boolean checkLogin(String email, String password) {
        Connection conn = null;
        CallableStatement callSt = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call check_login(?,?)}");

            callSt.setString(1, email);
            callSt.setString(2, password);

            ResultSet rs = callSt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findByEmail(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        User user = null;

        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_by_email(?)}");

            callSt.setString(1, email);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
