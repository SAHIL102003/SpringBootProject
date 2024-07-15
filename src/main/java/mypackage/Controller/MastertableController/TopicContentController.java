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

import mypackage.Services.Mastertable.TopicContentServices;
import mypackage.model.mastertables.Topic_contents;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class TopicContentController {
	
	@Autowired
	TopicContentServices contentservice;
	
	@GetMapping("content")
	public List<Topic_contents>getall(){
		return contentservice.getalltopiccontent();
	}
	@GetMapping("topicwisecontents/{id}")
	public List<Topic_contents>gettopicwisecontents(@PathVariable("id")int id){
		return contentservice.getTopicWiseContents(id);
	}
	@GetMapping("Gettopiccontentbyid/{id}")
	public Topic_contents getbyid(@PathVariable("id")int id)
	{
		return contentservice.gettopiccontentbyid(id);
	}
	
	@PostMapping("content")
	public String Addtopic(@RequestBody Topic_contents t)
	{
		contentservice.Addtopiccontent(t);
		return "Topic Content Added Sucessfully";
	}
	@PutMapping("content")
	public String Updatetopic(@RequestBody Topic_contents t)
	{
		contentservice.Updatetopiccontent(t);
		return "Topic Content Updated Sucessfully";
	}
	
	@DeleteMapping("Deletecontent/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		contentservice.Deletetopicontent(id);
		Topic_contents t=new Topic_contents(id, "", 0, null);
		return "Topic Content Deleted Sucessfully";
	}
	@DeleteMapping("Restorecontent/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		contentservice.Restoretopicontent(id);
		Topic_contents t=new Topic_contents(id, "", 0, null);
		return "Topic Content Restored Sucessfully";
	}
	
	

}
