package com.tap.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.tap.daoimpl.OrderDAOImpl;
import com.tap.model.Cart;
import com.tap.model.Order;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get logged-in user
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Cart cart = (Cart) session.getAttribute("cart");
        Integer restaurantId = (Integer) session.getAttribute("restaurantId");

        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        String address = request.getParameter("address");
        String paymentMethod = request.getParameter("paymentMethod");

        double totalAmount = cart.getTotalPrice();

        try {
            Order order = new Order();
            order.setUserId(user.getUserId());
            order.setRestaurantId(restaurantId);
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setTotalAmount(totalAmount);
            order.setPaymentMethod(paymentMethod);
            order.setStatus("Pending");
            order.setAddress(address);

            OrderDAOImpl dao = new OrderDAOImpl();
            int orderId = dao.addOrder(order);

            session.setAttribute("orderId", orderId);
            session.removeAttribute("cart");

            response.sendRedirect("orderconfirm.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
