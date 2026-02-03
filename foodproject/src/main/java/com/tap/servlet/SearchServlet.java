package com.tap.servlet;
import java.io.IOException;
import java.util.List;

import com.tap.dao.MenuDAO;
import com.tap.dao.RestaurantDAO;
import com.tap.daoimpl.MenuDaoImpl;
import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Menu;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private RestaurantDAO restaurantDAO;
    private MenuDAO menuDAO;

    public SearchServlet() {
        try {
            restaurantDAO = new RestaurantDaoImpl();
            menuDAO = new MenuDaoImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");

        if (keyword == null || keyword.trim().isEmpty()) {
            response.sendRedirect("serach.jsp");   // ✔ correct
            return;
        }

        List<Restaurant> restaurants = restaurantDAO.searchRestaurants(keyword);
        List<Menu> menuItems = menuDAO.searchMenu(keyword);

        if (!restaurants.isEmpty()) {
            request.setAttribute("restaurants", restaurants);
            request.getRequestDispatcher("reata.jsp").forward(request, response);  // ✔ correct
        }
        else if (!menuItems.isEmpty()) {
            request.setAttribute("menus", menuItems);
            request.getRequestDispatcher("Menu.jsp").forward(request, response);  // ✔ correct
        }
        else {
            request.setAttribute("message", "No matching results found.");
            request.getRequestDispatcher("serach.jsp").forward(request, response);  // ✔ correct
        }
    }
}
