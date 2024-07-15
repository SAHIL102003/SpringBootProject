package mypackage.Controller.JobOpeningControllers;

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

import mypackage.Services.JobopeningPost.Job_OpeningServices;
import mypackage.model.Job_Opening.Job_opening;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class JobOpeningController {

	@Autowired
	Job_OpeningServices openingservice;
	
	@GetMapping("Getalljobopenings")
	public List<Job_opening>getall()
	{
		return openingservice.getallopenings();
	}
	
	@GetMapping("Getjobopeningbyid/{id}")
	public Job_opening getbyid(@PathVariable("id")int id)
	{
		return openingservice.Getjobopeningbyid(id);
	}
	
	@PostMapping("Addjobopening")
	public String Add(@RequestBody Job_opening j)
	{
		openingservice.Addjobopening(j);
		return "Job Opening Added Sucessfully";
	}
	
	@PutMapping("Updatejobopening")
	public String Update(@RequestBody Job_opening j)
	{
		openingservice.Updatejobopening(j);
		return "Job Opening Updated Sucessfully";
	}
	
	@DeleteMapping("Deletejobopening/{id}")
	public String delete(@PathVariable("id")int id)
	{
		openingservice.Deletejobopening(id);
		Job_opening j=new Job_opening(id, "", null, "", "", "", "", 0, 0);
		return "Job Opening Deleted Sucessfully";
	}
	

	@DeleteMapping("Restorejobopening/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		openingservice.Restorejobopening(id);
		Job_opening j=new Job_opening(id, "", null, "", "", "", "", 0, 0);
		return "Job Opening Restored Sucessfully";
	}
}
