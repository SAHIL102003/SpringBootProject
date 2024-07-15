package mypackage.Services.Userpost;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import mypackage.model.Userposts.Post_comment;
import mypackage.model.Userposts.Post_comment_reply;
import mypackage.model.user.User_details;
import mypackage.repositories.Userposts.PostcommentrRepliesRepositories;

@Service
public class PostCommentRepliesServices {

	@Autowired
	PostcommentrRepliesRepositories replyrepo;
	
	
	public List<Post_comment_reply>getallpostcommentreplies()
	{
		List<Post_comment_reply>lst=new ArrayList<Post_comment_reply>();
		for(Post_comment_reply p:replyrepo.findAll())
		{
			if(p.getFlag()==0)
			{
				Post_comment pc=new Post_comment(p.getPost_comment().getComment_id(), null, null, null, null, null, 0);
				User_details u=new User_details(p.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
				Post_comment_reply ps=new Post_comment_reply(p.getReply_id(), pc, p.getReply_date(), u, p.getReply_message(), p.getReply_photo(), p.getFlag());
				
				lst.add(ps);
			}
		}
		
		return lst;
	}
	
	public void Addpostcommentreplies(Post_comment_reply p)
	{
		 replyrepo.save(p);
	}
	
	public void Updatepostcommentreplies(Post_comment_reply p)
	{
		 replyrepo.save(p);
	}
	
	public Post_comment_reply getcommentrepliesbyid(int id)
	{
		Post_comment_reply p=(Post_comment_reply)replyrepo.findById(id).get();
		Post_comment pc=new Post_comment(p.getPost_comment().getComment_id(), null, null, null, null, null, 0);
		User_details u=new User_details(p.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		Post_comment_reply ps=new Post_comment_reply(p.getReply_id(), pc, p.getReply_date(), u, p.getReply_message(), p.getReply_photo(), p.getFlag());
		
		return(ps);
	}
	
	public void Deletepostcommentreply(int id)
	{
		Post_comment_reply p=replyrepo.findById(id).get();
		p.setFlag(1);
		replyrepo.save(p);
	}
	
	public void Restorepostcommentreply(int id)
	{
		Post_comment_reply p=replyrepo.findById(id).get();
		p.setFlag(0);
		replyrepo.save(p);
	}
	
	public List<Post_comment_reply>getallreplybypostid(int id)
	{
		List<Post_comment_reply>lst=new ArrayList<Post_comment_reply>();
		for(Post_comment_reply p:replyrepo.findAll())
		{
			if(p.getPost_comment().getComment_id()==id)
			{
				Post_comment pc=new Post_comment(p.getPost_comment().getComment_id(), null, null, null, null, null, 0);
				User_details u=new User_details(p.getUser_details().getUser_id(), p.getUser_details().getFirst_name(), null, p.getUser_details().getLast_name(), null, null, null, "upload/"+ p.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
				Post_comment_reply ps=new Post_comment_reply(p.getReply_id(), pc, p.getReply_date(), u, p.getReply_message(), "upload/"+p.getReply_photo(), p.getFlag());
				lst.add(ps);
			}
		}
		return lst;
	}
}
