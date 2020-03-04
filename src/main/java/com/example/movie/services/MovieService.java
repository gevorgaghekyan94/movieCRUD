package com.example.movie.services;

import com.example.movie.entities.Movie;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

import static com.example.movie.MariaDBConstants.*;

@Service
public class MovieService {

    public void createMovie(Movie movie) {

        try (Connection connection = DriverManager.getConnection(DB_URL, PASS, USER)) {
            if (connection != null) {

                String query = "INSERT INTO Movies(name, description, duration) " +
                        "VALUES (?,?,?)";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, movie.getName());
                preparedStatement.setString(2, movie.getDescription());
                preparedStatement.setInt(3, movie.getDuration());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Movie> findAllMovies() {

        ArrayList<Movie> movies = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Movies";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Movie movie = new Movie();
                    movie.setName(resultSet.getString("name"));
                    movie.setDescription(resultSet.getString("description"));
                    movie.setDuration(resultSet.getInt("duration"));
                    movies.add(movie);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movies;
    }

    public Movie findMovieById(int id) {

        Movie movie = new Movie();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Movies WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    movie.setName(resultSet.getString("name"));
                    movie.setDescription(resultSet.getString("description"));
                    movie.setDuration(resultSet.getInt("duration"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return movie;
    }

    public void updateMovie(int enteredId, Movie movie) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "UPDATE Movies SET name = ?, description = ?, duration = ? WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(4, enteredId);
                preparedStatement.setString(1, movie.getName());
                preparedStatement.setString(2, movie.getDescription());
                preparedStatement.setInt(3, movie.getDuration());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteMovieById(int enteredId) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "DELETE FROM Movies WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, enteredId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
