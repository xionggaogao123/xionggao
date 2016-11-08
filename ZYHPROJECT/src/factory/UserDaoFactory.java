package factory;

import proxy.UserDaoProxy;
import dao.UserDao;

public class UserDaoFactory {
	public static UserDao getUserDaoInstance() throws Exception{
		return new UserDaoProxy() ;
	}
}
