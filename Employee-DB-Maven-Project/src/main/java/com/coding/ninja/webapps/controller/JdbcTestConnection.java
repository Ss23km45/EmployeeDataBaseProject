package com.coding.ninja.webapps.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class JdbcTestConnection
 */
@WebServlet("/JdbcTestConnection")
public class JdbcTestConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//Get the JDBC connection for Connection pooling using resourse injection
	@Resource(name="jdbc/employeeTracker")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Get the PRintWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		/*EmployeeDBUtil empDB = new EmployeeDBUtil(dataSource);
		try {
			List<Employee> stu = empDB.getEmployeeList();
			for(int i=0;i<stu.size();i++) {
				out.println(stu.get(i).getEmpFname() + "  " + stu.get(i).getEmpEmail());
			}
		}catch(Exception eq) {
			eq.printStackTrace();
		} */
		
		//Define Connection,Statement, ResultSet
		Connection myconn =null;
		Statement mystmt = null;
		ResultSet myset = null;
		String sql;
		
		//Create a SQL statement
		try {
			//Connection
			myconn= dataSource.getConnection();
			//SQL Query
			sql = "SELECT * FROM web_student_tracker.STUDENT";
			//CreateStatement/prepareStatement
			mystmt = myconn.createStatement();
			//Execute the SQL
			myset = mystmt.executeQuery(sql);
			
			//Process the Result
			while(myset.next()){
				String email = myset.getString("email");
				out.println(email);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	

		
	}

}
