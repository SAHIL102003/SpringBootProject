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

import mypackage.Services.JobopeningPost.JobOpeningpostShareServices;
import mypackage.model.Job_Opening.Jobopening_post_share;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class JobOpeningPostShareController {

	@Autowired
	JobOpeningpostShareServices shareservice;
	
	@GetMapping("Getallsharesonjobopeningpost")
	public List<Jobopening_post_share>getall()
	{
		return shareservice.getallshares();
	}
	
	@GetMapping("GetSharebyidonjobopeningpost/{id}")
	public Jobopening_post_share getbyid(@PathVariable("id")int id)
	{
		return shareservice.getsharebyid(id);
	}
	@PostMapping("Addshareonjobopeningpost")
	public String Add(@RequestBody Jobopening_post_share s)
	{
		shareservice.AddShareonpost(s);
		return "Post Shared Sucessfully";
	}
	
	@PutMapping("Updateshareonjobopeningpost")
	public String update(@RequestBody Jobopening_post_share s)
	{
		shareservice.UpdateShareonpost(s);
		return "Post Share Details Updated Sucessfully";
	}
	
	@DeleteMapping("Deleteshareonjobopeningpost/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		shareservice.Deleteshare(id);
		Jobopening_post_share s=new Jobopening_post_share(id, null, "", null, 0);
		return "Share on Post Deleted Sucessfully";
	}
	
	@DeleteMapping("Restoreshareonjobopeningpost/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		shareservice.Restoreshare(id);
		Jobopening_post_share s=new Jobopening_post_share(id, null, "", null, 0);
		return "Share on Post Restored Sucessfully";
	}
	
	
	
}
