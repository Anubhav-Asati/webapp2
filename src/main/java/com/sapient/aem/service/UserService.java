package com.sapient.aem.service;

import java.sql.SQLException;
import java.util.List;

import com.sapient.aem.Exception.UserException;
import com.sapient.aem.model.User;

public interface UserService {
	
	public abstract List<User> getUsers() throws UserException;
	public abstract Boolean isValidUser(String userName,String password) throws UserException;

}
