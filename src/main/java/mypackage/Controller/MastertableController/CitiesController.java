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

import mypackage.Services.Mastertable.CitiesServices;
import mypackage.model.mastertables.Cities;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE},allowedHeaders = "*")
public class CitiesController {

	@Autowired
	CitiesServices cityservice;
	
	@GetMapping("city")
	public List<Cities>getall(){
		return cityservice.Getallcities();
	}
	@GetMapping("statewisecities/{id}")
	public List<Cities>getstatwisecities(@PathVariable("id")int id){
		return cityservice.GetStateWiseCities(id);
	}
	@GetMapping("city/{id}")
	public Cities getbyid(@PathVariable("id")int id)
	{
		return cityservice.Getbyid(id);
	}
	
	@PutMapping("city")
	public String update(@RequestBody Cities c)
	{
		cityservice.UpdateCities(c);
		return "City Updated Sucessfully";
	}
	
	@PostMapping("city")
	public String Addcity(@RequestBody Cities c)
	{
		cityservice.AddCities(c);
		return "city Added Sucessfully";
	}
	
	@DeleteMapping("Deletecity/{id}")
	public String delete(@PathVariable("id")int id)
	{
		cityservice.DeleteCity(id);
		Cities ct=new Cities(id,"", 0, null);
		return "City Deleted Sucessfully";
	}
	
	@DeleteMapping("Restorecity/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		cityservice.RestoreCity(id);
		Cities ct=new Cities(id,"", 0, null);
		return "City Restored Sucessfully";
	}
}
