package com.tap.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDAO;
import com.tap.model.OrderItem;

public class OrderItemDAOImpl implements OrderItemDAO {

    private Connection connection;

    public OrderItemDAOImpl() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/food_application",
                "root",
                "Pradeep@8951501954"
        );
    }


    @Override
    public void addOrderItem(OrderItem item) throws Exception {

        String sql = "INSERT INTO orderitem (Order_id, Menu_id, Quantity, Item_total) "
                   + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, item.getOrderId());
            stmt.setInt(2, item.getMenuId());
            stmt.setInt(3, item.getQuantity());
            stmt.setDouble(4, item.getItemTotal());

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Creating OrderItem failed, no rows affected.");
            }

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    item.setOrderItemId(keys.getInt(1));
                } else {
                    throw new SQLException("Creating OrderItem failed, no ID obtained.");
                }
            }
        }
    }


    // -------------------------------------------------------------------
    // GET ORDER ITEM BY ID
    // -------------------------------------------------------------------
    @Override
    public OrderItem getOrderItemById(int orderItemId) throws Exception {

        String sql = "SELECT * FROM orderitem WHERE OrderItem_id = ?";
        OrderItem item = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, orderItemId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                item = mapOrderItem(rs);
            }
        }

        return item;
    }


    // -------------------------------------------------------------------
    // GET ORDER ITEMS BY ORDER ID
    // -------------------------------------------------------------------
    @Override
    public List<OrderItem> getOrderItemsByOrder(int orderId) throws Exception {

        String sql = "SELECT * FROM orderitem WHERE Order_id = ?";
        List<OrderItem> list = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, orderId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapOrderItem(rs));
            }
        }

        return list;
    }


    // -------------------------------------------------------------------
    // UPDATE ORDER ITEM
    // -------------------------------------------------------------------
    @Override
    public boolean updateOrderItem(OrderItem item) throws Exception {

        String sql = "UPDATE orderitem SET Menu_id = ?, Quantity = ?, Item_total = ? "
                   + "WHERE OrderItem_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, item.getMenuId());
            stmt.setInt(2, item.getQuantity());
            stmt.setDouble(3, item.getItemTotal());
            stmt.setInt(4, item.getOrderItemId());

            return stmt.executeUpdate() > 0;
        }
    }


    // -------------------------------------------------------------------
    // DELETE ORDER ITEM
    // -------------------------------------------------------------------
    @Override
    public boolean deleteOrderItem(int orderItemId) throws Exception {

        String sql = "DELETE FROM orderitem WHERE OrderItem_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, orderItemId);
            return stmt.executeUpdate() > 0;
        }
    }


    // -------------------------------------------------------------------
    // MAPPING METHOD
    // -------------------------------------------------------------------
    private OrderItem mapOrderItem(ResultSet rs) throws Exception {

        return new OrderItem(
                rs.getInt("OrderItem_id"),
                rs.getInt("Order_id"),
                rs.getInt("Menu_id"),
                rs.getInt("Quantity"),
                rs.getDouble("Item_total")
        );
    }
}
