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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		
		switch (action) {
		case "login":
			loginAction(request, response);
			break;
		case "register":
			registerAction(request, response);
			break;
		}
	}
	
	private void loginAction (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDAO userDao = new UserDAO();
		
		try {
			User user = userDao.checkLogin(email, password);
			String destPage = "login.jsp";
			
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				destPage = "/index.jsp";
			}
			else {
				String message = "Invalid email/password";
				request.setAttribute("message", message);
			}
			
			

	    	

	    	List<Car> list = DBInteract.readDocsFromCollection(user.getEmail());
	    	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(destPage);
	    	request.setAttribute("carList", list);
	    	dispatcher.forward(request, response);
	    	
		} catch (SQLException | ClassNotFoundException ex) {
			throw new ServletException(ex);
		}
		
		
	}
	
	private void registerAction (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String name = request.getParameter("name");
		
		UserDAO userDao = new UserDAO();
		
		try {
			String user = userDao.registerUser(email, password, repassword, name);
			String destPage = "register.jsp";
			
			switch (user) {
			case "Email is already registered. Please try a different email or login using that email":
				request.setAttribute("message", user);
				break;
			case "Passwords didn't match. Try again.":
				request.setAttribute("message", user);
				break;
			case "Succesfully registered":
				destPage = "login.jsp";
				request.setAttribute("message", user);
				break;
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
			dispatcher.forward(request, response);
		} catch (SQLException | ClassNotFoundException ex) {
			throw new ServletException(ex);
		}
	}

}
