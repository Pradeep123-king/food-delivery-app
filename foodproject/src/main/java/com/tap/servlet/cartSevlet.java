package com.tap.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class cartSevlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        int newRestaurantId = parseInteger(req.getParameter("restaurantId"), 0);
        Integer oldRestaurantId = (Integer) session.getAttribute("restaurantId");

 
        if (cart == null || oldRestaurantId == null || oldRestaurantId != newRestaurantId) {
            cart = new Cart();
            session.setAttribute("cart", cart);
            session.setAttribute("restaurantId", newRestaurantId);
        }

        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                addItemToCart(req, cart);
            } else if ("update".equals(action)) {
                updateItemCart(req, cart);
            } else if ("delete".equals(action)) {
                deleteItemFromCart(req, cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.sendRedirect("cart.jsp");
    }

    private void addItemToCart(HttpServletRequest req, Cart cart) 
            throws ClassNotFoundException, SQLException {

        int itemId = parseInteger(req.getParameter("itemId"), -1);
        int quantity = parseInteger(req.getParameter("quantity"), 1);

        if (itemId == -1) return; 

        MenuDAO menuDAO = new MenuDaoImpl();
        Menu menu = menuDAO.getMenuById(itemId);

        if (menu != null) {
            
            CartItem item = new CartItem(
                menu.getMenuId(),   
                menu.getMenuName(),
                quantity,           
                menu.getPrice()     
            );
            cart.addItem(item);
        }
    }

   
    private void updateItemCart(HttpServletRequest req, Cart cart) {
        int itemId = parseInteger(req.getParameter("itemId"), -1);
        int quantity = parseInteger(req.getParameter("quantity"), 1);
        if (itemId != -1) cart.updateItem(itemId, quantity);
    }

   
    private void deleteItemFromCart(HttpServletRequest req, Cart cart) {
        int itemId = parseInteger(req.getParameter("itemId"), -1);
        if (itemId != -1) cart.removeItem(itemId);
    }

    // Utility method to safely parse integers 
    private int parseInteger(String str, int defaultValue) {
        if (str == null || str.trim().isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}







/*

import java.io.IOException;
import java.sql.SQLException;

import com.tap.dao.MenuDAO;
import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class cartSevlet extends HttpServlet
{
     protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
     {
    	 HttpSession session=req.getSession();
    	 Cart cart=(Cart)session.getAttribute("cart");
    	 
    	 int oldRestaurantId=(int)session.getAttribute("restaurantId");
    	 
    	 int newRestaurantId =Integer.parseInt(req.getParameter("restaurantId"));
    	 
    	 if(cart ==null || oldRestaurantId !=newRestaurantId)
          {
	          Cart newCart =new Cart();
	          session.setAttribute("cart", newCart);
	          
	          session.setAttribute("restaurantId", newRestaurantId);

	         
          }
    	 
    	 String action=req.getParameter("action");
    	 if(action.equals("add"))
    	 {
    		 addItemToCart(req,cart);
    	 }
    	 else if(action.equals("update"))
    	 {
    		 updateItemCart(req,cart);
    	 }
    	 else if(action.equals("delete"))
    	 {
    		 deleteItemFromCart(req,cart);
    	 }
    	 resp.sendRedirect("cart.jsp");
    	 
     }

	 private void addItemToCart(HttpServletRequest req,Cart cart) throws ClassNotFoundException,SQLException
	 
	 {
		 int itemId=Integer.parseInt(req.getParameter("itemId"));
		 int quantity=Integer.parseInt(req.getParameter("quantity"));
		 
		 MenuDAO menuDAO=new MenuDaoImpl();
		 
		 Menu menu=menuDAO.getMenuById(itemId);
		 
		// String itemName=menu.getMenuName();
		 //double price=menu.getPrice();
		 
		 System.out.println("The menu in Cart Servlet"+menu);//IT IS FROM THE 62 REFERNCE;
		 
		 if(menu!=null)
		 {
			 CartItem item=new CartItem(menu.getMenuId(),menu.getRestaurantId(),menu.getItemName(),quantity,menu.getPrice() );
			 
			 cart.addItem(item);
		 }
	 }

	 private void updateItemCart(HttpServletRequest req,Cart cart)
	 {
		 int itemId=Integer.parseInt(req.getParameter("itemId"));
		 int quantity=Integer.parseInt(req.getParameter("quantity"));
		 cart.updateItem(itemId,quantity);
		
		
	  }

	 private void deleteItemFromCart(HttpServletRequest req,Cart cart)
	 {
		 int itemId=Integer.parseInt(req.getParameter("itemId"));
		 cart.removeItem(itemId);
		
		
	 }
}*/
