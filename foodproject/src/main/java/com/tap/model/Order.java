package com.tap.model;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private int userId;
    private int restaurantId;
    private Timestamp orderDate;
    private double totalAmount;
    private String address;
    private String status;
    private String paymentMethod;
    private String orderItemsJson;

    public Order() {}

    // Getters & Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getRestaurantId() { return restaurantId; }
    public void setRestaurantId(int restaurantId) { this.restaurantId = restaurantId; }

    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getOrderItemsJson() { return orderItemsJson; }
    public void setOrderItemsJson(String orderItemsJson) { this.orderItemsJson = orderItemsJson; }
}
