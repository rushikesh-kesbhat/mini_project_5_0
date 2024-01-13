package com.sapours.mini_project_5_0.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sapours.mini_project_5_0.dao.emaildao;
import com.sapours.mini_project_5_0.dao.filedao;
import com.sapours.mini_project_5_0.dao.registerdao;
import com.sapours.mini_project_5_0.pojo.emailpojo;
import com.sapours.mini_project_5_0.pojo.filepojo;
import com.sapours.mini_project_5_0.pojo.registerpojo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class HomeController {

	@Autowired
	emaildao emailDao;
	@Autowired
	registerdao dao;
	@Autowired
	filedao fileDao;

	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public int save(@RequestBody registerpojo reg) {  
		return dao.save(reg);// will redirect to viewemp request mapping
	}
	@RequestMapping(value = "/isActiveEmail", method = RequestMethod.PUT)
	public int isActive(@RequestBody registerpojo reg) {  	
		return dao.isActiveEmail(reg);// will redirect to viewemp request mapping
	}


	// Get All users
	@RequestMapping(value = "/getuser", method = RequestMethod.GET)
	public List<registerpojo> listuser() throws IOException {
		List<registerpojo> listuser = dao.getUserList();
		return listuser;
	}
	
	//get id by email
	@RequestMapping(value = "/getidbyemail", method = RequestMethod.GET)
	public List<registerpojo> listid(@RequestParam String email) throws IOException {
		List<registerpojo> id = dao.getuseridbyemail(email);
		return id;
	}
	
	//share with me
		@RequestMapping(value = "/sharewithme", method = RequestMethod.POST)
		public List<filepojo> listme(@RequestBody filepojo f) throws IOException {
			List<filepojo> list = fileDao.sharewithme(f);
			return list;
		}
	
		//uploaded by me
		@RequestMapping(value = "/uploadedbyme", method = RequestMethod.POST)
		public List<filepojo> showListByMe(@RequestBody filepojo f) throws IOException {
			List<filepojo> list = fileDao.uploadedWithMe(f);
			return list;
		}
	
		
	// Get All files
	@ResponseBody
	@RequestMapping(value = "/getfiles", method = RequestMethod.POST)
	public List<filepojo> listfiles(@RequestBody filepojo filep) throws IOException {
		List<filepojo> listfile = fileDao.getfilelist(filep);
		return listfile;
	}

   //Get Recent 10 files
	@ResponseBody
	@RequestMapping(value = "/recentfiles", method = RequestMethod.POST)
	public List<filepojo> recentfiles(@RequestBody filepojo f) throws IOException {
		List<filepojo> listfile = fileDao.getRecentfilelist(f);
		return listfile;
	}
	//Get co-owner
	 //Get Recent 10 files
		@ResponseBody
		@RequestMapping(value = "/getcoowner", method = RequestMethod.POST)
		public List<filepojo> getcowner(@RequestBody filepojo f) throws IOException {
			List<filepojo> listfile = fileDao.getcowner(f);
			return listfile;
		}

	@ResponseBody
	@RequestMapping(value = "/deletefile", method = RequestMethod.DELETE)
	public int delete(@RequestParam int fileid) {
		return fileDao.deleteFile(fileid);
	}

//upload file
	@RequestMapping(value = "/filesave", method = RequestMethod.POST)
	public int filesave(@RequestParam CommonsMultipartFile file, @RequestParam int userid,@RequestParam int readfile,@RequestParam int writefile,@RequestParam String shareby,@RequestParam String role) {
		String filename = file.getOriginalFilename();
		String location = file.getName();
		System.out.println(userid);
		System.out.println(readfile);
		System.out.println(writefile);
		System.out.println(shareby);
		try {
			Files.copy(file.getInputStream(), Paths.get("D:\\miniProjectFiles\\" + file.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			fileDao.savefile(filename, userid,readfile,writefile,shareby,role);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return 0;
		}
		System.out.println(filename);
		return 1;
	}
	@RequestMapping(value = "/sharefile", method = RequestMethod.GET)
	public int filesave(@RequestParam String filename, @RequestParam int userid,@RequestParam int readfile,@RequestParam int writefile,@RequestParam String shareby,@RequestParam String role) {
		System.out.println(userid);
		System.out.println(readfile);
		System.out.println(writefile);
		System.out.println(shareby);
		try {
			fileDao.savefile(filename, userid,readfile,writefile,shareby,role);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return 0;
		}
		System.out.println(filename);
		return 1;
	}
	//shared file emails
	@RequestMapping(value = "/sharedfileemail",method=RequestMethod.GET)
	public  List<filepojo> shareemail(@RequestParam String filename,@RequestParam String shareby) throws IOException {
		List<filepojo> listEmp = fileDao.sharedfileemail(filename,shareby);
		return listEmp;
	}	
	@RequestMapping(value = "/updatepassword", method = RequestMethod.PUT)
	public int update(@RequestBody registerpojo reg) {
		return dao.update(reg);// will redirect to viewemp request mapping
	}

	@RequestMapping(value = "/revokePermission", method = RequestMethod.GET)
	public int update(@RequestParam int fileid) {
		return fileDao.updatePermission(fileid);// will redirect to viewemp request mapping
	}
	@RequestMapping(value = "/setPermission", method = RequestMethod.GET)
	public int set(@RequestParam int fileid) {
		return fileDao.setPermissionCowner(fileid);// will redirect to viewemp request mapping
	}
	
	@RequestMapping(value = "/updateread", method = RequestMethod.GET)
	public int updateread(@RequestParam int fileid,@RequestParam int access) {
		return fileDao.updateReadAccess(fileid,access);// will redirect to viewemp request mapping
	}
	@RequestMapping(value = "/updatewrite", method = RequestMethod.GET)
	public int updatewrite(@RequestParam int fileid,@RequestParam int access) {
		return fileDao.updateWriteAccess(fileid,access);// will redirect to viewemp request mapping
	}
	
	@RequestMapping(value = "/sendotp", method = RequestMethod.POST)
	@ResponseBody
	public int login(@RequestBody emailpojo email) {
		int otp = new Random().nextInt(999999);
		String message = "your OtP is " + otp;
		String subject = "OTP verification";
		String to = email.getTo();
		String from = "demo.jk.183@gmail.com";
		emailDao.sendOtp(message, subject, to);
		return otp;
	}
	
	@RequestMapping(value = "/gettext", method = RequestMethod.GET)
	public @ResponseBody String getText(@RequestParam String filename) throws IOException {
		System.out.println("jdfj");
		String txt=fileDao.gettext(filename);
	
		return txt;
	}
	@RequestMapping(value = "/settext", method = RequestMethod.GET)
	public @ResponseBody int settext(@RequestParam String filename,String txt) throws IOException {
		System.out.println("jdfj");
		fileDao.settext(filename, txt);
		return 1;
	}
	
}
