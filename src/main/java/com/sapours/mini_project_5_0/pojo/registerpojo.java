package com.sapours.mini_project_5_0.pojo;

public class registerpojo {
	private int id;
	private String fname;
	private String lname;
	private String email;
	private String designation;
	private String password;
	private int isActive;

	
	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public registerpojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public registerpojo(int id, String fname, String lname, String email, String designation, String password,int isActive) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.designation = designation;
		this.password = password;
		this.isActive=isActive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
