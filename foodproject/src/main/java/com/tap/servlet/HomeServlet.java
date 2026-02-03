package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Restaurants")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            RestaurantDaoImpl dao = new RestaurantDaoImpl();

            List<Restaurant> allRestaurants = dao.getAllRestaurants();

            req.setAttribute("restaurants", allRestaurants);
            RequestDispatcher rd=req.getRequestDispatcher("reata.jsp");
            
            rd.forward(req,resp);
            

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}


















/*package com.tap.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.tap.dao.RestaurantDAO;
import com.tap.daoimpl.RestaurantDaoImpl;
import com.tap.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class homesevlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	        throws ServletException, IOException {

	    try {
	        RestaurantDaoImpl restaurantDaoImpl = new RestaurantDaoImpl();

	        
	        List<Restaurant> allRestaurant = restaurantDaoImpl.getAllRestaurants();
	        
	        
	        for(Restaurant restaurant :allRestaurant)
	        {
	        	System.out.println( restaurant);
	        }
	        


	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new ServletException(e);
	    }
	}

		
}*/
		
		
		
		
		
		
		
		
		


	   
	  
	  
  

