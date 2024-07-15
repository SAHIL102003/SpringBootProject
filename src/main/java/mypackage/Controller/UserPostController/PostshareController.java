package mypackage.Controller.UserPostController;

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

import mypackage.Services.Userpost.PostSharesServices;
import mypackage.model.Userposts.Post_Shares;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class PostshareController {

	@Autowired
	PostSharesServices shareservice;
	
	@GetMapping("GetallSharesonpost")
	public List<Post_Shares>getall()
	{
		return shareservice.getallshares();
	}
	
	@GetMapping("Getshareonpostbyid/{id}")
	public Post_Shares getbyid(@PathVariable("id")int id)
	{
		return shareservice.Getshareid(id);
	}
	
	@PostMapping("AddShareonpost")
	public String Add(@RequestBody Post_Shares p)
	{
		shareservice.Addshare(p);
		return "Post Share sucessfully";
	}
	
	@PutMapping("Updateshareonpost")
	public String update(@RequestBody Post_Shares p)
	{
		shareservice.Updateshare(p);
		return "Post Share Updated Sucessfully";
	}
	
	@DeleteMapping("Deleteshareonpost/{id}")
	public String delete(@PathVariable("id")int id)
	{
		shareservice.Deletesharepost(id);
		Post_Shares s=new Post_Shares(id, null, "", null, 0);
		return "Post Share Deleted Sucessfully";
	}
	
	@DeleteMapping("Restoreshareonpost/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		shareservice.Restoresharepost(id);
		Post_Shares s=new Post_Shares(id, null, "", null, 0);
		return "Post Share Restored Sucessfully";
	}
}
