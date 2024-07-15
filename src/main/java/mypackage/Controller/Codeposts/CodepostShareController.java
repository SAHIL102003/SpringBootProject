package mypackage.Controller.Codeposts;

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

import mypackage.Services.Codeposts.CodePostShareServices;
import mypackage.model.Codes.Code_post_share;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class CodepostShareController {

	@Autowired
	CodePostShareServices shareservice;
	
	@GetMapping("Getallshares")
	public List<Code_post_share>getall()
	{
		return shareservice.getallshares();
	}
	
	@GetMapping("Getsharedetailsbyid/{id}")
	public Code_post_share getbyid(@PathVariable("id")int id)
	{
		return shareservice.getsharebyid(id);
	}
	
	@PostMapping("Addpostshare")
	public String Add(@RequestBody Code_post_share c)
	{
		shareservice.Addpostshare(c);
		return "Post Share Added Sucessfully";
	}
	
	@PutMapping("Updatepostshare")
	public String Update(@RequestBody Code_post_share c)
	{
		shareservice.Updatepostshare(c);
		return "Post Share Updated Sucessfully";
	}
	
	@DeleteMapping("DeletePostshare/{id}")
	public String delete(@PathVariable("id")int id)
	{
		shareservice.Deleteshare(id);
		return "Post Share Deleted Sucessfully";
	}
	@DeleteMapping("RestorePostshare/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		shareservice.Restoreshare(id);
		return "Post Share Restored Sucessfully";
	}
	
}
