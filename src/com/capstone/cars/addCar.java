package com.capstone.cars;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

/**
 * Servlet implementation class addCar
 */
@WebServlet("/addCar")
public class addCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forwardListCars(request, response);
		
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		System.out.println(action);
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
