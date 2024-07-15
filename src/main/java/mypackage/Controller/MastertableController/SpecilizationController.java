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

import mypackage.Services.Mastertable.SpecilizationServices;
import mypackage.model.mastertables.Specializations;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class SpecilizationController {

	@Autowired
	SpecilizationServices specialservice;
	
	@GetMapping("specialization")
	public List<Specializations>getall(){
		return specialservice.getallspecial();
	}
	@GetMapping("qualificationwisespecializations/{id}")
	public List<Specializations>getqualificationwisespecializations(@PathVariable("id")int id){
		return specialservice.getQualificationWiseSpecializations(id);
	}
	
	@GetMapping("specialization/{id}")
	public Specializations getbyid(@PathVariable("id")int id)
	{
		return specialservice.getspecilizationbyid(id);
	}
	
	@PostMapping("specialization")
	public String Addspecia(@RequestBody Specializations s)
	{
		specialservice.Addspecialization(s);
		return "Specilization Added Sucessfully";
	}
	@PutMapping("specialization")
	public String Updatespec(@RequestBody Specializations s)
	{
		specialservice.Updatespecilization(s);
		return "Specilization Updated Sucessfully";
	}
	
	@DeleteMapping("Deletespecialization/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		specialservice.Deletespecilization(id);
		Specializations s=new Specializations(id, "", 0, null);
		return "Specilization Deleted Sucessfully";
	}
	
	@DeleteMapping("Restorespecialization/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		specialservice.Restorespecilization(id);
		Specializations s=new Specializations(id, "", 0, null);
		return "Specilization Restore Sucessfully";
	}
	
	
}
