package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Юрий", "Maсилевич", (byte) 60);
        userService.saveUser("Диана", "Дангель", (byte) 100);
        userService.saveUser("Фидана", "Тагиева", (byte) 127);
        List<User> users = userService.getAllUsers();
        for (User l: users){
            System.out.println(l);
        }
        userService.createUsersTable();
        userService.dropUsersTable();

    }
}

