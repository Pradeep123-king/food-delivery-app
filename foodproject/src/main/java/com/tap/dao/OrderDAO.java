package com.tap.dao;

import com.tap.model.Order;
import java.util.List;

public interface OrderDAO {
    int addOrder(Order order) throws Exception;
    Order getOrderById(int orderId) throws Exception;
    List<Order> getOrdersByUser(int userId) throws Exception;
    List<Order> getOrdersByRestaurant(int restaurantId) throws Exception;
    boolean updateOrderStatus(int orderId, String status) throws Exception;
    boolean deleteOrder(int orderId) throws Exception;
}

