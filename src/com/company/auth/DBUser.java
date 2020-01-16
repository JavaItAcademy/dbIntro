package com.company.auth;

import com.company.Database;
import com.company.auth.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUser {
    public boolean addUser(User user) {
        String SQL = "insert into users_1 (login, email, password, date_of_registration) values (?,?,?, now())";
        try (Connection conn = Database.connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1,user.getLogin());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            System.out.println("Successfully created user: " + user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByLogin(String login) {
        String SQL = "select * from users_1 where login = ?";
        try (Connection conn = Database.connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1,login);
            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("ID"));
                    user.setLogin(rs.getString("LOGIN"));
                    user.setEmail(rs.getString("EMAIL"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setDateOfRegistration(rs.getDate("DATE_OF_REGISTRATION"));
                    return user;
                }
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
