package com.capstone.cars;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.mongodb.client.model.Filters;
import java.util.Arrays;

import com.capstone.cars.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.mongodb.client.model.Filters.eq;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
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
    	
    	forwardListCars(req, resp, "/index.jsp");
    }
    
    private void forwardListCars(HttpServletRequest req, HttpServletResponse resp, String nextJSP) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

    	

    	List<Car> list = DBInteract.readDocsFromCollection(user.getEmail());
    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
    	req.setAttribute("carList", list);
    	dispatcher.forward(req, resp);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		
		String id = request.getParameter("carId");
		
		switch (action) {
		case "add":
			addCarAction(request, response);
			break;
		case "edit":
			editCarAction(request, response, id);
			break;
		case "delete":
			deleteCarAction(request, response, id);
			break;

		}
		
		
	}
	
	private void addCarAction (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		
		String make = req.getParameter("make");
		String model = req.getParameter("model");
		String year = req.getParameter("year");
		String mileage = req.getParameter("mileage");
		mileage = mileage.replace(",", "");
		int numParsed = Integer.parseInt(mileage);
		Car c = new Car(make, model, year, NumberFormat.getNumberInstance(Locale.US).format(numParsed), user.getEmail());
		
    	
    	
		
		Document doc = CarConverter.toMongoDoc(c);
		DBInteract.storeInDB(doc);
    	String nextJSP = "/index.jsp";
		forwardListCars(req, resp, nextJSP);
		
		
	}
	
	private void deleteCarAction (HttpServletRequest req, HttpServletResponse resp, String id) throws ServletException, IOException {
		
		ObjectId o = new ObjectId(id);
		Bson filter = eq("_id", o);
		
		try {
			UserDAO.deleteHistory(id);
		} catch (SQLException | ClassNotFoundException ex) {
			
			throw new ServletException(ex);
		}
		DBInteract.deleteDocInDB(filter);
		
    	String nextJSP = "/index.jsp";
		forwardListCars(req, resp, nextJSP);
		
		
	}
	
	private void editCarAction (HttpServletRequest req, HttpServletResponse resp, String id) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		String make = req.getParameter("make");
		String model = req.getParameter("model");
		String year = req.getParameter("year");
		String mileage = req.getParameter("mileage");
		Car c = new Car(make, model, year, mileage, user.getEmail());
		
		DBInteract.updateCar(c, id);
    	String nextJSP = "/index.jsp";
		forwardListCars(req, resp, nextJSP);
		
		
	}
	

		
		
	
	

}
