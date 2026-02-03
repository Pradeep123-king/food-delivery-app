package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.model.Restaurant;

public class RestaurantDaoImpl implements RestaurantDAO {

   
    private static final String URL = "jdbc:mysql://localhost:3306/food_application";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Pradeep@8951501954";

  
    private Connection getConnection() throws SQLException 
    {
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public int addRestaurant(Restaurant restaurant) {

        String sql = "INSERT INTO restaurant "
                + "(Restaurant_name, Address, Rating, Cuisine, Delivery_time, Is_Active, image_URL, User_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, restaurant.getRestaurantName());
            ps.setString(2, restaurant.getAddress());
            ps.setDouble(3, restaurant.getRating());
            ps.setString(4, restaurant.getCuisine());
            ps.setString(5, restaurant.getDeliveryTime());
            ps.setBoolean(6, restaurant.isActive());
            ps.setString(7, restaurant.getImageURL());
            ps.setInt(8, restaurant.getUserId());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {

        String sql = "SELECT * FROM restaurant WHERE Restaurant_id = ?";
        Restaurant res = null;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                res = new Restaurant();
                res.setRestaurantId(rs.getInt("Restaurant_id"));
                res.setRestaurantName(rs.getString("Restaurant_name"));
                res.setAddress(rs.getString("Address"));
                res.setRating(rs.getDouble("Rating"));
                res.setCuisine(rs.getString("Cuisine"));
                res.setDeliveryTime(rs.getString("Delivery_time"));
                res.setActive(rs.getBoolean("Is_Active"));
                res.setImageURL(rs.getString("image_URL"));
                res.setUserId(rs.getInt("User_id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {

        List<Restaurant> list = new ArrayList<>();
        String sql = "SELECT * FROM restaurant";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Restaurant res = new Restaurant();
                res.setRestaurantId(rs.getInt("Restaurant_id"));
                res.setRestaurantName(rs.getString("Restaurant_name"));
                res.setAddress(rs.getString("Address"));
                res.setRating(rs.getDouble("Rating"));
                res.setCuisine(rs.getString("Cuisine"));
                res.setDeliveryTime(rs.getString("Delivery_time"));
                res.setActive(rs.getBoolean("Is_Active"));
                res.setImageURL(rs.getString("image_URL"));
                res.setUserId(rs.getInt("User_id"));

                list.add(res);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

   
    @Override
    public int updateRestaurant(Restaurant restaurant) {

        String sql = "UPDATE restaurant SET "
                + "Restaurant_name=?, Address=?, Rating=?, Cuisine=?, Delivery_time=?, "
                + "Is_Active=?, image_URL=?, User_id=? "
                + "WHERE Restaurant_id=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, restaurant.getRestaurantName());
            ps.setString(2, restaurant.getAddress());
            ps.setDouble(3, restaurant.getRating());
            ps.setString(4, restaurant.getCuisine());
            ps.setString(5, restaurant.getDeliveryTime());
            ps.setBoolean(6, restaurant.isActive());
            ps.setString(7, restaurant.getImageURL());
            ps.setInt(8, restaurant.getUserId());
            ps.setInt(9, restaurant.getRestaurantId());

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteRestaurant(int restaurantId) {

        String sql = "DELETE FROM restaurant WHERE Restaurant_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

	
    @Override
    public List<Restaurant> searchRestaurants(String keyword) {

        List<Restaurant> list = new ArrayList<>();

        String sql = "SELECT * FROM restaurant WHERE Restaurant_name LIKE ? OR Cuisine LIKE ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Restaurant res = new Restaurant();
                res.setRestaurantId(rs.getInt("Restaurant_id"));
                res.setRestaurantName(rs.getString("Restaurant_name"));
                res.setAddress(rs.getString("Address"));
                res.setRating(rs.getDouble("Rating"));
                res.setCuisine(rs.getString("Cuisine"));
                res.setDeliveryTime(rs.getString("Delivery_time"));
                res.setActive(rs.getBoolean("Is_Active"));
                res.setImageURL(rs.getString("image_URL"));
                res.setUserId(rs.getInt("User_id"));

                list.add(res);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    
}
