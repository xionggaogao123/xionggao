package proxy;

import util.GetConnection;
import dao.UserDao;
import daoImpl.UserDaoImpl;
import bean.User;

public class UserDaoProxy implements UserDao {
	private GetConnection dbc = null;
	private UserDao dao =null;
	public UserDaoProxy(){
		this.dbc = new GetConnection();
		this.dao = new UserDaoImpl(this.dbc.getConnection());
	}
	@Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try{
			flag = this.dao.login(user);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				this.dbc.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	public boolean register(User user){
		boolean flag = true;
		try{
			flag = this.dao.register(user);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				this.dbc.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return flag;
	}

}
