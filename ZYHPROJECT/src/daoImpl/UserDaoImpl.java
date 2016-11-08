package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;

public class UserDaoImpl implements dao.UserDao {
	public Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	public UserDaoImpl(Connection conn){
		this.conn = conn;
	}
	
	@Override
	public boolean login(User user) {
		// TODO Auto-generated method stub
		String sql = "select *from user where name=? and password =?";
		try{
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2,user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e1) {

				}
			}
		}
		return false;
	}
	
	public boolean register(User user){
		String sql = "insert into user (name,password)values(?,?)";
		boolean flag = false;
		int i =0;
		try{
			pstmt = this.conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			i = pstmt.executeUpdate();
			if(i>0){
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
}
