package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FoodDao {

	public List<Food> listAllFood(){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_id"),
							res.getInt("food_code"),
							res.getString("display_name"), 
							res.getInt("portion_default"), 
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"),
							res.getDouble("calories")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Condiment> listAllCondiment(){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_id"),
							res.getInt("food_code"),
							res.getString("display_name"), 
							res.getString("condiment_portion_size"), 
							res.getDouble("condiment_calories")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}

	}

	public List<Condiment> getCondiment(int calorie) {
		
		String sql = "SELECT condiment_id, food_code, display_name, condiment_portion_size, condiment_calories " + 
				"FROM condiment " + 
				"WHERE condiment_calories<? ";
		
		List<Condiment> result = new ArrayList<>();
		
				try {
					Connection conn = DBConnect.getConnection() ;
					PreparedStatement st = conn.prepareStatement(sql) ;		
					st.setInt(1, calorie);
					ResultSet res = st.executeQuery() ;
					
					while(res.next()) {
						try {
							result.add( new Condiment(res.getInt("condiment_id"),
									res.getInt("food_code"),
									res.getString("display_name"), 
									res.getString("condiment_portion_size"), 
									res.getDouble("condiment_calories")));
						} catch (Throwable t) {
							t.printStackTrace();
						}
					}
					
					conn.close();
					return result ;

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null ;
				}
		}

	public int getDifferentFood(Integer condiment_id, Integer condiment_id2) {
		String sql = "SELECT COUNT(DISTINCT(f1.food_code)) AS peso " + 
				"FROM food_condiment f1, food_condiment f2 " + 
				"WHERE f1.condiment_food_code = ? AND f2.condiment_food_code = ? " + 
				"AND f1.food_code= f2.food_code ";
		int result = 0;
		try {
			Connection conn = DBConnect.getConnection() ;
			PreparedStatement st = conn.prepareStatement(sql) ;		
			st.setInt(1, condiment_id);
			st.setInt(2, condiment_id2);
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					result = res.getInt("peso");
					}
				 catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}

	}
}
