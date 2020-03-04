package com.example.movie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.movie.MariaDBConstants.*;

public class CreateDatabaseAndTables {

    public static void createDatabaseAndTables() {
        createDatabase();
        createTableMovies();
    }

    private static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL_DATA, USER, PASS)) {
            if (conn != null) {
                System.out.println("Database library created");
                String query = "CREATE DATABASE IF NOT EXISTS service;";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }

    private static void createTableMovies() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Table Movies created");
                String query = "CREATE TABLE IF NOT EXISTS Movies (" +
                        "id int NOT NULL AUTO_INCREMENT," +
                        "name VARCHAR(255)," +
                        "description VARCHAR(255)," +
                        "duration int," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }

}
