package com.company;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
	// write your code here
        DB db = new DB();
        System.out.println(db.getStudentsCount());
        System.out.println(db.getStudentsCountWithLetter("а"));
    }
}
