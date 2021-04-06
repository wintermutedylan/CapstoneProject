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
 * Servlet implementation class viewAttribute
 */
@WebServlet("/viewAttribute")
public class viewAttribute extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewAttribute() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String nextJSP = "/viewCarAttributes.jsp";
    	List<Attribute> list = DBInteract.readAttributesFromDocument(request.getParameter("id"));
    	
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
    	request.setAttribute("attributeList", list);
    	dispatcher.forward(request, response);
    	*/
		forwardAttributeList(request, response);
		
	}
	
	private void forwardAttributeList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nextJSP = "/viewCarAttributes.jsp";
		
    	List<Attribute> list = DBInteract.readAttributesFromDocument(req.getParameter("id"));
    	
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
    	
    		String currCar = req.getParameter("carYear") + " " + req.getParameter("carMake") + " " + req.getParameter("carModel");
    	
    		req.setAttribute("currentCar", currCar);
    	
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
		String c = request.getParameter("current");
		
		request.setAttribute("currentCar", c);
		
		try {
			UserDAO.storeHistory(name, mileage, last, id);
		} catch (SQLException | ClassNotFoundException ex) {
			
			throw new ServletException(ex);
		}
		
		Attribute att = new Attribute(name, mileage, last);
		DBInteract.updateAttribute(att, id);
		forwardAttributeList(request, response);
		
		
		
	}

}
