package com.sapient.aem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


import com.sapient.aem.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public List<User> getUsers() throws SQLException {

		String sql= "select * from user";
		Connection connection=null;
		try {

			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/emsDB");
			connection= dataSource.getConnection();
			Statement statement= 
					connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_UPDATABLE);

			ResultSet resultSet= statement.executeQuery(sql);
			int rowcount=0;
			if ( resultSet.last()) {
				rowcount =  resultSet.getRow();
				resultSet.beforeFirst(); // not  resultSet.first() because the  resultSet.next() below will move on, missing the first element
			}
			List<User> employeeList=new ArrayList<>();			
			while(resultSet.next()) {
				User employee= new User();
				populateUser(resultSet,employee);
				employeeList.add(employee);				
			}

			return employeeList;

		}catch(SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}
	}

	private void populateUser(ResultSet resultSet, User user) throws SQLException {
		
		user.setUserId(resultSet.getInt("userId"));
		user.setUserName(resultSet.getString("uname"));
		user.setPassword(resultSet.getString("password"));
		
		
		
	}

	@Override
	public Boolean isValidUser(String userName, String password) throws SQLException {
		String sql="select * from registered_users where user_name=? and password=?";
		Connection connection=null;
		try {
			Context envContext = (Context) new InitialContext().lookup("java:comp/env");		
			DataSource dataSource = (DataSource) envContext.lookup("jdbc/emsDB");
			connection= dataSource.getConnection();
			PreparedStatement preparedStatement= connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, password);
			ResultSet resultSet= preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			throw e;
		}catch(Exception e) {
			throw new SQLException(e.getMessage());
		}finally {
			connection.close();
		}
	}

}


