package com.LeverInc.Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Environment {
	final static String DB_URL = "jdbc:derby:EliteBrowserDB;create=true";
	
	// REQ #7
	// Method for inserting new favorite url(s) into Favorites Table
	public static void addFavorite(String favName, String favURL) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("[Favorite Insert] Connection created to DB!");
			
			Statement stmt = conn.createStatement();
			String insertFavorite = String.format(
					"INSERT into Favorites(Fav_Name, Fav_URL)" + " values ('%s', '%s')", favName, favURL);
			stmt.executeUpdate(insertFavorite);
			System.out.println(" >> Favorite added!");
			
			conn.close();
			System.out.println("[Favorite Insert] Connection closed.\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// REQ #8
	// Returns an ArrayList of all favorites stored in the DB
	public static ArrayList<Favorite> getFavorites(){
		Connection conn = null;
		ArrayList<Favorite> favList = new ArrayList<>();
		
		try {
			conn = DriverManager.getConnection(DB_URL);
			//System.out.println("[Favorites Retrieve] Connection created to DB!");
			
			Statement stmt = conn.createStatement();
			String selectStatement = "SELECT Fav_ID, Fav_Name, Fav_URL FROM Favorites";
			ResultSet result = stmt.executeQuery(selectStatement);
			
			while(result.next()){
				Favorite favObj = new Favorite(result.getString("Fav_Name"), result.getString("Fav_URL"));
				favList.add(favObj);
			}
			
			conn.close();
			//System.out.println("[Favorites Retrieve] Connection closed.\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return favList;
	}
	
	public static void updatePreference(String windowColor) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("[Preference Insert] Connection created to DB!");
			
			Statement stmt = conn.createStatement();
			String updatePreference = "UPDATE Preferences " 
									+ String.format("SET Window_Color = '%s'", windowColor);
			
			stmt.executeUpdate(updatePreference);
			System.out.println(" >> Preferences added!");
			
			conn.close();
			System.out.println("[Preference Insert] Connection closed.\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static String getWindowColor(){
		Connection conn = null;
		String color = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("[Window Color Retrieve] Connection created to DB!");
			
			Statement stmt = conn.createStatement();
			String selectStatement = "SELECT Window_Color FROM Preferences";
			ResultSet result = stmt.executeQuery(selectStatement);
			
			while(result.next()){
				color = result.getString("Window_Color");
			}
			
			conn.close();
			System.out.println("[Window Color Retrieve] Connection closed.\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return color;
	}
	
}
