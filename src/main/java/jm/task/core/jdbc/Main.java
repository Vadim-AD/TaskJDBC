package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao daoJDBC = new UserDaoHibernateImpl();

        byte age1 = 10;
        byte age2 = 33;
        byte age3 = 51;
        byte age4 = 30;

        daoJDBC.createUsersTable();

        daoJDBC.saveUser("test1", "lastLest1", age1);
        daoJDBC.saveUser("test2", "lastLest2", age2);
        daoJDBC.saveUser("test3", "lastLest3", age3);
        daoJDBC.saveUser("test4", "lastLest4", age4);

        System.out.println(daoJDBC.getAllUsers());

        for (int i = 1; i < 5; i++) {
            daoJDBC.removeUserById(i);
        }
        daoJDBC.cleanUsersTable();

        daoJDBC.dropUsersTable();

        final String testName = "Ivan";
        final String testLastName = "Ivanov";
        final byte testAge = 5;
        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser(testName, testLastName, testAge);
        userService.saveUser(testName, testLastName, testAge);
        userService.saveUser(testName, testLastName, testAge);
        userService.saveUser(testName, testLastName, testAge);
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
