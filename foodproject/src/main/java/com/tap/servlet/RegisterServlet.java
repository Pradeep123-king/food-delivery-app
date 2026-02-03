package com.tap.servlet;

import java.io.IOException;

import com.tap.dao.UserDao;
import com.tap.daoimpl.UserDaoImpl;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDao userDAO = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phonenumber");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String role = request.getParameter("role");

        // Validate required fields
        if (username == null || username.isEmpty() ||
            email == null || email.isEmpty() ||
            password == null || password.isEmpty()) {
            request.setAttribute("msg", "Please fill in all required fields!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Create User object
        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPhoneNumber(phone);
        user.setPassword(password);
        user.setAddress(address);
        user.setRole(role); // DAO will default to "Customer" if null

        // Call DAO
        UserDao.RegisterResult result = userDAO.registerUser(user);

        // Handle result
        switch (result) {
            case SUCCESS:
                response.sendRedirect("login.jsp");
                break;
            case EMAIL_EXISTS:
                request.setAttribute("msg", "Registration Failed! Email already exists.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                break;
            case ERROR:
            default:
                request.setAttribute("msg", "Registration Failed! Something went wrong.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                break;
        }
    }
}
