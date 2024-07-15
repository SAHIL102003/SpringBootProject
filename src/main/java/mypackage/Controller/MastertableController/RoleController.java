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

import mypackage.Services.Mastertable.RolesServices;
import mypackage.model.mastertables.Roles;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
public class RoleController {
	
	@Autowired
	RolesServices rolservice;
	
	@GetMapping("role")
	public List<Roles>getall(){
		return rolservice.getallroles();
	}
	
	@GetMapping("role/{id}")
	public Roles getbyid(@PathVariable ("id")int id)
	{
		return rolservice.getrolesbyid(id);
	}

	@PostMapping("role")
	public String Addrole(@RequestBody Roles r)
	{
		rolservice.Addrole(r);
		return "Role Added Sucessfully";
	}
	
	@PutMapping("role")
	public String updaterole(@RequestBody Roles r)
	{
		rolservice.Updaterole(r);
		return "Role Updated Sucessfully";
	}
	@DeleteMapping("Deleterole/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		rolservice.Deleterole(id);
		Roles r=new Roles(id, "", 0);
		return "Role Deleted Sucessfully";
	}
	
	@DeleteMapping("Restorerole/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		rolservice.Restorerole(id);
		Roles r=new Roles(id, "", 0);
		return "Role Restored Sucessfully";
	}
}
