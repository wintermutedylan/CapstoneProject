package com.capstone.cars;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class viewRepairs
 */
@WebServlet("/viewRepairs")
public class viewRepairs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewRepairs() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		forwardAttributeList(request, response);
	}
	
	private void forwardAttributeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nextJSP = "/viewCarRepairs.jsp";
		
    	List<Attribute> list = DBInteract.readRepairsFromDocument(req.getParameter("id"));
    	
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
    	req.setAttribute("attributeList", list);
    	dispatcher.forward(req, resp);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String last = request.getParameter("lastUpdated");
		String mileage = request.getParameter("mileage");
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		
		try {
			UserDAO.storeHistory(name, mileage, last, id);
		} catch (SQLException | ClassNotFoundException ex) {
			
			throw new ServletException(ex);
		}
		
		Attribute att = new Attribute(name, mileage, last);
		DBInteract.updateRepair(att, id);
		forwardAttributeList(request, response);
	}

}
