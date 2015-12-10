package com.LeverInc.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Environment {
	final static String DB_URL = "jdbc:derby:EliteBrowserDB;create=true";
	
	// Method for inserting new favorite url(s) into Favorites Table
	public static void addFavorite(String favName, String favURL) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("Connection created to DB!");
			
			Statement stmt = conn.createStatement();
			String insertFavorite = String.format(
					"INSERT into Favorites(Fav_Name, Fav_URL)" + " values ('%s', '%s')", favName, favURL);
			stmt.executeUpdate(insertFavorite);
			System.out.println("Favorite added!");
			
			conn.close();
			System.out.println("Connection closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet getFavorites(){
		Connection conn = null;
		ResultSet result = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("Connection created to DB!");
			
			Statement stmt = conn.createStatement();
			String selectStatement = "SELECT Fav_ID, Fav_Name, Fav_URL FROM Favorites";
			result = stmt.executeQuery(selectStatement);
			
//			while (result.next()) {
//				favoritesList.add(arg0)
//			}
			
			conn.close();
			System.out.println("Connection closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
