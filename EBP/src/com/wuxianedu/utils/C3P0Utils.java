package com.wuxianedu.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	private static DataSource dataSource;
	
	private static final String NAMED_CONFIG = "wuxianedu";
	
	static{
		dataSource = new ComboPooledDataSource(NAMED_CONFIG);
	}
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static Connection getConnection(){
		Connection connection = null;
		try {
			connection = threadLocal.get();
			if(connection == null){
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void startTransaction(){
		Connection connection = null;
		try {
			connection = threadLocal.get();
			if(connection == null){
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commitTransaction(){
		Connection connection = null;
		try {
			connection = threadLocal.get();
			if(connection == null){
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollbackTransaction(){
		Connection connection = null;
		try {
			connection = threadLocal.get();
			if(connection == null){
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeTransaction(){
		Connection connection = null;
		try {
			connection = threadLocal.get();
			if(connection == null){
				connection = dataSource.getConnection();
				threadLocal.set(connection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(connection != null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			threadLocal.remove();
		}
	}
	
	public static String generateKey(){
		return UUID.randomUUID().toString();
	}
}
