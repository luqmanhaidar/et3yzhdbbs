package com.ntsky.bbs.util.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ntsky.bbs.util.Application;
import com.ntsky.bbs.util.BeanFactory;

public class JDBCManager {
	/*private static ResourceBundle rb = ResourceBundle.getBundle("jdbc");

	private static String driver = rb.getString("driver");

	private static String url = rb.getString("url");

	private static String username = rb.getString("username");

	private static String password = rb.getString("password");

	private Connection connection = null;

	public Connection getConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);

		} catch (Exception e) {
			close(connection);
			e.printStackTrace();
		}

		return connection;
	}
*/
	private Connection connection = null;
	
	private BeanFactory bf=BeanFactory.getInstance(Application.getInstance().getServletContext());
	private DriverManagerDataSource dataSource=(DriverManagerDataSource)bf.getBean("dataSource");
	
	
	public Connection getConnection(){
		try {
			connection=dataSource.getConnection();
		} catch (SQLException e) {
			close(connection);
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

/*	public ResourceBundle getRb() {
		return rb;
	}

	public void setRb(ResourceBundle rb) {
		this.rb = rb;
	}*/

}
