package com.coding.ninja.jsp.webapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	private PreparedStatement pstmt;
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
				empId = myset.getInt("id");
				fname = myset.getString("first_name");
				lname = myset.getString("last_name");
				eemail = myset.getString("email");
				esal = myset.getString("sal");
				
				
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



	public void setNewEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
		//CreateConnection details first
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = datasource.getConnection();
			
			String SQL = "INSERT INTO EMPLOYEE (first_name, last_name, email, sal) values (?,?,?,?)";
			pstmt = conn.prepareStatement(SQL);
			//Read the Elements from Employee Object.//Setup the SQL String
			pstmt.setString(1, emp.getEmpFname());
			pstmt.setString(2, emp.getEmpLname());
			pstmt.setString(3, emp.getEmpEmail());
			pstmt.setString(4, emp.getEmpSal());
			//Execute the SQL//INSERT our DB with the Student Details.
			pstmt.execute();
		}catch(Exception ee) {
			ee.printStackTrace();
		}finally {
			close(conn,pstmt,null);
		}
		
		
	}



	public Employee getStudent(String employeeId) throws Exception{
		// TODO Auto-generated method stub
		// We are storing the studentID in Integer in DB so parse it to INT
		int empid = Integer.parseInt(employeeId);
		//Create a SQL String
		String sql = "select * from employee where id=?";
		//Initialize all DB related Objects
		Connection comm = null;
		PreparedStatement stmt = null;
		ResultSet set = null;
		try{
			comm = datasource.getConnection();
		
			stmt= comm.prepareStatement(sql);
			stmt.setInt(1, empid);
			set = stmt.executeQuery();
			
			if(set.next()) {
				empId = set.getInt("id");
				fname = set.getString("first_name");
				lname = set.getString("last_name");
				eemail = set.getString("email");
				esal = set.getString("sal");
				
				Employee emp = new Employee(empId,fname,esal,lname,eemail);
				return emp;
			}
			
		}catch(Exception ee) {
			throw new Exception("DEtails not found in DataBase");
		}finally {
			close(comm,stmt,set);
		}
		
		return null;
		
		
		
	}



	public void updateStudent(int empId2, String fname2, String lname2, String esal2, String email) {
		// TODO Auto-generated method stub
		String sql = "UPDATE SET first_name=?, last_name=?, email=?,sal=? where id =?";
		try {
			myconn = datasource.getConnection();
			pstmt = myconn.prepareStatement(sql);
			pstmt.setString(1, fname2);
			pstmt.setString(2, lname2);
			pstmt.setString(3, email);
			pstmt.setString(4, esal2);
			pstmt.setInt(5, empId2);
			
			pstmt.execute();
			
		}catch(Exception ee) {
			ee.printStackTrace();
		}finally {
			close(myconn,pstmt,myset);
		}
		
	}



	public void deleteEmployee(int empId2) {
		// TODO Auto-generated method stub
		//Create SQL String
		String sql = "Delete from employee where id = ?";
		try {
			myconn = datasource.getConnection();
			pstmt = myconn.prepareStatement(sql);
			pstmt.setInt(1, empId2);
			
			pstmt.execute();
			
		}catch(Exception ee) {
			
		}finally {
			close(myconn,pstmt,myset);
		}
	}


	
	
}
