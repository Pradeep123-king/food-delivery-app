package com.tap.dao;

import java.util.List;
import com.tap.model.OrderItem;

public interface OrderItemDAO {

    void addOrderItem(OrderItem orderItem) throws Exception;

    OrderItem getOrderItemById(int orderItemId) throws Exception;

    List<OrderItem> getOrderItemsByOrder(int orderId) throws Exception;

    boolean updateOrderItem(OrderItem orderItem) throws Exception;

    boolean deleteOrderItem(int orderItemId) throws Exception;
}

