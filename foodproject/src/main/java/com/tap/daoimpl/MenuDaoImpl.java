package com.tap.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.model.Menu;

public class MenuDaoImpl implements MenuDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/food_application";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Pradeep@8951501954";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public boolean addMenu(Menu menu) {
        String sql = "INSERT INTO menu (Menu_name, Price, Is_Available, Description, Image_URL, Restaurant_id)"
                   + " VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, menu.getMenuName());
            ps.setDouble(2, menu.getPrice());
            ps.setBoolean(3, menu.isAvailable());
            ps.setString(4, menu.getDescription());
            ps.setString(5, menu.getImageUrl());
            ps.setInt(6, menu.getRestaurantId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Menu getMenuById(int menuId) {
        String sql = "SELECT * FROM menu WHERE Menu_id = ?";
        Menu menu = null;

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menuId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                menu = mapResultSetToMenu(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return menu;
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> list = new ArrayList<>();
        String sql = "SELECT * FROM menu";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapResultSetToMenu(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean updateMenu(Menu menu) {
        String sql = "UPDATE menu SET Menu_name=?, Price=?, Is_Available=?, Description=?, Image_URL=?, Restaurant_id=? "
                   + "WHERE Menu_id=?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, menu.getMenuName());
            ps.setDouble(2, menu.getPrice());
            ps.setBoolean(3, menu.isAvailable());
            ps.setString(4, menu.getDescription());
            ps.setString(5, menu.getImageUrl());
            ps.setInt(6, menu.getRestaurantId());
            ps.setInt(7, menu.getMenuId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteMenu(int menuId) {
        String sql = "DELETE FROM menu WHERE Menu_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, menuId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Menu> getMenusByRestaurantId(int restaurantId) {
        List<Menu> list = new ArrayList<>();
        String sql = "SELECT * FROM menu WHERE Restaurant_id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, restaurantId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToMenu(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private Menu mapResultSetToMenu(ResultSet rs) throws SQLException {
        Menu menu = new Menu();
        menu.setMenuId(rs.getInt("Menu_id"));
        menu.setMenuName(rs.getString("Menu_name"));
        menu.setPrice(rs.getDouble("Price"));
        menu.setAvailable(rs.getBoolean("Is_Available"));
        menu.setDescription(rs.getString("Description"));
        menu.setImageUrl(rs.getString("Image_URL"));
        menu.setRestaurantId(rs.getInt("Restaurant_id"));
        return menu;
    }

    @Override
    public List<Menu> searchMenu(String keyword) {

        List<Menu> list = new ArrayList<>();

        String sql = "SELECT * FROM menu WHERE Menu_name LIKE ? OR Description LIKE ?";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToMenu(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
