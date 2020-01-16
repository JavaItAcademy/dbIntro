package com.company;

import com.company.auth.DBUser;
import com.company.auth.model.User;
import com.company.students.DB;
import com.company.students.model.Country;

import java.sql.SQLException;
import java.util.List;

public class Main {
    static DBUser db = new DBUser();
    public static void main(String[] args) throws SQLException {
        User user = new User("1234", "1234", "1234");
        register(user);
        System.out.println();
        login("123", "123");
    }

    static void register(User user) {
        if (db.addUser(user)) System.out.println("Register success");
        else System.err.println("Register failed");
    }

    static void login(String login, String password) {
        User user = db.getUserByLogin(login);
        if (user == null) {
            System.err.println("Login or password is incorrect");
            return;
        }
        if (user.getPassword().equals(password)) {
            //TODO write to authorization logs
            System.out.println("Authorized successfully");
        }
        else {
            System.err.println("Login or password is incorrect");
        }
        //TODO write to authorization logs

    }
}
