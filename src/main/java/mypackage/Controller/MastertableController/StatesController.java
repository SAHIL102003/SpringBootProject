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

import mypackage.Services.Mastertable.StateServices;
import mypackage.model.mastertables.States;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = "*")
public class StatesController {

	@Autowired
	StateServices stateservice;

	
	@PostMapping("state")
	public String Addstates(@RequestBody States s)
	{
		stateservice.Addstate(s);
		return "States Added Sucessfully";
	}
	
	@PutMapping("state")
	public String Update(@RequestBody States s)
	{
		stateservice.updatestate(s);
		return "State Updated Sucessfully";
	}
	
	@GetMapping("state/{id}")
	public States get(@PathVariable("id")int id)
	{
		return stateservice.GetstateByID(id);
	}
	
	@GetMapping("state")
	public List<States>Getall(){
		return stateservice.GetallStates();
	}
	
	@DeleteMapping("Deletestate/{id}")
	public String delete(@PathVariable("id")int id)
	{
		stateservice.DeleteStates(id);
		States st=new States(id, "", 0);
		return "State Deleted Sucessfully";
	}
	
	@DeleteMapping("Restorestate/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		stateservice.RestoreStates(id);
		States st=new States(id, "", 0);
		return "State Restored Sucessfully";
	}
	
	
}
