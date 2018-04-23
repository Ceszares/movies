package edu.movies.repository;

import edu.movies.model.Movie;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private ConnectionManager connectionManager;

    public MovieDAO() {
        connectionManager = ConnectionManager.getInstance();
    }

    public Movie insertMovie(Movie movie) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO movie (title, created_date, actor, genre, image_url) VALUES (?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            int q = 1;
            preparedStatement.setString(q++, movie.getTitle());
            preparedStatement.setDate(q++, movie.getCreatedDate());
            preparedStatement.setString(q++, movie.getActor());
            preparedStatement.setString(q++, movie.getGenre());
            preparedStatement.setString(q++, movie.getImageUrl());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();
            movie.setId(resultSet.getLong("id"));
        } catch (SQLException e) {
            return null;
        }
        return movie;
    }

    public List<Movie> findAllMovies() {
        Connection connection = connectionManager.getConnection();
        String sql = "SELECT * FROM movie;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Movie> movies;
            movies = new ArrayList<>();
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(Long.parseLong(resultSet.getString("id")));
                String title = resultSet.getString("title");
                movie.setTitle(title);
                Date createdDate = resultSet.getDate("created_date");
                movie.setCreatedDate(createdDate);
                String actor = resultSet.getString("actor");
                movie.setActor(actor);
                String genre = resultSet.getString("genre");
                movie.setGenre(genre);
                String imageUrl = resultSet.getString("image_url");
                movie.setImageUrl(imageUrl);
                movies.add(movie);
            }
            return movies;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void findById(String id) {
        throw new NotImplementedException();
    }

    public void delete(String id) {
        throw new NotImplementedException();
    }

    public Movie getMovieById(String id) {
        if (id.length() > 10) {
            return null;
        }
        Connection connection = connectionManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movie WHERE id=" + id);
            Movie movie = null;
            if (resultSet.next()) {
                movie = new Movie();
                movie.setId(Long.parseLong(resultSet.getString("id")));
                String title = resultSet.getString("title");
                movie.setTitle(title);
                Date createdDate = resultSet.getDate("created_date");
                movie.setCreatedDate(createdDate);
                String actor = resultSet.getString("actor");
                movie.setActor(actor);
                String genre = resultSet.getString("genre");
                movie.setGenre(genre);
                String imageUrl = resultSet.getString("image_url");
                movie.setImageUrl(imageUrl);
            }
            resultSet.close();
            statement.close();
            return movie;
        } catch (SQLException e) {
            return null;
        }
    }

    public void delete(Movie movie) {
        Connection connection = connectionManager.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM movie WHERE id=" + movie.getId());
        } catch (SQLException ignored) {
        }
    }
}
