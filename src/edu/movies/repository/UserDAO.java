package edu.movies.repository;

import edu.movies.model.User;
import org.postgresql.util.PSQLException;

import java.sql.*;

public class UserDAO {
    private ConnectionManager connectionManager;

    public UserDAO() {
        connectionManager = ConnectionManager.getInstance();
    }

    public User insertUser(User user) {
        Connection connection = connectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            int q = 1;
            preparedStatement.setString(q++, user.getUsername());
            preparedStatement.setString(q++, user.getPassword());
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            resultSet.next();
            user.setId(resultSet.getLong(1));
        } catch (PSQLException e) {
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public User getUserByUsername(String username) {
        Connection connection = connectionManager.getConnection();
        try {
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM users where username='" + username + "'";
            ResultSet resultSet = statement.executeQuery(query);
            User user = new User();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
            } else {
                return null;
            }
            resultSet.close();
            statement.close();
            return user;
        } catch (SQLException e) {
            return null;
        }
    }


}
