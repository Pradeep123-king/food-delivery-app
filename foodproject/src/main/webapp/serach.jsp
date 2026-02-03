<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.tap.model.Restaurant" %>
<%@ page import="com.tap.model.Menu" %>

<html>
<head>
<title>Search Results</title>
</head>
<body>

<h2>Search Results</h2>

<%
    List<Restaurant> restaurants = (List<Restaurant>) request.getAttribute("restaurants");
    List<Menu> menuItems = (List<Menu>) request.getAttribute("menuItems");
%>

<h3>Restaurants</h3>
<%
if (restaurants != null && !restaurants.isEmpty()) {
    for (Restaurant r : restaurants) {
%>
    <div>
        <p><strong><%= r.getRestaurantName() %></strong> - <%= r.getCuisine() %></p>
    </div>
<%
    }
} else {
%>
    <p>No restaurants found.</p>
<%
}
%>

<h3>Menu Items</h3>
<%
if (menuItems != null && !menuItems.isEmpty()) {
    for (Menu m : menuItems) {
%>
    <div>
        <p><strong><%= m.getMenuName() %></strong> - Price: <%= m.getPrice() %></p>
    </div>
<%
    }
} else {
%>
    <p>No menu items found.</p>
<%
}
%>

</body>
</html>
