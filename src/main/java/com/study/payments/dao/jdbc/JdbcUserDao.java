package com.study.payments.dao.jdbc;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.study.payments.dao.UserDao;
import com.study.payments.entity.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {

    private static final String URL = "jdbc:mysql://localhost:3306/userpaymentservice";
    private static final String USERNAME = "root";
    private static final String PASS = "root123";

    public static final String SQL_GET_ALL = "select * from user";
    private static final String SQL_ADD = "insert into user (firstName, lastName, payment) values(?, ?, ?)";
    public static final String SQL_DELETE = "delete from user where id = ?";
    public static final String SQL_GET_BY_ID = SQL_GET_ALL + " where id = ?";
    public static final String SQL_UPDATE = "update user set firstName = ?, lastName = ?, payment = ? where id = ?";
    public static final String SQL_GET_MAX_ID = "select max(id) from user";

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();

        try (Connection connection = connectToDatabase();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPayment(resultSet.getDouble("payment"));
                result.add(user);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create connection!", e);
        }
        return result;
    }

    @Override
    public void save(User user) {
        try (Connection connection = connectToDatabase();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDouble(3, user.getPayment());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create connection!", e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = connectToDatabase();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDouble(3, user.getPayment());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create connection!", e);
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = connectToDatabase()) {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create connection!", e);
        }
    }


    @Override
    public User getById(Integer id) {
        User user = null;
        Connection connection = connectToDatabase();
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setPayment(resultSet.getDouble("payment"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create connection!", e);
        }
        return user;
    }

    @Override
    public int getMaxId() {
        int maxId = 0;
        Connection connection = connectToDatabase();
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_MAX_ID)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                maxId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create connection!", e);
        }
        return maxId;
    }

    private static Connection connectToDatabase() {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Driver is not found", e);
        }
        return connection;
    }
}
