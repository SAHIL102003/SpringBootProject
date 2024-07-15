package mypackage.Services.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import mypackage.model.mastertables.Genders;
import mypackage.model.mastertables.Locations;
import mypackage.model.mastertables.Roles;
import mypackage.model.user.User_details;
import mypackage.repositories.Mastertables.IGender_repositories;
import mypackage.repositories.User.IUserdetailsRepositories;

@Service
public class UserdetailsServices {

	
	@Autowired
	IUserdetailsRepositories userdetailrepo;
	@Autowired
	IGender_repositories genderrepo;
	
	public List<User_details>getallusers()
	{
		List<User_details>lst=new ArrayList<User_details>();
		for(User_details u:userdetailrepo.findAll())
		{
			if(u.getFlag()==0)
			{
				Genders gn=genderrepo.findById(u.getGender().getGender_id()).get();
				Genders g=new Genders(gn.getGender_id(),gn.getGender(), 0);
				Locations l=new Locations(u.getLocation().getLocation_id(), null, 0, null);
				Roles r=new Roles(u.getRoles().getRole_id(), null, 0);
				
				User_details ud=new User_details(u.getUser_id(), u.getFirst_name(), u.getMiddle_name(), u.getLast_name(), u.getLocal_address(), u.getBirth_date(), u.getJoining_date(),"upload/"+u.getUser_photo(), u.getMobile_no(), u.getEmail_address(), u.getUser_name(), 
						u.getIs_premium(), u.getPassword(), u.getFlag(), g, l, r);
				lst.add(ud);
			}
		}
		return lst;
	}

	public void Adduser(User_details u)
	{
		userdetailrepo.save(u);
	}
	
	public void Updateuser(User_details u)
	{
		userdetailrepo.save(u);
	}
	public void ActivateUser(int id)
	{
		User_details u=(User_details)userdetailrepo.findById(id).get();
		u.setIs_premium(1);
		userdetailrepo.save(u);
	}
	public void DeActivateUser(int id)
	{
		User_details u=(User_details)userdetailrepo.findById(id).get();
		u.setIs_premium(0);
		userdetailrepo.save(u);
	}
	public User_details Getuserbyid(int id)
	{
		User_details u=(User_details)userdetailrepo.findById(id).get();
		
		Genders g=new Genders(u.getGender().getGender_id(), null, 0);
		Locations l=new Locations(u.getLocation().getLocation_id(), null, 0, null);
		Roles r=new Roles(u.getRoles().getRole_id(), null, 0);
		
		User_details ud=new User_details(u.getUser_id(), u.getFirst_name(), u.getMiddle_name(), u.getLast_name(), u.getLocal_address(), u.getBirth_date(), u.getJoining_date(), "upload/"+u.getUser_photo(), u.getMobile_no(), u.getEmail_address(), u.getUser_name(), 
				u.getIs_premium(), u.getPassword(), u.getFlag(), g, l, r);
		
		return(ud);
	}
	
	public void Deleteuser(int id)
	{
		User_details u=userdetailrepo.findById(id).get();
		u.setFlag(1);
		userdetailrepo.save(u);
	}
	
	public void Restoreuser(int id)
	{
		User_details u=userdetailrepo.findById(id).get();
		u.setFlag(1);
		userdetailrepo.save(u);
	}
	
	public User_details Login(String email, String pass)
	{

		User_details user=null;
		for(User_details u : userdetailrepo.findAll()) {
			if(u.getEmail_address().equals(email) && u.getPassword().equals(pass)) {
			user=u;
			
			Locations loc=new Locations(user.getLocation().getLocation_id(), user.getLocation().getLocation_name(), 0, null);
			user =new User_details(user.getUser_id(), user.getFirst_name(), user.getMiddle_name(), user.getLast_name(), user.getLocal_address(), user.getBirth_date(), user.getJoining_date(), "upload/"+user.getUser_photo(), user.getMobile_no(), user.getEmail_address(), null, user.getIs_premium(), null, 0, null, loc, null);
			break;
			}
		}
		return user;
		

	}
}
