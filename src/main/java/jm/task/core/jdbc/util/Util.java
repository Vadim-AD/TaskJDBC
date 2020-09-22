package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import javax.imageio.spi.ServiceRegistry;
import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "3405895Dflb";

    private Connection connection;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return connection;
    }

    public Util() {
        try  {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            statement.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static AppConfigurationEntry[] sessionFactory;

    public static AppConfigurationEntry[] getSessionFactory(String str) {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration() {
                    @Override
                    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
                        return new AppConfigurationEntry[0];
                    }
                };
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306");
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, str);
                config.getAppConfigurationEntry(String.valueOf(settings));
                config.getAppConfigurationEntry(String.valueOf(User.class));
                ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder()
                        .applySettings((Map) config.getParameters()).build();
                sessionFactory = config.getAppConfigurationEntry(String.valueOf(serviceRegistry));
                // SessionFactory factory=configuration.buildSessionFactory();
                // creating session object Session session=factory.openSession();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    static ServiceRegistry serviceRegistry = null;
    public static AppConfigurationEntry[] getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration() {
                    @Override
                    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
                        return new AppConfigurationEntry[0];
                    }
                };
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306");
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.getAppConfigurationEntry(String.valueOf(settings));
                configuration.getAppConfigurationEntry(String.valueOf(User.class));
                serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder()
                        .applySettings((Map) configuration.getParameters()).build();
                sessionFactory = configuration.getAppConfigurationEntry(String.valueOf(serviceRegistry));
                // SessionFactory factory=configuration.buildSessionFactory();
                // creating session object Session session=factory.openSession();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
