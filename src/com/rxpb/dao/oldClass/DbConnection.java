package com.rxpb.dao.oldClass;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DbConnection  {
	public static java.sql.Connection getConn()
	{
		DbConnection dbc = new DbConnection();
		Properties prop = dbc.getProperties();
		String driver = prop.getProperty("driver");
		String url = prop.getProperty("url");
		String user = prop.getProperty("username");
		String password = prop.getProperty("password");
		java.sql.Connection conn=null;
		try {
			
			Class.forName(driver);			
			 conn=	DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		return conn;
	}

	public static void closeConn( Connection conn)
	{
	  	  if(conn!=null)
	  	  {
	  		  
	  		  try {
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	  	  }
	}
	
	public Properties getProperties(){
		Properties prop = new Properties();
		try {
			String s = this.getClass().getResource("/").getPath().substring(1)+"config.properties";
			//去空格
			s=s.replace("%20", " ");
			prop.load(new BufferedInputStream(new FileInputStream(s)));	
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
}
