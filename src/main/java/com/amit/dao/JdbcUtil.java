package com.amit.dao;

import java.io.*;
import java.util.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
//Jdbc connection utility

//steps to communicate with the database;
//1.load and register the driver
//2.establish the connection with the database
//3.create statement object and execute the query
//4. process the result set
//5.handle the sql exception if gets the generated
//6.close the connection

public class JdbcUtil {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
//	public static Connection getJdbcConnection() throws SQLException, IOException {
//		String fileLoc="C:\\Users\\amitm\\git\\JSP_SERVLET_JavaFullStack\\ServletJDBCCRUDApp\\src\\main\\java\\com\\amit\\dao\\application.properties";
//		Properties properties = new Properties();
//		try (FileInputStream fis = new FileInputStream(fileLoc)) {
//		    properties.load(fis);
//		    properties.forEach((key, value) -> System.out.println(key + ": " + value));
//		}
//
//		HikariConfig config = new HikariConfig(properties);
//		try (HikariDataSource dataSource = new HikariDataSource(config)) {
//			return dataSource.getConnection();
//		}
//	}

	public static Connection getJdbcConnection()throws SQLException, IOException {
		Connection connection = null;
		System.out.println("driver loaded and registered sucessfully");

		String fileLoc="C:\\Users\\amitm\\git\\JSP_SERVLET_JavaFullStack\\ServletJDBCCRUDApp\\src\\main\\java\\com\\amit\\dao\\application.properties";
		FileInputStream fis=new FileInputStream(fileLoc);
		Properties properties=new Properties();
		properties.load(fis);
		
		String url = properties.getProperty("jdbcUrl");
		String userName =properties.getProperty("userName");
		String passWord =properties.getProperty("passWord");
		connection = DriverManager.getConnection(url, userName, passWord);
		System.out.println("\nConnection established sucesfully");
		return connection;
	}

	public static void cleanup(Connection conn, Statement statement, ResultSet resultset) throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (resultset != null) {
			resultset.close();
		}
		System.out.println("\nConnection closed sucessfully");
	}
}
