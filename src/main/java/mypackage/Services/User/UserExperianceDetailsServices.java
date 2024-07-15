package mypackage.Services.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Designations;
import mypackage.model.user.Experience_Details;
import mypackage.model.user.User_details;
import mypackage.repositories.User.IExperiance_details_repositories;

@Service
public class UserExperianceDetailsServices {

	@Autowired
	IExperiance_details_repositories userexrepo;
	
	public List<Experience_Details>getallexperiancedetails(){
		List<Experience_Details>lst=new ArrayList<Experience_Details>();
		for(Experience_Details e:userexrepo.findAll())
		{
			if(e.getFlag()==0)
			{
				User_details u=new User_details(e.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
				Designations d=new Designations(e.getDesignation().getDesignation_id(), null, 0);
				Experience_Details ex=new Experience_Details(e.getExperience_id(), e.getCompany_name(), e.getFrom_year(), e.getTo_year(), e.getJob_descrption(), e.getFlag(), u, d);
				
				lst.add(ex);
			}
		}
		return lst;
	}

	public void Addexperiance(Experience_Details e)
	{
		userexrepo.save(e);
	}
	
	public void Updateexperiance(Experience_Details e)
	{
		userexrepo.save(e);
	}
	
	public Experience_Details Getexperiancebyid(int id)
	{
		Experience_Details e=(Experience_Details)userexrepo.findById(id).get();
		
		User_details u=new User_details(e.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		Designations d=new Designations(e.getDesignation().getDesignation_id(), null, 0);
		Experience_Details ex=new Experience_Details(e.getExperience_id(), e.getCompany_name(), e.getFrom_year(), e.getTo_year(), e.getJob_descrption(), e.getFlag(), u, d);
		
		return(ex);
		
	}
	
	public void DeleteExperiance(int id)
	{
		Experience_Details e=userexrepo.findById(id).get();
		e.setFlag(1);
		userexrepo.save(e);
	}
	
	public void RestoreExperiance(int id)
	{
		Experience_Details e=userexrepo.findById(id).get();
		e.setFlag(0);
		userexrepo.save(e);
	}
	
	
}
