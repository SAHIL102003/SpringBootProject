package mypackage.Services.AdminServices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Admin.Admin_details;
import mypackage.repositories.Admin.IAdminRepositories;

@Service
public class AdminServices {

	@Autowired
	IAdminRepositories adminrepo;
	
	public List<Admin_details>getalldetails()
	{
		List<Admin_details>lst=new ArrayList<Admin_details>();
		for(Admin_details a:adminrepo.findAll())
		{
			if(a.getFlag()==0)
			{
				Admin_details ad=new Admin_details(a.getAdmin_id(), a.getFull_name(), a.getAdmin_unique_name(), a.getPassword(), a.getFlag());
				lst.add(ad);
			}
		}
		return lst;
	}
	
	public void AddAdmindetails(Admin_details a)
	{
		adminrepo.save(a);
	}
	
	public void UpdateAdmindetail(Admin_details a)
	{
		adminrepo.save(a);
	}
	
	public Admin_details Getadmindetailsbyid(int id)
	{
		Admin_details a=(Admin_details)adminrepo.findById(id).get();
		Admin_details ad=new Admin_details(a.getAdmin_id(), a.getFull_name(), a.getAdmin_unique_name(), a.getPassword(), a.getFlag());
		return(ad);
	}
	
	public void DeleteAdmindetails(int id)
	{
		Admin_details a=adminrepo.findById(id).get();
		a.setFlag(1);
		adminrepo.save(a);
	}
	
	public void RestoreAdmindetails(int id)
	{
		Admin_details a=adminrepo.findById(id).get();
		a.setFlag(0);
		adminrepo.save(a);
	}
	
	public Admin_details Adminlogin(String unique_name,String pass)
	{
		Admin_details admin=null;
		for(Admin_details a:adminrepo.findAll())
		{
			if( a.getAdmin_unique_name().equals(unique_name) && a.getPassword().equals(pass))
			{
				admin=a;
			}
		}
		Admin_details ad=new Admin_details(admin.getAdmin_id(), admin.getFull_name(), admin.getAdmin_unique_name(), admin.getPassword(), admin.getFlag());
		return ad;
		
	}
}
