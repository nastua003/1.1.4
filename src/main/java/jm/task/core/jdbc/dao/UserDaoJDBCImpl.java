package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection;

    static {
        try {
            connection = Util.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS USERS" +
            "(ID BIGINT NOT NULL AUTO_INCREMENT," +
            " NAME VARCHAR(90)," +
            " LASTNAME VARCHAR(90), " +
            " AGE TINYINT," +
            " PRIMARY KEY(ID));";
    private static final String DELETE_IF_EXIST = "DROP TABLE IF EXISTS USERS;";

    private static final String INSERT_USER = "INSERT INTO USERS(NAME, LASTNAME, AGE) VALUES (?, ?, ?);";
    private static final String DELETE_USER = "DELETE FROM USERS WHERE ID = ?;";
    private static final String GET_ALL_USERS = "SELECT * FROM USERS;";
    private static final String DELETE_ALL_USERS = "DELETE FROM USERS WHERE ID > 0;";

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE)) {
            statement.executeUpdate();
            connection.commit();
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void dropUsersTable() {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_IF_EXIST)) {
                statement.executeUpdate();
                connection.commit();
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            connection.commit();
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            connection.commit();
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet result = statement.executeQuery();
            connection.commit();
            connection.rollback();
            while (result.next()) {
                String name = result.getString("NAME");
                String lastName = result.getString("LASTNAME");
                byte age = result.getByte("AGE");
                User user = new User(name, lastName, age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void cleanUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ALL_USERS)) {
            statement.executeUpdate();
            connection.commit();
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
