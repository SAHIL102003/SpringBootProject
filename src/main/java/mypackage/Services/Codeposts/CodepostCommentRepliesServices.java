package mypackage.Services.Codeposts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Codes.Code_post_comment;
import mypackage.model.Codes.Code_post_comment_replies;
import mypackage.model.user.User_details;
import mypackage.repositories.Codes.Icode_post_comment_replies;

@Service
public class CodepostCommentRepliesServices {

	
	@Autowired
	Icode_post_comment_replies replyrepo;
	
	public List<Code_post_comment_replies>getallreplies()
	{
		List<Code_post_comment_replies>lst=new ArrayList<Code_post_comment_replies>();
		for(Code_post_comment_replies c:replyrepo.findAll())
		{
			if(c.getFlag()==0)
			{
			
				User_details ud=new User_details(c.getUser_details().getUser_id(), c.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
				Code_post_comment cc=new Code_post_comment(c.getCode_comment().getComment_id(), null, null, null, null, null, 0);
				Code_post_comment_replies cp=new Code_post_comment_replies(c.getReply_id(), cc, c.getReply_date(), ud, c.getReply_message(), c.getComment_photo(), c.getFlag());
				
				lst.add(cp);
			}
		}
		return lst;
	}
	
	public void Addcommentreply(Code_post_comment_replies c)
	{
		replyrepo.save(c);
	}
	
	public void Updatecommentreply(Code_post_comment_replies c)
	{
		replyrepo.save(c);
	}
	
	public Code_post_comment_replies getcommentreplybyid(int id)
	{
		Code_post_comment_replies c=(Code_post_comment_replies)replyrepo.findById(id).get();
		
		User_details ud=new User_details(c.getUser_details().getUser_id(), c.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		Code_post_comment cc=new Code_post_comment(c.getCode_comment().getComment_id(), null, null, null, null, null, 0);
		Code_post_comment_replies cp=new Code_post_comment_replies(c.getReply_id(), cc, c.getReply_date(), ud, c.getReply_message(), c.getComment_photo(), c.getFlag());
		
		return(cp);
	}
	
	public void DeleteCommentreply(int id)
	{
		Code_post_comment_replies c=replyrepo.findById(id).get();
		c.setFlag(1);
		replyrepo.save(c);
	}
	
	public void RestoreCommentreply(int id)
	{
		Code_post_comment_replies c=replyrepo.findById(id).get();
		c.setFlag(0);
		replyrepo.save(c);
	}
	
	
}
