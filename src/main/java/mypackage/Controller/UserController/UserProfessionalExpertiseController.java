package mypackage.Controller.UserController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mypackage.Services.User.UserprofessionalexpertiseServices;
import mypackage.model.user.User_professional_expertise;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class UserProfessionalExpertiseController {

	@Autowired
	UserprofessionalexpertiseServices exservice;
	
	@GetMapping("Getallprofessionalexpertise")
	public List<User_professional_expertise>getall()
	{
		return exservice.getallprofessionalexpertise();
	}
	
	@GetMapping("Getprofessionalexpertisebyid/{id}")
	public User_professional_expertise getbyid(@PathVariable("id")int id)
	{
		return exservice.Getallprofessionalexpertisebyid(id);
	}
	
	@PostMapping("Addprofessionalexpertise")
	public String Add(@RequestBody User_professional_expertise u)
	{
		exservice.Addprofessionalexpertise(u);
		return "User Professional Expertise Added Sucessfully";
	}
	
	@PutMapping("Updateprofessionalexpertise")
	public String update(@RequestBody User_professional_expertise u)
	{
		exservice.Updateprofessional(u);
		return "User Professional Expertise Updated Sucessfully";
	}
	
	@DeleteMapping("DeleteProfessionalexpertise/{id}")
	public String delete(@PathVariable("id")int id)
	{
		exservice.DeleteprofessionalExpertise(id);
		User_professional_expertise u=new User_professional_expertise(id, "", 0, null, null); 
		return " User Profesional Expertise Deleted Sucessfully";
	}
	

	@DeleteMapping("RestoreProfessionalexpertise/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		exservice.RestoreprofessionalExpertise(id);
		User_professional_expertise u=new User_professional_expertise(id, "", 0, null, null); 
		return " User Profesional Expertise Restored Sucessfully";
	}
}
