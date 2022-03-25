package com.sapient.aem.dao;

import java.sql.SQLException;
import java.util.List;

import com.sapient.aem.model.User;

public interface UserDAO {
	
	public abstract Boolean isValidUser(String userName,String password) throws SQLException;
	
	public abstract List<User> getUsers() throws SQLException;

}
