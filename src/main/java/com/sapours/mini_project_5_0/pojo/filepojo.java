package com.sapours.mini_project_5_0.pojo;

public class filepojo {
	String fileName;
	int userid;
	int fileid;
	int readfile;
	int writefile;
	String shareby;
	String role;
	String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public filepojo(String fileName, int userid, int fileid, int readfile, int writefile, String shareby, String role,
			String email) {
		super();
		this.fileName = fileName;
		this.userid = userid;
		this.fileid = fileid;
		this.readfile = readfile;
		this.writefile = writefile;
		this.shareby = shareby;
		this.role = role;
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getFileid() {
		return fileid;
	}

	public void setFileid(int fileid) {
		this.fileid = fileid;
	}

	public int getReadfile() {
		return readfile;
	}

	public void setReadfile(int readfile) {
		this.readfile = readfile;
	}

	public int getWritefile() {
		return writefile;
	}

	public void setWritefile(int writefile) {
		this.writefile = writefile;
	}

	public String getShareby() {
		return shareby;
	}

	public void setShareby(String shareby) {
		this.shareby = shareby;
	}

	public filepojo() {
		super();
		// TODO Auto-generated constructor stub
	}

}
