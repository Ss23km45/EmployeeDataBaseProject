package com.coding.ninja.jsp.webapp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.coding.ninja.jsp.webapp.server.Employee;

public class EmployeeDBUtil {
	
	private List<Employee> employeeList;
	private DataSource datasource;
	private Connection myconn;
	private Statement mystmt;
	private ResultSet myset;
	private static final String MY_SQL_STRING = "SELECT * FROM EMPLOYEE";
	private String fname,lname,esal,eemail;
	private int empId;
	
	public List<Employee> getEmployeeList() {
		getEmployeeDetailsfromDB();
		return employeeList;
	}



	private void getEmployeeDetailsfromDB() {
		// TODO Auto-generated method stub
		//Initialize the Connection as Global
		employeeList = new ArrayList<>();
		
		try {
			myconn = datasource.getConnection();
			mystmt = myconn.createStatement();
			myset = mystmt.executeQuery(MY_SQL_STRING);
			while(myset.next()) {
				fname = myset.getString("first_name");
				lname = myset.getString("last_name");
				eemail = myset.getString("email");
				esal = myset.getString("sal");
				empId = myset.getInt("id");
				
				employeeList.add(new Employee(empId, fname, esal, lname, eemail));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(myconn, mystmt, myset);
		}
		
	}
		
	
	private void close(Connection myconn2, Statement mystmt2, ResultSet myset2) {
		try {
			if(myconn2!=null) {
				myconn2.close();
			}
			
			if(mystmt2!=null) {
				mystmt2.close();
			}
			
			if(myset2!=null) {
				myset2.close();
			}
			
		}catch(Exception ee) {
			
			ee.printStackTrace();
		}
		
	}



	public EmployeeDBUtil(DataSource datasource) {
		this.datasource = datasource;
	}
	
	
	
}
