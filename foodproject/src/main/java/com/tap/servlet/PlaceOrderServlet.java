package com.tap.servlet;

import com.tap.dao.OrderDAO;
import com.tap.daoimpl.OrderDAOImpl;
import com.tap.model.Order;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // 1?? Get logged-in user
        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2?? Read form values
        double totalAmount = Double.parseDouble(request.getParameter("totalAmount"));
        String paymentMode = request.getParameter("paymentMode");

        // 3?? Create Order object
        Order order = new Order();
        order.setUserId(user.getUserId());   // ? IMPORTANT
        order.setTotalAmount(totalAmount);
        order.setPaymentMethod(paymentMode);
        order.setStatus("PLACED");
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));

        // 4?? Save order to DB
        OrderDAO orderDAO = null;
		try {
			orderDAO = new OrderDAOImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        try {
            orderDAO.addOrder(order);

            // 5?? Clear cart
            session.removeAttribute("cart");

            // 6?? Redirect to servlet (NOT JSP)
            response.sendRedirect("orderList.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
