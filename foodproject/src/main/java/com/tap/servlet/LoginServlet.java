package com.tap.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDaoImpl userDAO = new UserDaoImpl();
        User user = null;

        try {
            user = userDAO.loginUser(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user == null) {
            req.setAttribute("error", "Invalid Email or Password");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("loggedInUser", user); // ✅ ONLY THIS

        resp.sendRedirect("Restaurants");
    }
}
