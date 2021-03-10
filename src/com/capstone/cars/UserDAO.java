package com.capstone.cars;

import java.sql.*;

public class UserDAO {
	public User checkLogin(String email, String password) throws SQLException, ClassNotFoundException{
		String jdbcURL = "jdbc:mysql://capstonedatabase.cjus59jdxyan.us-east-2.rds.amazonaws.com:3306/login";
		String dbUser = "dwintermute";
		String dbPassword = "KalyN2007!";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		String sql = "SELECT * FROM users WHERE email = ? and password = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		statement.setString(2, password);
		
		ResultSet result = statement.executeQuery();
		
		User user = null;
		
		if (result.next()) {
			user = new User();
			user.setFullname(result.getString("fullname"));
			user.setEmail(email);
		}
		
		connection.close();
		
		return user;
	}
	
	public String registerUser(String email, String password, String rePassword, String name) throws SQLException, ClassNotFoundException{
		String jdbcURL = "jdbc:mysql://capstonedatabase.cjus59jdxyan.us-east-2.rds.amazonaws.com:3306/login";
		String dbUser = "dwintermute";
		String dbPassword = "KalyN2007!";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		String sql = "SELECT * FROM users WHERE email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			return "Email is already registered. Please try a different email or login using that email";
		}
		
		else if (!password.equals(rePassword)) {
			
			return "Passwords didn't match. Try again.";
		}
		else {
			String sqlInsert = "INSERT INTO users (email, password, fullname)"
					+ " values (?, ?, ?)";
			PreparedStatement prepState = connection.prepareStatement(sqlInsert);
			prepState.setString(1, email);
			prepState.setString(2, password);
			prepState.setString(3, name);
			
			prepState.execute();
			
			connection.close();
			return "Succesfully registered";
		}
		
		
		
		
		
		
		
		
	}

}
