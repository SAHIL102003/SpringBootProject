package mypackage.Controller.UserController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.*;
//import java.net.http.HttpHeaders;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;

import mypackage.payload.UploadFileResponse;
import mypackage.Controller.UserPostController.UserPostController;
import mypackage.FileStorageService.FileStorageServices;
import mypackage.Services.User.UserdetailsServices;
import mypackage.model.mastertables.Genders;
import mypackage.model.mastertables.Locations;
import mypackage.model.mastertables.Roles;
import mypackage.model.user.User_details;


@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class UserDetailsController {

	   private static final Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

	
	@Autowired
	UserdetailsServices userservice;
	
	@Autowired
	private FileStorageServices fileStorageService;

	  @PostMapping("/uploadFile")
	    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
	        String fileName = fileStorageService.storeFile(file);

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();

	        return new mypackage.payload.UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
	    }

		@PostMapping("registration")
		public String Adduser(@RequestParam("file") MultipartFile file,@RequestParam("first_name")String first_name,@RequestParam("middle_name")String middle_name,@RequestParam("last_name")String last_name,
				@RequestParam("local_address")String local_address,
				@RequestParam("birth_date")String birth_date,@RequestParam("joining_date")String joining_date,@RequestParam("mobile_no")String mobile_no,
				@RequestParam("email_address")String email_address,@RequestParam("user_name")String user_name,
				@RequestParam("password")String password,@RequestParam("gender_id")int gender_id,@RequestParam("location_id")int location_id,@RequestParam("role_id")int role_id)
		{
	        String fileName = fileStorageService.storeFile(file);
	        System.out.println(fileName+" "+first_name+" "+middle_name+ " "+last_name+" "+local_address+" "+birth_date+" "+email_address+" "+joining_date
	        		+" "+mobile_no+" "+user_name+" "+password+" "+gender_id+" "+location_id+" "+role_id);
	        	User_details u=new User_details();
	        	Genders g=new Genders(gender_id, null, 0);
	        	Locations l=new Locations(location_id, null, 0, null);
	        	Roles rl=new Roles(role_id, null, 0);
	   	User_details u1=new User_details(0, first_name, middle_name, last_name, local_address, birth_date, joining_date, fileName, mobile_no, email_address, user_name, 0, password, 0, g, l, rl);

	        	
			 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		                .path("/downloadFile/")
		                .path(fileName)
		                .toUriString();
		        UploadFileResponse r= new UploadFileResponse(fileName, fileDownloadUri,
		                file.getContentType(), file.getSize());
		        userservice.Adduser(u1);
		        return "Your account has been created successfully";
			
		}
		@PostMapping("newregistration")
		public String adduserdemo(@RequestParam("file") MultipartFile file,@RequestParam("first_name")String first_name) {
			 String fileName = fileStorageService.storeFile(file);
		        System.out.println(fileName+" "+first_name);
		        return "success";
		}
	  @PostMapping("/uploadMultipleFiles")
	    public List<Object> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
	        return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());		  
	    }
	  
	  @GetMapping("/upload/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	        	logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }

	@GetMapping("Getallusers")
	public List<User_details>getall()
	{
		return userservice.getallusers();
	}
	
	@GetMapping("Getuserbyid/{id}")
	public User_details getbyid(@PathVariable("id")int id)
	{
		return userservice.Getuserbyid(id);
	}
	
	@PutMapping("activateuser/{id}")
	public String ActivateUser(@PathVariable("id")int id)
	{
		userservice.ActivateUser(id);
		return "User Activated Sucessfully";
	}
	@PutMapping("deactivateuser/{id}")
	public String DeActivateUser(@PathVariable("id")int id)
	{
		userservice.DeActivateUser(id);
		return "User Activated Sucessfully";
	}
	@PutMapping("Updateuser")
	public String update(@RequestBody User_details u)
	{
		userservice.Updateuser(u);
		return "User Updated Sucessfully";
	}
	
	@DeleteMapping("Deleteuser/{id}")
	public String delete(@PathVariable("id")int id)
	{
		userservice.Deleteuser(id);
		User_details u=new User_details(id, "", "", "", "", "", "", "", "", "", "", 0, "", 0, null, null, null);
		return "User Deleted Sucessfully";
	}
	
	@DeleteMapping("Restoreuser/{id}")
	public String restore(@PathVariable("id")int id)
	{
		userservice.Restoreuser(id);
		User_details u=new User_details(id, "", "", "", "", "", "", "", "", "", "", 0, "", 0, null, null, null);
		return "User Restore Sucessfully";
	}
	
	@PostMapping("login")
	public User_details Checklogin(@RequestParam("email_address")String email_address,@RequestParam("password")String pass) {
		
//	 User_details u=new User_details(0, "", "", "", "", "", "", "", "", "", uname, 0, pass, 0, null, null, null);
	 System.out.println(email_address+" "+pass+" ");
	 User_details u= userservice.Login(email_address,pass);	
	 if(u!=null) {
		 return u;
	 }
	 else {
		 return null;
	 }
		
		
		
	}
	
}
