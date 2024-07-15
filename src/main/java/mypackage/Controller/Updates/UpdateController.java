package mypackage.Controller.Updates;

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

import mypackage.Services.Updates.UpdateServices;
import mypackage.model.Updates.Updates;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class UpdateController {

	@Autowired
	UpdateServices updateservice;
	
	@GetMapping("Getallupdates")
	public List<Updates>getall()
	{
		return updateservice.getallupdates();
	}
	
	@GetMapping("Getupdatebyupdateid/{id}")
	public Updates getbyid(@PathVariable("id")int id)
	{
		return updateservice.getupdatebyudateid(id);
	}
	@PostMapping("Addupdates")
	public String Add(@RequestBody Updates u)
	{
		updateservice.Addupdates(u);
		return "Update Added Sucessfully";
	}
	@PutMapping("Updateupdates")
	public String update(@RequestBody Updates u)
	{
		updateservice.Updateupdates(u);
		return "Update Updted Sucessfully";
	}
	
	@DeleteMapping("DeleteUpdate/{id}")
	public String delete(@PathVariable("id")int id)
	{
		updateservice.Deleteupdate(id);
		return "Update Deleted Sucessfully";
	}
	
	@DeleteMapping("RestoreUpdate/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		updateservice.Restoreupdate(id);
		return "Update Restored Sucessfully";
	}
	
}
