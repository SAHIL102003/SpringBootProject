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

import mypackage.Services.User.UserExperianceDetailsServices;
import mypackage.model.user.Experience_Details;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class ExperianceUserController {

	@Autowired
	UserExperianceDetailsServices exservice;
	
	@GetMapping("Getallexperiancedetails")
	public List<Experience_Details>getall()
	{
		return exservice.getallexperiancedetails();
	}
	
	@GetMapping("Getexperiancedetailsbyid/{id}")
	public Experience_Details getbyid(@PathVariable("id")int id)
	{
		return exservice.Getexperiancebyid(id);
	}
	
	@PostMapping("Addexperiance")
	public String Add(@RequestBody Experience_Details e)
	{
		exservice.Addexperiance(e);
		return "Experiance Added Sucessfully";
	}
	
	@PutMapping("UpdateExperiance")
	public String Update(@RequestBody Experience_Details e)
	{
		exservice.Updateexperiance(e);
		return "Experiance Updated Successfully";
	}
	
	@DeleteMapping("Deleteexperiance/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		exservice.DeleteExperiance(id);
		Experience_Details e=new Experience_Details(id, "", 0, 0, "", 0, null, null);
		return "eExperiance Deleted Sucessfully";
	}
	
	@DeleteMapping("Restoreexperiance/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		exservice.RestoreExperiance(id);
		Experience_Details e=new Experience_Details(id, "", 0, 0, "", 0, null, null);
		return "eExperiance Restored Sucessfully";
	}
	
}
