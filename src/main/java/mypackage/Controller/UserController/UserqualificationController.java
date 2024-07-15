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

import mypackage.Services.User.UserqualificationServices;
import mypackage.model.user.User_qualification;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class UserqualificationController {

	@Autowired
	UserqualificationServices qualservice;
	
	@GetMapping("Getalluserqualification")
	public List<User_qualification>getall()
	{
		return qualservice.getalluserqualification();
		
	}
	
	@GetMapping("Getuserqualificationbyid/{id}")
	public User_qualification getbyid(@PathVariable("id")int id)
	{
		return qualservice.getuserqualificationbyid(id);
	}
	
	@PostMapping("Adduserqualification")
	public String add(@RequestBody User_qualification u )
	{
		qualservice.Adduserqualification(u);
		return "User Qualification Added Sucessfully";
	}
	
//	@PutMapping("Updatequalification")
//	public String Updatequalification(@RequestBody User_qualification u)
//	{
//		qualservice.Updateuserqualification(u);
//		return "User Qualification Updated Sucessfully";
//	}
	
	@DeleteMapping("Deleteuserqualification/{id}")
	public String delete(@PathVariable("id")int id)
	{
		qualservice.Deleteuserqualification(id);
		User_qualification u=new User_qualification(id, "",0,"",0,0,null, null);
		return "User Qualification Deleted Sucessfully";
	}
	
	@DeleteMapping("Restoreuserqualification/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		qualservice.Restoreuserqualification(id);
		User_qualification u=new User_qualification(id, "",0,"",0,0,null, null);
		return "User Qualification Restore Sucessfully";
	}
}
