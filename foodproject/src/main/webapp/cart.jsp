<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.tap.model.Cart, com.tap.model.CartItem" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" href="cart.css">
</head>
<body>

<div class="cart-wrapper">

<%
    Cart cart = (Cart) session.getAttribute("cart");
    Integer restaurantId = (Integer) session.getAttribute("restaurantId");
%>

<% if (cart != null && !cart.getItems().isEmpty()) { %>

    <% for (CartItem item : cart.getItems().values()) { %>

    <div class="cart-item">

        <div class="item-header">
            <h2><%= item.getName() %></h2>
            <p class="price">₹ <%= item.getPrice() * item.getQuantity() %></p>
        </div>

        <div class="item-body">
            <p class="small-price">₹ <%= item.getPrice() %> each</p>

            <div class="quantity-controls">

                <!-- Decrease -->
                <form action="cart" method="post">
                    <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
                    <button class="qty-btn">−</button>
                </form>

                <span class="qty-number"><%= item.getQuantity() %></span>

                <!-- Increase -->
                <form action="cart" method="post">
                    <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                    <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
                    <button class="qty-btn">+</button>
                </form>

            </div>

            <!-- Remove -->
            <form action="cart" method="post">
                <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                <input type="hidden" name="action" value="delete">
                <button class="remove-btn">Remove</button>
            </form>

        </div>
    </div>

    <% } %>

    <div class="total-row">
        <h2>Grand Total</h2>
        <h2>₹ <%= cart.getTotalPrice() %></h2>
    </div>

    <div class="btn-center">
        <a class="btn add-more" href="menu?restaurantId=<%= restaurantId %>">
            Add More Items
        </a>
    </div>

    <form action="checkout.jsp" method="post" class="btn-center">
        <button class="btn checkout">Proceed to Checkout</button>
    </form>

<% } else { %>

    <p class="empty">Your cart is empty.</p>

    <div class="btn-center">
        <a class="btn add-more" href="menu?restaurantId=<%= restaurantId %>">
            Add Items
        </a>
    </div>

<% } %>

</div>

</body>
</html>
