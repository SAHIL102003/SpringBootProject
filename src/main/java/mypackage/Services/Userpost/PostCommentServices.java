package mypackage.Services.Userpost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.NlpPipeline;
import mypackage.SentimentModel;
import mypackage.model.Userposts.Post_Comment_Model;
import mypackage.model.Userposts.Post_comment;
import mypackage.model.Userposts.User_posts;
import mypackage.model.user.User_details;
import mypackage.repositories.Userposts.IUserpostRepositories;
import mypackage.repositories.Userposts.PostcommentRepositories;

@Service
public class PostCommentServices {

	@Autowired
	PostcommentRepositories commentrepo;
	
	@Autowired
	IUserpostRepositories postrepo;
	
	public List<Post_comment>getallpostcomment()
	{
		List<Post_comment>lst=new ArrayList<Post_comment>();
		for(Post_comment p:commentrepo.findAll())
		{
			if(p.getFlag()==0)
			{
				User_posts up=new User_posts(p.getUser_post().getPost_id(), null, null, null, null, null, 0, 0);
				User_details ud=new User_details(p.getUser_details().getUser_id(), p.getUser_details().getFirst_name(), null, p.getUser_details().getLast_name(), null, null, null,"upload/"+ p.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
				Post_comment ps=new Post_comment(p.getComment_id(), up, p.getComment_date(), ud, p.getComment_message(),"upload/"+ p.getComment_photo(), p.getFlag());
				
				lst.add(ps);
			}
		}
		return lst;
	}
	
	
	
	public void AddCommenttopost(Post_comment p)
	{
		commentrepo.save(p);
	}
	
	public void UpdateCommentpost(Post_comment p)
	{
		commentrepo.save(p);
	}
	
	public Post_comment Getpostcommentbyid(int id)
	{
		Post_comment p=(Post_comment)commentrepo.findById(id).get();
		
		User_posts up=new User_posts(p.getUser_post().getPost_id(), null, null, null, null, null, 0, 0);
		User_details ud=new User_details(p.getUser_details().getUser_id(), p.getUser_details().getFirst_name(), null, p.getUser_details().getLast_name(), null, null, null,"upload/"+ p.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
		Post_comment ps=new Post_comment(p.getComment_id(), up, p.getComment_date(), ud, p.getComment_message(), "upload/"+p.getComment_photo(), p.getFlag());
		
		return(ps);
	}
	
	public void Deletepostcomment(int id)
	{
		Post_comment p = commentrepo.findById(id).get();
		p.setFlag(1);
		commentrepo.save(p);
	}
	
	public void Restorepostcomment(int id)
	{
		Post_comment p = commentrepo.findById(id).get();
		p.setFlag(0);
		commentrepo.save(p);
	}
	
	
//	public List<Post_comment> Getpostwisecomment(int id)
//	{
//		List<Post_comment>lst=new ArrayList<Post_comment>();
//		for(Post_comment p:commentrepo.findAll())
//		{
//				if(p.getUser_post().getPost_id()==(id))
//				{
//					User_details ud=new User_details(p.getComment_id(), p.getUser_details().getFirst_name(), p.getUser_details().getMiddle_name(), p.getUser_details().getLast_name(), null, null, null, null, null, null, null, 0, null, 0, null, null, null);
//					Post_comment ps=new Post_comment(p.getComment_id(), p.getUser_post(), p.getComment_date(), ud, p.getComment_message(), "upload/"+p.getComment_photo(), p.getFlag());
//					
//					lst.add(ps);
//					
//				}
//				
//				
//		}
//	
//		return lst;
//		
//		
//	}
	
	public List<Post_comment>getallpostcommentbypostid( int id)
	{
		List<Post_comment>lst=new ArrayList<Post_comment>();
		for(Post_comment p:commentrepo.findAll())
		{
			if(p.getUser_post().getPost_id()==id)
			{
				User_posts up=new User_posts(p.getUser_post().getPost_id(), null, null, null, null,"upload/"+ p.getUser_post().getPhoto(), 0, 0);
				User_details ud=new User_details(p.getUser_details().getUser_id(), p.getUser_details().getFirst_name(), null, p.getUser_details().getLast_name(), null, null, null,"upload/"+ p.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
				Post_comment ps=new Post_comment(p.getComment_id(), up, p.getComment_date(), ud, p.getComment_message(),"upload/"+ p.getComment_photo(), p.getFlag());
				
				lst.add(ps);
			}
		}
		return lst;
	}
	
	public List<Post_Comment_Model>getallpostcommentWitSentimentbypostid( int id)
	{
		List<Post_Comment_Model>lst=new ArrayList<Post_Comment_Model>();
		for(Post_comment p:commentrepo.findAll())
		{
			if(p.getUser_post().getPost_id()==id)
			{
				User_posts up=new User_posts(p.getUser_post().getPost_id(), null, null, null, null,"upload/"+ p.getUser_post().getPhoto(), 0, 0);
				User_details ud=new User_details(p.getUser_details().getUser_id(), p.getUser_details().getFirst_name(), null, p.getUser_details().getLast_name(), null, null, null,"upload/"+ p.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
				 NlpPipeline.init();
				 List<SentimentModel>lstdata=   NlpPipeline.estimatingSentiment(p.getComment_message());
				 	SentimentModel s=lstdata.get(0);
				 	System.out.println(s.getSentence()+" "+s.getSentimentInt()+" "+s.getSentimentName());
				
				Post_Comment_Model ps=new Post_Comment_Model(p.getComment_id(), up, p.getComment_date(), ud, p.getComment_message(),"upload/"+ p.getComment_photo(), p.getFlag(),s.getSentimentInt(),s.getSentimentName());
				
				lst.add(ps);
			}
		}
		return lst;
	}
//	
//	public List<Post_comment> AddCommenttopostbypostid(int id)
//	{
//		List<Post_comment>lst=new ArrayList<Post_comment>();
//		
//		for(Post_comment p:commentrepo.findAll())
//		{
//			if(p.getUser_post().getPost_id()==id)
//			{
//				
//				lst.add(p);
//			}
//		}
//	return lst;
//	
//	}
	
	public void Addpostcommentbypostid( int id)
	{
		
		for(Post_comment p:commentrepo.findAll())
		{
			if(p.getUser_post().getPost_id()==id)
			{
				User_posts up=new User_posts(p.getUser_post().getPost_id(), null, null, null, null, null, 0, 0);
				User_details ud=new User_details(p.getUser_details().getUser_id(), p.getUser_details().getFirst_name(), null, p.getUser_details().getLast_name(), null, null, null,"upload/"+ p.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
				Post_comment ps=new Post_comment(p.getComment_id(), up, p.getComment_date(), ud, p.getComment_message(),"upload/"+ p.getComment_photo(), p.getFlag());
				
				commentrepo.save(ps);
				
			}
		}
	}
}
