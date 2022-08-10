package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String Username = "jdbc:mysql://localhost:3306/12";
    private static final String URL = "root";
    private static final String Password = "xeroxvn";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, Password, Username);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
