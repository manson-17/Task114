package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static String userName = "root";
    private static String password ="2776";
    private static String connectionUrl = "jdbc:mysql://localhost:3306/test";

    public static Connection getConnection()  {

        Connection connection = null;

        try {
        //    Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            connection.setAutoCommit(false);
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return connection;
    }
}
