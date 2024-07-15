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

import mypackage.Services.Mastertable.TopicServices;
import mypackage.model.mastertables.Topics;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class TopicController {

	
	@Autowired
	TopicServices topicservice;
	
	@GetMapping("topic")
	public List<Topics>getall()
	{
		return topicservice.getalltopics();
	}
	
	@GetMapping("topic/{id}")
	public Topics getbyid(@PathVariable("id")int id)
	{
		return topicservice.gettopicbyid(id);
	}
	
	@PostMapping("topic")
	public String Addtopic(@RequestBody Topics t)
	{
		topicservice.Addtopics(t);
		return "Topic Added Sucessfully";
	}
	
	@PutMapping("topic")
	public String Updatetopic(@RequestBody Topics t)
	{
		topicservice.Updatetopics(t);
		return "Topic Updated Sucessfully";
	}
	
	@DeleteMapping("Deletetopic/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		topicservice.Deletetopics(id);
		Topics t=new Topics(id, "", 0);
		return "Topic Deleted Sucessfully";
	}
	
	@DeleteMapping("Restoretopic/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		topicservice.Restoretopics(id);
		Topics t=new Topics(id, "", 0);
		return "Topic Restored Sucessfully";
	}
}
