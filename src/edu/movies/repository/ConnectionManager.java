package edu.movies.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager connectionManager;
    private Connection connection;

    private ConnectionManager() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionManager getInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public Connection getConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://54.93.65.5:5432/sebi7", "fasttrackit_dev", "fasttrackit_dev");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException ignored) {

        }
    }
}
