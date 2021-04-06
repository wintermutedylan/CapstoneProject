package com.capstone.cars;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	public User checkLogin(String email, String password) throws SQLException, ClassNotFoundException{
		String jdbcURL = "jdbc:mysql://capstonedatabase.cjus59jdxyan.us-east-2.rds.amazonaws.com:3306/login";
		String dbUser = "dwintermute";
		String dbPassword = "KalyN2007!";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		String sql = "SELECT * FROM newusers WHERE email = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, email);
		
		
		
		ResultSet result = statement.executeQuery();
		
		User user = null;
		
		if (result.next()) {
			boolean passwordMatch = PasswordUtils.verifyUserPassword(password, result.getString("password"), result.getString("salty"));
			if (passwordMatch) {
				user = new User();
				user.setFullname(result.getString("fullname"));
				user.setEmail(email);
			}
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
		String sql = "SELECT * FROM newusers WHERE email = ?";
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
			String salt = PasswordUtils.getSalt(30);
			String mySecurePassword = PasswordUtils.generateSecurePassword(password, salt);
			String sqlInsert = "INSERT INTO newusers (email, password, salty, fullname)"
					+ " values (?, ?, ?, ?)";
			PreparedStatement prepState = connection.prepareStatement(sqlInsert);
			prepState.setString(1, email);
			prepState.setString(2, mySecurePassword);
			prepState.setString(3, salt);
			prepState.setString(4, name);
			
			prepState.execute();
			
			connection.close();
			return "Succesfully registered";
		}
			
		
	}
	
	public static List<Attribute> readHistory(String itemName, String id) throws ClassNotFoundException, SQLException {
		String jdbcURL = "jdbc:mysql://capstonedatabase.cjus59jdxyan.us-east-2.rds.amazonaws.com:3306/login";
		String dbUser = "dwintermute";
		String dbPassword = "KalyN2007!";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		String sql = "SELECT * FROM attributerepairhistory WHERE name = ? and carid = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, itemName);
		statement.setString(2, id);
		ResultSet result = null;
		List<Attribute> list = new ArrayList<Attribute>();
		
			result = statement.executeQuery();
			while (result.next()) {
				Attribute att = new Attribute(itemName, result.getString("mileage"), result.getString("lastupdated"));
				list.add(att);
			}

		
		
		
		
		
		
		
		connection.close();
		return list;
		
	}
	
	public static void storeHistory(String itemName, String miles, String updated, String id) throws ClassNotFoundException, SQLException{
		String jdbcURL = "jdbc:mysql://capstonedatabase.cjus59jdxyan.us-east-2.rds.amazonaws.com:3306/login";
		String dbUser = "dwintermute";
		String dbPassword = "KalyN2007!";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		String sql = "INSERT INTO attributerepairhistory (name, mileage, lastupdated, carid)"
				+ " values (?, ?, ?, ?)";
		PreparedStatement prepState = connection.prepareStatement(sql);
		prepState.setString(1, itemName);
		prepState.setString(2, miles);
		prepState.setString(3, updated);
		prepState.setString(4, id);
		
		prepState.execute();
		
		
		
	}
	
	public static void deleteHistory(String id) throws ClassNotFoundException, SQLException{
		String jdbcURL = "jdbc:mysql://capstonedatabase.cjus59jdxyan.us-east-2.rds.amazonaws.com:3306/login";
		String dbUser = "dwintermute";
		String dbPassword = "KalyN2007!";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
		String sqlNoSafe = "SET sql_safe_updates = 0";
		String sqlYesSafe = "SET sql_safe_updates = 1";
		String sql = "DELETE FROM attributerepairhistory WHERE carid = ?";
		PreparedStatement prepState = connection.prepareStatement(sqlNoSafe);
		prepState.execute();
		prepState = connection.prepareStatement(sql);
		prepState.setString(1, id);
		
		prepState.execute();
		prepState = connection.prepareStatement(sqlYesSafe);
		prepState.execute();
		
		connection.close();
		
		
		
	}

}
