package com.company;

import java.sql.*;

public class DB {
    private final String url = "jdbc:postgresql://localhost:5432/";
    private final String user = "postgres";
    private final String password = "123";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public int getStudentsCount() {
        int count = 0;
        String SQL = "select count(*) from students_2";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return count;
    }
    public int getStudentsCountWithLetter(String letter) {
        int count = 0;
        String SQL = "select count(*) from students_2 where name like '%"+ letter+ "%'";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return count;
    }
}
