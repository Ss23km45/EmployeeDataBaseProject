package com.coding.ninja.webapps.controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.coding.ninja.webapps.dao.EmployeeDBUtil;
import com.coding.ninja.webapps.server.Employee;

/**
 * Servlet implementation class EmployeeServletController
 */
@WebServlet("/EmployeeServletController")
public class EmployeeServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Employee> empList;
	private EmployeeDBUtil empDBList;
	private RequestDispatcher reqDis;
	
	
	//Resource mappping for DB e should use the name form context.xml

	@Resource(name="jdbc/employeeTracker")
	private DataSource datasource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Here We Can write code but the best practice is to call one searate method from here.
		
		//Readwhich operation we are doing here
		String opr= request.getParameter("something");
		
		if(opr==null) {
			opr = "List";
		}
		
		switch(opr) {
		case "add" :
			addEmpDetails(request, response);
			break;
		case "List":
			forwardEmpDBDetails(request,response);
			break;
		case "LOAD":
			getEmpDetailstoUpdate(request,response);
			break;
		case "update":
			updateEmployeeDetails(request,response);
			break;
		case "DELETE":
			deleteEmployeeDetails(request,response);
			break;
		case "SEARCH":
			searchEmployeeDetails(request,response);
			break;
		default :
			forwardEmpDBDetails(request,response);
			break;
		}
		
	}

	private void searchEmployeeDetails(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//Load the Details from Search button
		String ser = request.getParameter("search");
		List<Employee> listEmp =new ArrayList<>();
		//Call the DAO Service to get the List of EMployee details from it
		listEmp = empDBList.getMatchedResults(ser);
		try {
		//Initialize the dispatcher to call new JSP file to display details
		reqDis = request.getRequestDispatcher("/List_Employee_Search.jsp");
		//Set the Values to dispatcher
		request.setAttribute("EMPLIST", listEmp);
		
		
		//forward the dispatcher
		reqDis.forward(request, response);
			
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteEmployeeDetails(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//read the REQ to get the current employee id 
		String temp = request.getParameter("deleteId");
		int empId= Integer.parseInt(temp);
		
		empDBList.deleteEmployee(empId);
		
		forwardEmpDBDetails(request,response);
		
		
	}

	private void updateEmployeeDetails(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		//read the REQ to get the current employee id 
		String temp = request.getParameter("empid");
		int empId= Integer.parseInt(temp);
		String fname =request.getParameter("firstName");
		String lname =request.getParameter("lastName");
		String esal =request.getParameter("salary");
		String email =request.getParameter("email");
		
		//WE have to Call our Service DAO
		empDBList.updateStudent(empId,fname,lname,esal,email);
		
		forwardEmpDBDetails(request, response);
		
	}

	private void getEmpDetailstoUpdate(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		
		//Read the REQ
		String employeeId = request.getParameter("employeeId");
		
		//Call the DAO service
		Employee emp;
		try {
				emp = empDBList.getStudent(employeeId);
			
			// add Student to REQ
			request.setAttribute("MY_EMP", emp);
			//Create Dispatcher
			RequestDispatcher reqDis1 = request.getRequestDispatcher("/update-employee-form.jsp");
			//forward the dispatcher
			reqDis1.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addEmpDetails(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//Read the Details from incoming REQ
		String fName = request.getParameter("firstName");
		String lName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String sal = request.getParameter("salary");
		
		//Here We Can do Two Things Create a Employee Object or else pass all these as a parameters to our DAO Class
		//Create a Employee Object
		Employee emp = new Employee(fName,sal,lName,email);
		empDBList.setNewEmployee(emp);
		
		forwardEmpDBDetails(request, response);
		
	}

	private void forwardEmpDBDetails(HttpServletRequest request, HttpServletResponse response) {
		//LEts get this coded asap
		
		//Get the details from student dao class to achive that we have to initialize DB and pass it to that class
		empList = empDBList.getEmployeeList();
		//We have to forward this to JSP pages  using RequestDispatcher
		request.setAttribute("EMPLIST", empList);
		
		//get the Dispatcher
		reqDis = request.getRequestDispatcher("/List_Employee_for.jsp");
		
		try {
			reqDis.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		//This method will be called fist since it is main Generic class method
		
		//LEts Call constructor from here
		empDBList = new EmployeeDBUtil(datasource); 
	}

}
