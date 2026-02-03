package com.tap.daoimpl;

import com.tap.dao.OrderDAO;
import com.tap.model.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private Connection connection;

    public OrderDAOImpl() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/food_application", "root", "Pradeep@8951501954");
    }

    @Override
    public int addOrder(Order order) throws Exception {
        String sql = "INSERT INTO orders (Order_date, Restaurant_id, UserID, Total_amount, Payment_method, Status, Address, Order_Items) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, order.getOrderDate());
            stmt.setInt(2, order.getRestaurantId());
            stmt.setInt(3, order.getUserId());
            stmt.setDouble(4, order.getTotalAmount());
            stmt.setString(5, order.getPaymentMethod());
            stmt.setString(6, order.getStatus());
            stmt.setString(7, order.getAddress());
            stmt.setString(8, order.getOrderItemsJson());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        }
        return 0;
    }

    private Order mapOrder(ResultSet rs) throws Exception {
        Order o = new Order();
        o.setOrderId(rs.getInt("Order_id"));
        o.setUserId(rs.getInt("UserID"));
        o.setRestaurantId(rs.getInt("Restaurant_id"));
        o.setOrderDate(rs.getTimestamp("Order_date"));
        o.setTotalAmount(rs.getDouble("Total_amount"));
        o.setPaymentMethod(rs.getString("Payment_method"));
        o.setStatus(rs.getString("Status"));
        o.setAddress(rs.getString("Address"));
        o.setOrderItemsJson(rs.getString("Order_Items"));
        return o;
    }

    @Override
    public Order getOrderById(int orderId) throws Exception {
        String sql = "SELECT * FROM orders WHERE Order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return mapOrder(rs);
        }
        return null;
    }

    @Override
    public List<Order> getOrdersByUser(int userId) throws Exception {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE UserID = ? ORDER BY Order_date DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) list.add(mapOrder(rs));
        }
        return list;
    }

    @Override
    public List<Order> getOrdersByRestaurant(int restaurantId) throws Exception {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE Restaurant_id = ? ORDER BY Order_date DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, restaurantId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) list.add(mapOrder(rs));
        }
        return list;
    }

    @Override
    public boolean updateOrderStatus(int orderId, String status) throws Exception {
        String sql = "UPDATE orders SET Status = ? WHERE Order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setInt(2, orderId);
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteOrder(int orderId) throws Exception {
        String sql = "DELETE FROM orders WHERE Order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            return stmt.executeUpdate() > 0;
        }
    }
}
