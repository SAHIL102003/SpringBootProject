package mypackage.Services.JobopeningPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Job_Opening.Job_opening;
import mypackage.model.Job_Opening.Jobopening_post_comment;
import mypackage.model.user.User_details;
import mypackage.repositories.Job_opening.Ijobopening_post_comment;

@Service
public class JobOpeningPostCommentServices {

	@Autowired
	Ijobopening_post_comment commentrepo;
	
	public List<Jobopening_post_comment>getallcomments()
	{
		List<Jobopening_post_comment>lst=new ArrayList<Jobopening_post_comment>();
		for(Jobopening_post_comment j:commentrepo.findAll())
		{
			if(j.getFlag()==0)
			{
				User_details ud=new User_details(j.getUser_details().getUser_id(), j.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

				Job_opening jo=new Job_opening(j.getJob_opening().getOpening_id(), null, null, null, null, null, null, 0, 0);
				
				Jobopening_post_comment jp=new Jobopening_post_comment(j.getComment_id(), jo, j.getComment_date(), ud, j.getComment_message(), j.getComment_photo(), j.getFlag());
				
				lst.add(jp);
			}
		}
		return lst;
	}
	
	public void Addcommentonpost(Jobopening_post_comment j)
	{
		commentrepo.save(j);
	}
	
	public void UpdateCommentonpost(Jobopening_post_comment j)
	{
		commentrepo.save(j);
	}
	
	public Jobopening_post_comment getcommentbyid(int id)
	{
		Jobopening_post_comment j=(Jobopening_post_comment)commentrepo.findById(id).get();
		
		User_details ud=new User_details(j.getUser_details().getUser_id(), j.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

		Job_opening jo=new Job_opening(j.getJob_opening().getOpening_id(), null, null, null, null, null, null, 0, 0);
		
		Jobopening_post_comment jp=new Jobopening_post_comment(j.getComment_id(), jo, j.getComment_date(), ud, j.getComment_message(), j.getComment_photo(), j.getFlag());
		
		return (jp);
		
	}
	
	public void Deletecommentonpost(int id)
	{
		Jobopening_post_comment j=commentrepo.findById(id).get();
		j.setFlag(1);
		commentrepo.save(j);
	}
	
	public void Restorecommentonpost(int id)
	{
		Jobopening_post_comment j=commentrepo.findById(id).get();
		j.setFlag(0);
		commentrepo.save(j);
	}
	
	
}
