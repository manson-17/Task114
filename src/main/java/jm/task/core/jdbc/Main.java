package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

    UserService userService = new UserServiceImpl();

    userService.createUsersTable();
    userService.saveUser("Max", "Cherenkov", (byte) 35);
    userService.saveUser("Vladimir", "DeMort", (byte) 65);
    userService.saveUser("Ilya", "Muromskiy", (byte) 115);
    userService.saveUser("Mixa", "Petrov", (byte) 33);
    userService.removeUserById(3);
    System.out.println(userService.getAllUsers());
    userService.cleanUsersTable();
   // userService.dropUsersTable();
    }
}
