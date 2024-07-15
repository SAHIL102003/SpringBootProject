package mypackage.Services.JobopeningPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Job_Opening.Job_opening;
import mypackage.model.user.User_details;
import mypackage.repositories.Job_opening.Ijob_opening;

@Service
public class Job_OpeningServices {

	@Autowired
	Ijob_opening openingrepo;
	
	public List<Job_opening>getallopenings()
	{
		List<Job_opening>lst=new ArrayList<Job_opening>();
		for(Job_opening j:openingrepo.findAll())
		{
			if(j.getFlag()==0)
			{
				User_details ud=new User_details(j.getUser_details().getUser_id(), j.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

				Job_opening jo=new Job_opening(j.getOpening_id(), j.getOpening_date(), ud, j.getCompany_name(), j.getJob_title(), j.getExperiance_required(), j.getJob_description(), j.getFlag(), j.getIs_active());
				lst.add(jo);
				
			}
		}
		return lst;
	}
	
	public void Addjobopening(Job_opening j)
	{
		openingrepo.save(j);
	}
	

	public void Updatejobopening(Job_opening j)
	{
		openingrepo.save(j);
	}
	
	public Job_opening Getjobopeningbyid(int id)
	{
		Job_opening j=(Job_opening)openingrepo.findById(id).get();
		User_details ud=new User_details(j.getUser_details().getUser_id(), j.getUser_details().getFirst_name(), null, j.getUser_details().getLast_name(), null, null, null,"upload/"+ j.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
		
		Job_opening jo=new Job_opening(j.getOpening_id(), j.getOpening_date(), ud, j.getCompany_name(), j.getJob_title(), j.getExperiance_required(), j.getJob_description(), j.getFlag(), j.getIs_active());
		return (jo);
		
	}
	
	public void Deletejobopening(int id)
	{
		Job_opening j=openingrepo.findById(id).get();
		j.setFlag(1);
		openingrepo.save(j);
	}
	
	public void Restorejobopening(int id)
	{
		Job_opening j=openingrepo.findById(id).get();
		j.setFlag(0);
		openingrepo.save(j);
	}
}
