package mypackage.Controller.MastertableController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mypackage.Services.Mastertable.PostcategoriesServices;
import mypackage.model.mastertables.Postcategories;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class PostcategoriesController {

	@Autowired
	PostcategoriesServices postservice;
	
	@GetMapping("postcategory")
	public List<Postcategories>getall()
	{
		return postservice.getallpostcat();
	}
	
	@PostMapping("postcategory")
	public String addpost(@RequestBody Postcategories p)
	{
		postservice.Addpostcategories(p);
		return "PostCategory Added Sucessfully";
	}
	
	@PutMapping("postcategory")
	public String update(@RequestBody Postcategories p)
	{
		postservice.updatecat(p);
		return "Postcatogory Updated Sucessfully";
	}
	
	@GetMapping("postcategory/{id}")
	public Postcategories getbyid(@PathVariable("id")int id)
	{
		return postservice.getbyid(id);
	}
	
	@DeleteMapping("Deletepostpostcategory/{id}")
	public String delete(@PathVariable("id")int id)
	{
		postservice.Deletecat(id);
		Postcategories p=new Postcategories(id, "", 0);
		return "PostCategories Deleted Sucessfully";
	}
	
	@DeleteMapping("Restorepostpostcategory/{id}")
	public String REstore(@PathVariable("id")int id)
	{
		postservice.Restorecat(id);
		Postcategories p=new Postcategories(id, "", 0);
		return "PostCategories Restored Sucessfully";
	}
}

