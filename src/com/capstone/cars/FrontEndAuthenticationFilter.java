package com.capstone.cars;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FrontEndAuthenticationFilter
 */
@WebFilter("/*")
public class FrontEndAuthenticationFilter implements Filter {
	private HttpServletRequest httpRequest;
	
	private static final String[] loginRequiredURLs = {
		"/helloServlet", "/viewAttribute", "/updateAttribute.jsp", "/editCar.jsp", "/new-car.jsp"
	};

    /**
     * Default constructor. 
     */
    public FrontEndAuthenticationFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		httpRequest = (HttpServletRequest) request;
		
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		
		if (path.startsWith("/admin/")) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession session = httpRequest.getSession(false);
		
		boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
		
		String loginURI = httpRequest.getContextPath() + "/login";
		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
		boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");
		
		if (isLoggedIn && (isLoginRequest || isLoginPage)) {
			httpRequest.getRequestDispatcher("/").forward(request, response);
		} else if (!isLoggedIn && isLoginRequired()) {
			String loginPage = "/login.jsp";
			RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
			dispatcher.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
		
	}
	
	private boolean isLoginRequired() {
		String requestURL = httpRequest.getRequestURL().toString();
		
		for (String loginRequiredURL : loginRequiredURLs) {
			if (requestURL.contains(loginRequiredURL)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
