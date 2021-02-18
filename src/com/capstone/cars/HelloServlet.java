package com.capstone.cars;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/helloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	forwardListCars(req, resp);
    }
    
    private void forwardListCars(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String nextJSP = "/index.jsp";
    	List<Car> list = DBInteract.readDocsFromCollection();
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
    	req.setAttribute("carList", list);
    	dispatcher.forward(req, resp);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		switch (action) {
		case "add":
			addCarAction(request, response);
			break;
		case "edit":
			addCarAction(request, response);
			break;
		case "delete":
			addCarAction(request, response);
			break;
		}
		
		
	}
	
	private void addCarAction (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String make = req.getParameter("make");
		String model = req.getParameter("model");
		String year = req.getParameter("year");
		String mileage = req.getParameter("mileage");
		Car c = new Car(make, model, year, mileage);
		
		Document doc = CarConverter.toMongoDoc(c);
		DBInteract.storeInDB(doc);
		
		forwardListCars(req, resp);
		
		
	}
	

}
