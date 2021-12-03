package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {

        String SQL = "CREATE TABLE IF NOT EXISTS Users(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(30) NOT NULL, " +
                "lastName VARCHAR(30) NOT NULL, " +
                "age INT CHECK(age>0 AND age <128) " +
                ") ";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try (PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS Users")) {
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement =  connection.prepareStatement
                ("INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?);")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE from Users WHERE id = ?")) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> usersList = new ArrayList<>();

        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * from Users")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                usersList.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public void cleanUsersTable() {

        try (PreparedStatement preparedStatement = connection.prepareStatement("TRUNCATE TABLE Users")) {
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
