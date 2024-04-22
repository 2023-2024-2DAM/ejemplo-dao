package org.ies.tierno.library.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private final String databaseUrl;
    private final String username;
    private final String password;

    public ConnectionManager(String databaseUrl, String username, String password) throws ClassNotFoundException {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
        Class.forName("com.mysql.jdbc.Driver");
    }

    public Connection getConnection() throws SQLException {

        Properties connectionProps = new Properties();
        connectionProps.put("user", username);
        connectionProps.put("password", password);
        return DriverManager.getConnection(
                databaseUrl,
                connectionProps);
    }
}
