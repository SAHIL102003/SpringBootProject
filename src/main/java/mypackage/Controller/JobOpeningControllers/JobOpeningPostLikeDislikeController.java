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

import mypackage.Services.JobopeningPost.JobOpeningpostLikeDislikeServices;
import mypackage.model.Job_Opening.Jobopening_post_like_dislike;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class JobOpeningPostLikeDislikeController {

	
	@Autowired
	JobOpeningpostLikeDislikeServices likeservice;
	
	@GetMapping("Getallreactionsonpost")
	public List<Jobopening_post_like_dislike>getall()
	{
		return likeservice.getallreactions();
	}
	
	@GetMapping("Getreactionbyid/{id}")
	public Jobopening_post_like_dislike getbyid(@PathVariable("id")int id)
	{
		return likeservice.getreactionbyid(id);
	}
	
	@PostMapping("Addreactiononpost")
	public String Add(@RequestBody Jobopening_post_like_dislike j)
	{
		likeservice.Addreaction(j);
		return "Reaction Added To Post";
	}
	
	@PutMapping("Updatereactiononpost")
	public String Update(@RequestBody Jobopening_post_like_dislike j)
	{
		likeservice.Updatereaction(j);
		return "Reaction Updated Sucessfully";
	}
	
	@DeleteMapping("Deletereaction/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		likeservice.Deletereaction(id);
		Jobopening_post_like_dislike j=new Jobopening_post_like_dislike(id, null, "", null, 0, 0);
		return "Reaction Deleted Sucessfully";
	}
	
	@DeleteMapping("Restorereaction/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		likeservice.Restorereaction(id);
		Jobopening_post_like_dislike j=new Jobopening_post_like_dislike(id, null, "", null, 0, 0);
		return "Reaction Deleted Sucessfully";
	}
}
