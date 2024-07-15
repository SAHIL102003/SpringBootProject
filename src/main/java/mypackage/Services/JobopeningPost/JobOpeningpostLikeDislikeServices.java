package mypackage.Services.JobopeningPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Job_Opening.Job_opening;
import mypackage.model.Job_Opening.Jobopening_post_like_dislike;
import mypackage.model.user.User_details;
import mypackage.repositories.Job_opening.Ijobopeningpost_like_dislike;

@Service
public class JobOpeningpostLikeDislikeServices {

	@Autowired
	Ijobopeningpost_like_dislike likerepo;
	
	public List<Jobopening_post_like_dislike>getallreactions()
	{
		List<Jobopening_post_like_dislike>lst=new ArrayList<Jobopening_post_like_dislike>();
		for(Jobopening_post_like_dislike j:likerepo.findAll())
		{
			if(j.getFlag()==0)
			{
				User_details ud=new User_details(j.getUser_details().getUser_id(), j.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

				Job_opening jo=new Job_opening(j.getJob_opening().getOpening_id(), null, null, null, null, null, null, 0, 0);
				
				Jobopening_post_like_dislike p=new Jobopening_post_like_dislike(j.getLike_dislike_id(), jo, j.getLike_dislike_date(), ud, j.getIs_like(),j.getFlag());
				
				lst.add(p);
			}
		}
		return lst;
	}
	
	public void Addreaction(Jobopening_post_like_dislike j)
	{
		likerepo.save(j);
	}
	
	public void Updatereaction(Jobopening_post_like_dislike j)
	{
		likerepo.save(j);
	}
	
	public Jobopening_post_like_dislike getreactionbyid(int id)
	{
		Jobopening_post_like_dislike j=(Jobopening_post_like_dislike)likerepo.findById(id).get();
		
		User_details ud=new User_details(j.getUser_details().getUser_id(), j.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

		Job_opening jo=new Job_opening(j.getJob_opening().getOpening_id(), null, null, null, null, null, null, 0, 0);
		
		Jobopening_post_like_dislike p=new Jobopening_post_like_dislike(j.getLike_dislike_id(), jo, j.getLike_dislike_date(), ud, j.getIs_like(),j.getFlag());
		
		return(p);
	}
	
	public void Deletereaction(int id)
	{
		Jobopening_post_like_dislike j=likerepo.findById(id).get();
		j.setFlag(1);
		likerepo.save(j);
	}
	
	public void Restorereaction(int id)
	{
		Jobopening_post_like_dislike j=likerepo.findById(id).get();
		j.setFlag(0);
		likerepo.save(j);
	}
}
