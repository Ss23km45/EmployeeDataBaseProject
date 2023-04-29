package com.coding.ninja.webapps.server;

public class Employee {
	
	private int empId;
	private String empFname;
	private String empSal;
	private String empLname;
	private String empEmail;
		
	public Employee(String empFname, String empSal, String empLname, String empEmail) {
		super();
		this.empFname = empFname;
		this.empSal = empSal;
		this.empLname = empLname;
		this.empEmail = empEmail;
	}
	public Employee(int empId, String empFname, String empSal, String empLname, String empEmail) {
		super();
		this.empId = empId;
		this.empFname = empFname;
		this.empSal = empSal;
		this.empLname = empLname;
		this.empEmail = empEmail;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpFname() {
		return empFname;
	}
	public void setEmpFname(String empFname) {
		this.empFname = empFname;
	}
	public String getEmpSal() {
		return empSal;
	}
	public void setEmpSal(String empSal) {
		this.empSal = empSal;
	}
	public String getEmpLname() {
		return empLname;
	}
	public void setEmpLname(String empLname) {
		this.empLname = empLname;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

}
