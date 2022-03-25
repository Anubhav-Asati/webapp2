package com.sapient.aem.service;

import java.sql.SQLException;
import java.util.List;

import com.sapient.aem.Exception.UserException;
import com.sapient.aem.dao.UserDAO;
import com.sapient.aem.dao.UserDAOImpl;

import com.sapient.aem.model.User;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDao=new UserDAOImpl();

	@Override
	public List<User> getUsers() throws UserException {
		try {
			List<User> userList= userDao.getUsers();
			//any filtering
			return userList; //returning array of employees to UI layer
		}catch(SQLException e) {
			//converting SQLException to EmployeeException
			throw new UserException(e.getMessage(),e);
		}
	}

	@Override
	public Boolean isValidUser(String userName, String password) throws UserException {
		try {
			return userDao.isValidUser(userName, password);
		}catch(Exception e){
			throw new UserException(e.getMessage(),e);
		}
	}
}
