package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final String DROP = "DROP TABLE IF EXISTS users";
    private static final String DEL = "DELETE FROM users WHERE Id = ?";
    private static final String QUERY = "select * from users";
    private static final String CLEAN = "DELETE FROM users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Util user = new Util();
        try (java.sql.Statement statement = user.getConnection().createStatement()) {
            DatabaseMetaData md = user.getConnection().getMetaData();
            ResultSet rs = md.getTables(null, null, "users", null);
            if (rs.next()) {
                System.out.println("Такая таблица уже есть");
            }
            System.out.println("Creating table in selected database...");
            String SQL = "CREATE TABLE IF NOT EXISTS users" +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(45), " +
                    " lastName VARCHAR (45), " +
                    " age INTEGER not NULL, " +
                    " PRIMARY KEY (id))";
            statement.executeUpdate(SQL);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        Util user = new Util();
        try (java.sql.Statement statement = user.getConnection().createStatement()) {
            DatabaseMetaData md = user.getConnection().getMetaData();
            ResultSet rs = md.getTables(null, null, "users", null);
            if (rs.next()) {
                statement.executeUpdate(DROP);
            } else {
                System.out.println("Такой таблицы уже нет");
            }
            System.out.println("Таблица удалена");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Util user = new Util();
        try (java.sql.Statement statement = user.getConnection().createStatement())
        {
            String sql = "INSERT INTO users (name, lastName, age) VALUES ('"+ name +"', '"+ lastName +"', '"+ age +"')";
            statement.executeUpdate(sql);
            System.out.println("User с именем –" + name + " добавлен в базу данных");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        Util user = new Util();
        try (PreparedStatement statement = user.getConnection().prepareStatement(DEL)) {
            statement.setLong(1, id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Пользователь удален");

    }

    public List<User> getAllUsers() {
        Util user = new Util();
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = user.getConnection().prepareStatement(QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User worker = new User();
                worker.setId(resultSet.getLong(1));
                worker.setName(resultSet.getString(2));
                worker.setLastName(resultSet.getString(3));
                worker.setAge(resultSet.getByte(4));
                users.add(worker);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Util user = new Util();
        try (Statement statement = user.getConnection().createStatement())
        {
            statement.executeUpdate(CLEAN);
            System.out.println("Таблица очищена");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
