<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.tap.model.Menu" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Restaurant Menu</title>
<link rel="stylesheet" href="menu.css?v=5">
</head>
<body>

<%
List<Menu> menuList = (List<Menu>) request.getAttribute("menus");
Integer restaurantId = (Integer) request.getAttribute("restaurantId");
%>

<% if (menuList != null && !menuList.isEmpty()) { %>

<div class="menu-container">
<% for (Menu m : menuList) { %>

<div class="menu-item">

    <div class="menu-img-box">
        <img src="<%= m.getImageUrl() %>" alt="Image Not Found">
    </div>

    <div class="menu-content">
        <h3><%= m.getMenuName() %></h3>
        <p><%= m.getDescription() %></p>
        <p class="price">₹<%= m.getPrice() %></p>

        <form action="cart" method="post">
            <input type="hidden" name="restaurantId" value="<%= restaurantId %>">
            <input type="hidden" name="itemId" value="<%= m.getMenuId() %>">
            <input type="hidden" name="quantity" value="1">
            <input type="hidden" name="action" value="add">
            <button class="add-to-cart-btn">Add to Cart</button>
        </form>
    </div>

</div>

<% } %>
</div>

<% } else { %>
<h2 style="color:red;">No menu items found.</h2>
<% } %>

</body>
</html>
