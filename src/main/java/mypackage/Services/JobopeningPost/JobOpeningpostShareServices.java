package mypackage.Services.JobopeningPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Job_Opening.Job_opening;
import mypackage.model.Job_Opening.Jobopening_post_share;
import mypackage.model.user.User_details;
import mypackage.repositories.Job_opening.Ijobopening_post_share;

@Service
public class JobOpeningpostShareServices {

	@Autowired
	Ijobopening_post_share sharereppo;
	
	public List<Jobopening_post_share>getallshares()
	{
		List<Jobopening_post_share>lst=new ArrayList<Jobopening_post_share>();
		for(Jobopening_post_share j:sharereppo.findAll())
		{
			if(j.getFlag()==0)
			{
				User_details ud=new User_details(j.getUser_details().getUser_id(), j.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

				Job_opening jo=new Job_opening(j.getJob_opening().getOpening_id(), null, null, null, null, null, null, 0, 0);
			
				Jobopening_post_share ps=new Jobopening_post_share(j.getShare_id(), jo, j.getShare_date(), ud, j.getFlag());
				
				lst.add(ps);
			}
		}
		return lst;
	}
	
	public void AddShareonpost(Jobopening_post_share s)
	{
		sharereppo.save(s);
	}
	
	public void UpdateShareonpost(Jobopening_post_share s)
	{
		sharereppo.save(s);
	}
	
	public Jobopening_post_share getsharebyid(int id)
	{
		Jobopening_post_share j=(Jobopening_post_share)sharereppo.findById(id).get();
		
		User_details ud=new User_details(j.getUser_details().getUser_id(), j.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

		Job_opening jo=new Job_opening(j.getJob_opening().getOpening_id(), null, null, null, null, null, null, 0, 0);
	
		Jobopening_post_share ps=new Jobopening_post_share(j.getShare_id(), jo, j.getShare_date(), ud, j.getFlag());
		
		return (ps);
		
	}
	public void Deleteshare(int id)
	{
		Jobopening_post_share j=sharereppo.findById(id).get();
		j.setFlag(1);
		sharereppo.save(j);
	}
	
	public void Restoreshare(int id)
	{
		Jobopening_post_share j=sharereppo.findById(id).get();
		j.setFlag(0);
		sharereppo.save(j);
	}
}
