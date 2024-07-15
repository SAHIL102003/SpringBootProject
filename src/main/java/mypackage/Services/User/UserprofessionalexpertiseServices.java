package mypackage.Services.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Specializations;
import mypackage.model.user.User_details;
import mypackage.model.user.User_professional_expertise;
import mypackage.repositories.User.IUser_professional_expertiseRepositories;

@Service
public class UserprofessionalexpertiseServices {

	
	@Autowired
	IUser_professional_expertiseRepositories prorepo;
	
	public List<User_professional_expertise>getallprofessionalexpertise()
	{
		List<User_professional_expertise>lst=new ArrayList<User_professional_expertise>();
		for(User_professional_expertise u:prorepo.findAll())
		{
			if(u.getFlag()==0)
			{
				User_details ud=new User_details(u.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
				Specializations sp=new Specializations(u.getSpecilization().getSpecialization_id(), null, 0, null);
				User_professional_expertise up=new User_professional_expertise(u.getExpertise_id(), u.getDescription(), u.getFlag(), ud, sp);
				
				lst.add(up);
			}
		}
		return lst;
	}
	
	public void Addprofessionalexpertise(User_professional_expertise u)
	{
		prorepo.save(u);
	}
	
	public void Updateprofessional(User_professional_expertise u)
	{
		prorepo.save(u);
	}
	
	public User_professional_expertise Getallprofessionalexpertisebyid(int id)
	{
		User_professional_expertise u= (User_professional_expertise)prorepo.findById(id).get();
		
		User_details ud=new User_details(u.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		Specializations sp=new Specializations(u.getSpecilization().getSpecialization_id(), null, 0, null);
		User_professional_expertise up=new User_professional_expertise(u.getExpertise_id(), u.getDescription(), u.getFlag(), ud, sp);
		
		return(up);
	}
	
	public void DeleteprofessionalExpertise(int id)
	{
		User_professional_expertise u=prorepo.findById(id).get();
		u.setFlag(1);
		prorepo.save(u);
	}
	
	public void RestoreprofessionalExpertise(int id)
	{
		User_professional_expertise u=prorepo.findById(id).get();
		u.setFlag(0);
		prorepo.save(u);
	}
}
