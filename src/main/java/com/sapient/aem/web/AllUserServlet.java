package com.sapient.aem.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sapient.aem.Exception.UserException;
import com.sapient.aem.model.User;
import com.sapient.aem.service.UserService;
import com.sapient.aem.service.UserServiceImpl;

/**
 * Servlet implementation class AllUserServlet
 */
@WebServlet("/alluser")
public class AllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService= new UserServiceImpl();
	private Logger logger= Logger.getLogger(AllUserServlet.class);


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//fetch all employees from database into List
			List<User> userList=  userService.getUsers();
			//Place employeeList object in request object
			request.setAttribute("userList", userList);
			//dispatch employeeList to show-all-emp.jsp
			request.getRequestDispatcher("WEB-INF/views/show-all-user.jsp")
										.forward(request, response);
			
		}catch(UserException e) {
			logger.error(e.getMessage(),e);
		}catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
