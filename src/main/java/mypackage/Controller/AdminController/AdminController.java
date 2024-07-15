package mypackage.Controller.AdminController;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mypackage.Services.AdminServices.AdminServices;
import mypackage.model.Admin.Admin_details;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class AdminController {

	@Autowired
	AdminServices adminservice;
	
	@GetMapping("GetAdmindetails")
	public List<Admin_details>getall()
	{
		return adminservice.getalldetails();
	}
	
	@GetMapping("GetAdminByid/{id}")
	public Admin_details getbyid(@PathVariable("id")int id)
	{
		return adminservice.Getadmindetailsbyid(id);
	}
	
	@PostMapping("AddAdmindetail")
	public String Add(@RequestBody Admin_details a)
	{
		adminservice.AddAdmindetails(a);
		return "Admin Details Added Sucessfully";
	}
	
	@PutMapping("UpdateAdmindetails")
	public String update(@RequestBody Admin_details a)
	{
		adminservice.UpdateAdmindetail(a);
		return "Admin Details Updated Sucessfully";
	}
	
	@DeleteMapping("DeleteAdmindetails/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		adminservice.DeleteAdmindetails(id);
		return "Admin Details Deleted Sucessfully";
	}
	
	@DeleteMapping("RestoreAdmindetails/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		adminservice.RestoreAdmindetails(id);
		return "Admin Details Restore Sucessfully";
	}
	
	@PostMapping("AdminLogin")
	public Admin_details adminlogin(@RequestParam("admin_unique_name")String unique_name,@RequestParam("password")String pass)
	{
		return adminservice.Adminlogin(unique_name, pass);
	}
}