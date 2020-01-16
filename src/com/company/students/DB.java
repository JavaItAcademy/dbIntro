package com.company.students;

import com.company.Database;
import com.company.students.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {

    public int getStudentsCount() {
        int count = 0;
        String SQL = "select count(*) from students_2";
        try (Connection conn = Database.connect();
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
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return count;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String SQL = "select * from students_2 ";
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while(rs.next()) {
                Student st = new Student();
                st.setId(rs.getInt("ID"));
                st.setName(rs.getString("NAME"));
                st.setGroupId(rs.getInt("GROUP_ID"));
                studentList.add(st);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return studentList;
    }

    public void pritnGroups() {
        String SQL = "select * from groups_2";
        try (Connection conn = Database.connect();
        Statement statement = conn.createStatement();
        ResultSet rs =statement.executeQuery(SQL)) {
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void addPerson(String name, int age) {
        String SQL = "insert into people (name, age) values (?,?)";
        try (Connection conn = Database.connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1,name);
            statement.setInt(2, age);
            statement.executeUpdate();
            System.out.println("Successfully created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void addCountry(String name, int peopleId) {
        String SQL = "insert into countries (name, people_id) values (?,?)";
        try (Connection conn = Database.connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1,name);
            statement.setInt(2, peopleId);
            statement.executeUpdate();
            System.out.println("Successfully created country");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("Duplicates")
    public void addCity(String name, int peopleId, int countryId) {
        String SQL = "insert into cities (name, people_id, country_id) values (?,?,?)";
        try (Connection conn = Database.connect();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1,name);
            statement.setInt(2, peopleId);
            statement.setInt(3, countryId);
            statement.executeUpdate();
            System.out.println("Successfully created city");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Country> getAllCountries() {
        List<Country> result = new ArrayList<>();
        String sql = "select * from countries";
        try(Connection conn = Database.connect();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()){
                Country c = new Country();
                c.setId(resultSet.getInt("ID"));
                c.setName(resultSet.getString("NAME"));
                c.setPeopleId(resultSet.getInt("PEOPLE_ID"));
                result.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Country> getAllCountriesByLength(int nameLength) {
        List<Country> result = new ArrayList<>();
        String sql = "select * from countries where length(name) > ?";
        try(Connection conn = Database.connect();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, nameLength);
            try(ResultSet resultSet = stmt.executeQuery()){
                while (resultSet.next()){
                    Country c = new Country();
                    c.setId(resultSet.getInt("ID"));
                    c.setName(resultSet.getString("NAME"));
                    c.setPeopleId(resultSet.getInt("PEOPLE_ID"));
                    result.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
