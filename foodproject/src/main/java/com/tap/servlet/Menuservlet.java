package com.tap.servlet;

import java.io.IOException;
import java.util.List;

import com.tap.daoimpl.MenuDaoImpl;
import com.tap.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class Menuservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("restaurantId");

        // Validate parameter
        if (idParam == null || idParam.trim().isEmpty()) {
            resp.getWriter().println("<h2 style='color:red'>Error: restaurantId is missing in URL</h2>");
            return;
        }

        int restaurantId = Integer.parseInt(idParam);

        // DAO fetch
        MenuDaoImpl dao = new MenuDaoImpl();
        List<Menu> menuList = dao.getMenusByRestaurantId(restaurantId);

        // Set attributes
        req.setAttribute("menus", menuList);
        req.setAttribute("restaurantId", restaurantId);

        // Forward
        RequestDispatcher rd = req.getRequestDispatcher("Menu.jsp");
        rd.forward(req, resp);
    }
}

































/*@WebServlet("/menu")
public class Menuservlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{

       
		int rid=Integer.parseInt(req.getParameter("restaurantId"));
	
		
		MenuDaoImpl daoImpl=new MenuDaoImpl();
		
		 List<Menu> menuList = daoImpl.getMenusByRestaurantId(rid);
		 
		 req.setAttribute("menus", menuList);
		 
		 RequestDispatcher rd=req.getRequestDispatcher("Menu.jsp");
		 
		 rd.forward(req,resp);
	}

}*/
