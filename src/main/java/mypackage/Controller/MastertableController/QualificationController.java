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

import mypackage.Services.Mastertable.QualificationServices;
import mypackage.model.mastertables.Qualifications;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class QualificationController {
	
	@Autowired
	QualificationServices qualservice;
	
	@GetMapping("qualification")
	public List<Qualifications>getall()
	{
		return qualservice.getallqualifications();
	}

	@PostMapping("qualification")
	public String add(@RequestBody Qualifications q)
	{
		qualservice.Addqualification(q);
		return "Qualification Added Sucessfully";
	}
	
	@PutMapping("qualification")
	public String update(@RequestBody Qualifications q)
	{
		qualservice.Updatequalification(q);
		return "Qualification Updated Successfully";
	}
	
	@GetMapping("qualification/{id}")
	public Qualifications getbyid(@PathVariable("id")int id)
	{
		return qualservice.getqualificationbyid(id);
	}
	
	@DeleteMapping("Deletequalification/{id}")
	public String delete(@PathVariable("id")int id)
	{
		qualservice.Deletequalification(id);
		Qualifications q=new Qualifications(id, "", 0);
		return "Qualification Deleted Sucessfully";
	}
	
	@DeleteMapping("Restorequalification/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		qualservice.Restorequalification(id);
		Qualifications q=new Qualifications(id, "", 0);
		return "Qualification Restored Sucessfully";
	}
			
	
}
