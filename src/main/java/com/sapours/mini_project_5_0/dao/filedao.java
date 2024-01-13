package com.sapours.mini_project_5_0.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sapours.mini_project_5_0.pojo.filepojo;
import com.sapours.mini_project_5_0.pojo.registerpojo;

public class filedao {

	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int savefile(String fileName, int userid, int readfile, int writefile, String shareby, String role) {
		System.out.println(fileName);
		System.out.println("hellooo");
		String sql = "insert into FileUpload(fileName,userid,readfile,writefile,shareby,role) values('" + fileName
				+ "','" + userid + "','" + readfile + "','" + writefile + "','" + shareby + "','" + role + "');";
		return jdbcTemplate.update(sql);
	}

	public int deleteFile(int fileid) {
		String sql = "delete from FileUpload where fileid='" + fileid + "'";
		return jdbcTemplate.update(sql);

	}

	public List<filepojo> getfilelist(filepojo f) {
		List<filepojo> list = jdbcTemplate.query("SELECT * FROM fileupload where userid='" + f.getUserid() + "'",
				new RowMapper<filepojo>() {

					@Override
					public filepojo mapRow(ResultSet rs, int rowNum) throws SQLException {
						filepojo emp = new filepojo();
						emp.setFileid(rs.getInt("fileid"));
						emp.setFileName(rs.getString("fileName"));
						emp.setUserid(rs.getInt("userid"));
						emp.setReadfile(rs.getInt("readfile"));
						emp.setWritefile(rs.getInt("writefile"));
						emp.setShareby(rs.getString("shareby"));
						emp.setRole(rs.getString("role"));

						return emp;
					}

				});

		return list;
	}

	public List<filepojo> getcowner(filepojo f) {
		List<filepojo> list = jdbcTemplate.query(
				"select fu.userid,fu.readfile,fu.writefile,fu.shareby,fu.fileid,fu.fileName,rg.email,fu.role FROM fileupload fu JOIN registration rg ON  rg.id = fu.userid where fu.role='co-owner' and fu.shareby='vb@gmail.com'",
				new RowMapper<filepojo>() {

					@Override
					public filepojo mapRow(ResultSet rs, int rowNum) throws SQLException {
						filepojo emp = new filepojo();
						emp.setFileid(rs.getInt("fileid"));
						emp.setFileName(rs.getString("fileName"));
						emp.setUserid(rs.getInt("userid"));
						emp.setReadfile(rs.getInt("readfile"));
						emp.setWritefile(rs.getInt("writefile"));
						emp.setShareby(rs.getString("shareby"));
						emp.setRole(rs.getString("role"));

						return emp;
					}

				});

		return list;
	}

	public List<filepojo> getRecentfilelist(filepojo f) {
		List<filepojo> list = jdbcTemplate.query(
				" select * from fileupload where userid='" + f.getUserid() + "' order by fileid desc limit 10",
				new RowMapper<filepojo>() {

					@Override
					public filepojo mapRow(ResultSet rs, int rowNum) throws SQLException {
						filepojo emp = new filepojo();
						emp.setFileid(rs.getInt("fileid"));
						emp.setFileName(rs.getString("fileName"));
						emp.setUserid(rs.getInt("userid"));
						emp.setReadfile(rs.getInt("readfile"));
						emp.setWritefile(rs.getInt("writefile"));
						emp.setShareby(rs.getString("shareby"));

						return emp;
					}

				});

		return list;
	}

	/// shared with me
	public List<filepojo> sharewithme(filepojo f) {
		List<filepojo> list = jdbcTemplate.query(" select * from fileupload where userid='" + f.getUserid()
				+ "' and shareby!='" + f.getShareby() + "' order by fileid desc", new RowMapper<filepojo>() {

					@Override
					public filepojo mapRow(ResultSet rs, int rowNum) throws SQLException {
						filepojo emp = new filepojo();
						emp.setFileid(rs.getInt("fileid"));
						emp.setFileName(rs.getString("fileName"));
						emp.setUserid(rs.getInt("userid"));
						emp.setReadfile(rs.getInt("readfile"));
						emp.setWritefile(rs.getInt("writefile"));
						emp.setShareby(rs.getString("shareby"));
						return emp;
					}

				});

		return list;
	}

	// uploadedfilesbyself
	public List<filepojo> uploadedWithMe(filepojo f) {
		List<filepojo> list = jdbcTemplate.query(" select * from fileupload where userid='" + f.getUserid()
				+ "' and shareby='" + f.getShareby() + "' order by fileid desc", new RowMapper<filepojo>() {

					@Override
					public filepojo mapRow(ResultSet rs, int rowNum) throws SQLException {
						filepojo emp = new filepojo();
						emp.setFileid(rs.getInt("fileid"));
						emp.setFileName(rs.getString("fileName"));
						emp.setUserid(rs.getInt("userid"));
						emp.setReadfile(rs.getInt("readfile"));
						emp.setWritefile(rs.getInt("writefile"));
						emp.setShareby(rs.getString("shareby"));
						return emp;
					}

				});

		return list;
	}

	public List<filepojo> sharedfileemail(String filename, String shareby) {
		List<filepojo> list = jdbcTemplate.query(
				"select fileid,email,role,readfile,writefile FROM fileupload fu, registration rg  where fu.fileName='"
						+ filename + "' and fu.shareby='" + shareby
						+ "' and fu.role!='owner' and fu.userid = rg.id GROUP BY email",
				new RowMapper<filepojo>() {

					@Override
					public filepojo mapRow(ResultSet rs, int rowNum) throws SQLException {
						filepojo emp = new filepojo();
						emp.setFileid(rs.getInt("fileid"));
						emp.setEmail(rs.getString("email"));
						emp.setRole(rs.getString("role"));
						emp.setReadfile(rs.getInt("readfile"));
						emp.setWritefile(rs.getInt("writefile"));
						return emp;
					}

				});

		return list;
	}

	public int updatePermission(int fileid) {
		String sql = "UPDATE fileupload\r\n" + "SET role = 'user'\r\n" + "WHERE fileid = " + fileid + ";";
		return jdbcTemplate.update(sql);
	}

	public int setPermissionCowner(int fileid) {
		String sql = "UPDATE fileupload\r\n" + "SET role = 'co-owner'\r\n" + "WHERE fileid = " + fileid + ";";
		return jdbcTemplate.update(sql);
	}

	public int updateReadAccess(int fileid, int access) {
		String sql = "UPDATE fileupload\r\n" + "SET readfile = '" + access + "'\r\n" + "WHERE fileid = " + fileid + ";";
		return jdbcTemplate.update(sql);
	}

	public int updateWriteAccess(int fileid, int access) {
		String sql = "UPDATE fileupload\r\n" + "SET writefile = '" + access + "'\r\n" + "WHERE fileid = " + fileid
				+ ";";
		return jdbcTemplate.update(sql);
	}

	public String gettext(String filename) throws IOException {
		String file = "D:\\miniProjectFiles\\" + filename;
		Path path = Paths.get(file);
		String text = "";
		BufferedReader bufferedReader = Files.newBufferedReader(path);

		String curLine;
		while ((curLine = bufferedReader.readLine()) != null) {
			text = text + curLine + ('\n');
		}
		bufferedReader.close();
		return text;
	}

	public int settext(String filename, String txt) {

		try {
			FileWriter fwrite = new FileWriter("D:\\miniProjectFiles\\" + filename, false);
			fwrite.write(txt);
			fwrite.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("exception");
			e.printStackTrace();
		}
		System.out.println("Content is successfully wrote to the file.");
		return 1;
	}

}
