package mypackage.Services.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Specializations;
import mypackage.model.user.User_details;
import mypackage.model.user.User_qualification;
import mypackage.repositories.User.IUserqualificationRepositores;

@Service
public class UserqualificationServices {

	
	@Autowired
	IUserqualificationRepositores qualrepo;
	
	public List<User_qualification>getalluserqualification()
	{
		List<User_qualification>lst=new ArrayList<User_qualification>();
		for(User_qualification u:qualrepo.findAll())
		{
			if(u.getFlag()==0)
			{
				User_details ud=new User_details(u.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
				Specializations sp=new Specializations(u.getSpecilization().getSpecialization_id(), null, 0, null);
				User_qualification q=new User_qualification(u.getUser_qualification_id(), u.getUniversity(), u.getPassing_year(), u.getMedium(), u.getPercentage(), u.getFlag(), ud, sp);
				
				lst.add(q);
			}
		}
		return lst;
	}
	
	public void Adduserqualification(User_qualification u)
	{
		qualrepo.save(u);
	}
	
	public void Updateuserqualification(User_qualification u)
	{
		qualrepo.save(u);
	}
	
	public User_qualification getuserqualificationbyid(int id)
	{
		User_qualification u=(User_qualification)qualrepo.findById(id).get();
		
		User_details ud=new User_details(u.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		Specializations sp=new Specializations(u.getSpecilization().getSpecialization_id(), null, 0, null);
		User_qualification q=new User_qualification(u.getUser_qualification_id(), u.getUniversity(), u.getPassing_year(), u.getMedium(), u.getPercentage(), u.getFlag(), ud, sp);
		
		return(q);
	}
	
	public void Deleteuserqualification(int id)
	{
		User_qualification u=qualrepo.findById(id).get();
		u.setFlag(1);
		qualrepo.save(u);
	}
	
	public void Restoreuserqualification(int id)
	{
		User_qualification u=qualrepo.findById(id).get();
		u.setFlag(0);
		qualrepo.save(u);
	}
}
