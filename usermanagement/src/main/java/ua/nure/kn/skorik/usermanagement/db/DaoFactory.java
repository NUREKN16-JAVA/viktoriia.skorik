package ua.nure.kn.skorik.usermanagement.db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {
	private static final String USER_DAO = "ua.nure.kn.skorik.usermanagement.db.UserDao";
	private final Properties properties;
    private final static DaoFactory INSTANCE = new DaoFactory();
	
	public DaoFactory() {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static DaoFactory getInstance() {
		return INSTANCE;
	}
	
	private ConnectionFactory getConnectionFactory(){
        String user = properties.getProperty("connection.user");
        String password = properties.getProperty("connection.password");
        String url = properties.getProperty("connection.url");
        String driver = properties.getProperty("connection.driver");
        return new ConnectionFactoryImpl(driver, url, user, password);
    }
	
	public UserDao getUserDao () {
		UserDao userDao = null;
        try {
            Class<?> clazz = Class.forName(properties.getProperty(USER_DAO));
            userDao = (UserDao) clazz.newInstance();
            userDao.setConnectionFactory(getConnectionFactory());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | DatabaseException e) {
        	e.printStackTrace();
        }

        return userDao;
    }
}
