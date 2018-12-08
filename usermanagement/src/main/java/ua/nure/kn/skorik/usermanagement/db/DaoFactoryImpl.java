package ua.nure.kn.skorik.usermanagement.db;

public class DaoFactoryImpl extends DaoFactory {

	@Override
	public UserDao getUserDao() {
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
