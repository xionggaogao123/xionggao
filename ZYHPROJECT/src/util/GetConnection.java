package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class GetConnection {
	private static final String DBDRIVER = "org.gjt.mm.mysql.Driver" ;
	private static final String DBURL = "jdbc:mysql://localhost:3306/firework" ;
	private static final String DBUSER = "root" ;
	private static final String DBPASS = "123456" ;
	private Connection conn = null ;
	public GetConnection(){
		try {
			Class.forName(DBDRIVER) ;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER,DBPASS) ;
			System.out.println("数据库连接成功~");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		return this.conn ;
	}
	public void close(){
		if(this.conn!=null){
			try {
				this.conn.close() ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}