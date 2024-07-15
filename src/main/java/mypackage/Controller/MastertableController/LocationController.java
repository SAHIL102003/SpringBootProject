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

import mypackage.Services.Mastertable.LocationsServices;
import mypackage.model.mastertables.Locations;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class LocationController {

	@Autowired
	LocationsServices locservice;
	
	@GetMapping("location")
	public List<Locations>getall(){
		return locservice.getalllocation();
	}
	@GetMapping("citywiselocations/{id}")
	public List<Locations>getcitywiselocations(@PathVariable("id")int id){
		return locservice.getCityWiseLocations(id);
	}
	
	@PostMapping("location")
	public String add(@RequestBody Locations l)
	{
		locservice.Addlocation(l);
		return "Location Added Sucessfully";
	}
	
	@PutMapping("location")
	public String update(@RequestBody Locations l)
	{
		locservice.Updatelocation(l);
		return "Location Updated Sucessfully";
	}
	
	@DeleteMapping("Deletelocation/{id}")
	public String delete(@PathVariable("id")int id)
	{
		locservice.Deletelocation(id);
		Locations l=new Locations(id, "", 0, null);
		return "Location Deleted Sucessfully";
	}
	
	@DeleteMapping("Restorelocation/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		locservice.Restorelocation(id);
		Locations l=new Locations(id, "", 0, null);
		return "Location Restored Sucessfully";
	}
}
