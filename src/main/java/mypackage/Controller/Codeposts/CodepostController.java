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

import mypackage.Services.Codeposts.CodePostServices;
import mypackage.model.Codes.Code_posts;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class CodepostController {

	@Autowired
	CodePostServices codeservice;
	
	@GetMapping("GetallUnapprovedcodepost")
	public List<Code_posts>getallunapprovedpost()
	{
		return codeservice.getallUnapprovedpost();
	}
	
	@GetMapping("Getcodepostbypostid/{id}")
	public Code_posts getbyid(@PathVariable("id")int id)
	{
		return codeservice.getcodepostbyid(id);
	}
	
	@PostMapping("Postcode")
	public String postcodepost(@RequestBody Code_posts p)
	{
		codeservice.Addcodepost(p);
		return "Code Post Added Sucessfully";
	}
	
	@PutMapping("Updatecodepost")
	public String update(@RequestBody Code_posts p)
	{
		codeservice.Updatecodepost(p);
		return "Code Post Updated Sucessfully";
	}
	
	@DeleteMapping("Deletecodepost/{id}")
	public String delete(@PathVariable("id")int id)
	{
		codeservice.Deletecodepost(id);
		Code_posts c=new Code_posts(id, null, "", null, "", "", "", 0, 0);
		return "Code post Deleted Sucessfully";
	}
	@DeleteMapping("Restorecodepost/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		codeservice.Restorecodepost(id);
		Code_posts c=new Code_posts(id, null, "", null, "", "", "", 0, 0);
		return "Code post Restored Sucessfully";
	}
	
	@GetMapping("api/Getallapprovalpost")
	public List<Code_posts>getapprovalpost()
	{
		return codeservice.getallaprovedpost();
	}
	
	@PostMapping("Approvecodepost/{id}")
	public String Approvecodepost(@PathVariable("id")int id)
	{
		codeservice.Approvecodepost(id);
		return "Code Post Approved Sucessfully";
	}
	
	@GetMapping("Getallcodeposts")
	public List<Code_posts>getall()
	{
		return codeservice.getallCodeposts();
	}
}
