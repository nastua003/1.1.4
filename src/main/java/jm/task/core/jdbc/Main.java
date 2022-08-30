package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Yury", "Masilevich", (byte) 60);
        userService.saveUser("Ivan", "Ivanov", (byte) 100);
        userService.saveUser("Nastua", "Karabut", (byte) 127);
        List<User> users = userService.getAllUsers();
        for (User l: users){
            System.out.println(l);
        }
        userService.dropUsersTable();

    }
}

