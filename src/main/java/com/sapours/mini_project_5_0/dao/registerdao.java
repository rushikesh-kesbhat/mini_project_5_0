package com.sapours.mini_project_5_0.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sapours.mini_project_5_0.pojo.registerpojo;

public class registerdao {

	JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int save(registerpojo p) {
		String sql = "insert into registration(id,fname,lname,email,designation,password,isActive) values('" + p.getId() + "','"
				+ p.getFname() + "','" + p.getLname() + "','" + p.getEmail() + "','" + p.getDesignation() + "','"
				+ p.getPassword() + "','0')";
		return jdbcTemplate.update(sql);
	}
	public int isActiveEmail(registerpojo p) {
		String sql =  "update registration set isActive='1' where email='" + p.getEmail() + "'";
		return jdbcTemplate.update(sql);
	}

	public int update(registerpojo p) {
		String sql = "update registration set password='" + p.getPassword() + "'where email='" + p.getEmail() + "'";
		return jdbcTemplate.update(sql);
	}

	public List<registerpojo> getuseridbyemail(String email) {
		List<registerpojo> list = jdbcTemplate.query("SELECT id FROM registration where email='" + email + "'",
				new RowMapper<registerpojo>() {

					@Override
					public registerpojo mapRow(ResultSet rs, int rowNum) throws SQLException {
						registerpojo emp = new registerpojo();
						emp.setId(rs.getInt("id"));
						return emp;
					}
				});

		return list;
	}

	public List<registerpojo> getUserList() {
		List<registerpojo> list = jdbcTemplate.query("SELECT * FROM registration", new RowMapper<registerpojo>() {

			@Override
			public registerpojo mapRow(ResultSet rs, int rowNum) throws SQLException {
				registerpojo emp = new registerpojo();
				emp.setId(rs.getInt("id"));
				emp.setEmail(rs.getString("email"));
				emp.setPassword(rs.getString("password"));
				emp.setFname(rs.getString("fname"));
				emp.setIsActive(rs.getInt("isActive"));
				return emp;
			}
		});
		return list;
	}
}
