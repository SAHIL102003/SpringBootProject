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

import mypackage.Services.Mastertable.DesignationServices;
import mypackage.model.mastertables.Designations;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.DELETE,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class DesignationController {

	@Autowired
	DesignationServices dservice;
	
	@GetMapping("designation")
	public List<Designations>getall()
	{
		return dservice.getalldes();
	}
	
	@GetMapping("designation/{id}")
	public Designations getbyid(@PathVariable("id")int id)
	{
		return dservice.getbyid(id);
	}
		
	@PostMapping("designation")
	public String Add(@RequestBody Designations d)
	{
		dservice.Adddesignation(d);
		return "Designation Added Sucessfully";
	}
	@PutMapping("designation")
	public String Update(@RequestBody Designations d)
	{
		dservice.updatedesignation(d);
		return "Designation Updated Successfully";
	}
	
	@DeleteMapping("Deletedesignation/{id}")
	public String delete(@PathVariable("id")int id)
	{
		dservice.Deletedesignation(id);
		Designations ds=new Designations(id, "", 0);
		return "Designation Deleted Sucessfully";
	}
	
	@DeleteMapping("Restoredesignation/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		dservice.Deletedesignation(id);
		Designations ds=new Designations(id, "", 0);
		return "Designation Restore Sucessfully";
	}
}
