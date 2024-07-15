package mypackage.Controller.MastertableController;

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

import mypackage.Services.Mastertable.GenderServices;
import mypackage.model.mastertables.Genders;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class GenderController {

	@Autowired
	GenderServices genservice;
	
	@GetMapping("gender")
	public List<Genders>getall()
	{
		return genservice.getgender();
	}
	
	@PostMapping("gender")
	public String addgender(@RequestBody Genders g)
	{
		genservice.Addgender(g);
		return "Gender Added Successfully";
	}
	
	@PutMapping("gender")
	public String updategender(@RequestBody Genders g)
	{
		genservice.Updategender(g);
		return "Gender Updated Sucessfully";
	}
	
	@DeleteMapping("Deletegender/{id}")
	public String deletegender(@PathVariable("id")int id)
	{
		genservice.DeleteGender(id);
		Genders gs=new Genders(id, "", 0);
		return "Gender Deleted Sucessfully";
	}
	
	@DeleteMapping("Restoregender/{id}")
	public String Restoregender(@PathVariable("id")int id)
	{
		genservice.DeleteGender(id);
		Genders gs=new Genders(id, "", 0);
		return "Gender Restored Sucessfully";
	}
}
