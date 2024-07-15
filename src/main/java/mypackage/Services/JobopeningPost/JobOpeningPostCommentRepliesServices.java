package mypackage.Services.JobopeningPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Job_Opening.Jobopening_post_comment;
import mypackage.model.Job_Opening.Jobopening_post_comment_repies;
import mypackage.model.user.User_details;
import mypackage.repositories.Job_opening.Ijobopening_post_comment_replies;

@Service
public class JobOpeningPostCommentRepliesServices {

	@Autowired
	Ijobopening_post_comment_replies replyrepo;
	
	public List<Jobopening_post_comment_repies>getallreplies()
	{
		List<Jobopening_post_comment_repies>lst=new ArrayList<Jobopening_post_comment_repies>();
		for(Jobopening_post_comment_repies j:replyrepo.findAll())
		{
			if(j.getFlag()==0)
			{
				User_details ud=new User_details(j.getUser_details().getUser_id(), j.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
				
				Jobopening_post_comment pc=new Jobopening_post_comment(j.getJob_opening_post_comment().getComment_id(), null, null, null, null, null, 0);
				
				Jobopening_post_comment_repies pr=new Jobopening_post_comment_repies(j.getReply_id(),pc, j.getReply_date(), ud, j.getReply_message(), j.getComment_photo(), j.getFlag());
				
				lst.add(pr);
			}
		}
		return lst;
	}
	
	public void Addreplytocomment(Jobopening_post_comment_repies j)
	{
		replyrepo.save(j);
	}
	
	public void Updatereplytocomment(Jobopening_post_comment_repies j)
	{
		replyrepo.save(j);
	}
	
	public Jobopening_post_comment_repies getreplybyid(int id)
	{
		Jobopening_post_comment_repies j=(Jobopening_post_comment_repies)replyrepo.findById(id).get();
		
		Jobopening_post_comment pc=new Jobopening_post_comment(j.getJob_opening_post_comment().getComment_id(), null, null, null, null, null, 0);

		User_details ud=new User_details(j.getUser_details().getUser_id(), j.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		Jobopening_post_comment_repies pr=new Jobopening_post_comment_repies(j.getReply_id(),pc, j.getReply_date(), ud, j.getReply_message(), j.getComment_photo(), j.getFlag());
		return (pr);
	}
	
	public void deletereply(int id)
	{
		Jobopening_post_comment_repies j=replyrepo.findById(id).get();
		j.setFlag(1);
		replyrepo.save(j);
	}
	
	public void Restorereply(int id)
	{
		Jobopening_post_comment_repies j=replyrepo.findById(id).get();
		j.setFlag(1);
		replyrepo.save(j);
	}
}
