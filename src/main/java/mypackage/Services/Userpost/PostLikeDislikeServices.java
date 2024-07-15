package mypackage.Services.Userpost;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Userposts.Post_like_Dislike;
import mypackage.model.Userposts.User_posts;
import mypackage.model.user.User_details;
import mypackage.repositories.Userposts.PostlikeDislikeRepositories;

@Service
public class PostLikeDislikeServices {

	@Autowired
	PostlikeDislikeRepositories likedisrepo;
	
	public List<Post_like_Dislike>getalllikedislikes()
	{
		List<Post_like_Dislike>lst=new ArrayList<Post_like_Dislike>();
		for(Post_like_Dislike p:likedisrepo.findAll())
		{
			if(p.getFlag()==0 )
			
		{
			User_posts up=new User_posts(p.getUser_post().getPost_id(), null, null, null, null, null, 0, 0);
			
			User_details u=new User_details(p.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
			Post_like_Dislike pl=new Post_like_Dislike(p.getLike_dislike_id(), up, p.getLike_dislike_date(), u, p.getIs_like(), 0);
			lst.add(pl);
		}
		
			
		}
		return lst;
	}
	
	
	
	public void AddLikedisliketopost(Post_like_Dislike p)
	{
		p.setIs_like(1);
		likedisrepo.save(p);
	}
	
	public void Updatelikedislike(Post_like_Dislike p)
	{
		likedisrepo.save(p);
	}
	
	public Post_like_Dislike getlikedislikebyid(int id)
	{
		Post_like_Dislike p=(Post_like_Dislike)likedisrepo.findById(id).get();
		
		User_posts up=new User_posts(p.getUser_post().getPost_id(), null, null, null, null, null, 0, 0);
		
		User_details u=new User_details(p.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		Post_like_Dislike pl=new Post_like_Dislike(p.getLike_dislike_id(), up, p.getLike_dislike_date(), u,  p.getIs_like(), 0);
		
		return(pl);
		
	}
	
	public void Deletelikedislike(int id)
	{
		Post_like_Dislike p=likedisrepo.findById(id).get();
		p.setFlag(1);
		likedisrepo.save(p);
	}
	
	public void Restorelikedislike(int id)
	{
		Post_like_Dislike p=likedisrepo.findById(id).get();
		p.setFlag(0);
		likedisrepo.save(p);
	}
	
	public List<Post_like_Dislike> Getlikebypostid(int id)
	{
		List<Post_like_Dislike>lst=new ArrayList<Post_like_Dislike>();
		
		for(Post_like_Dislike p:likedisrepo.findAll())
		{
			if(p.getUser_post().getPost_id()==id)
			{
				User_posts up=new User_posts(p.getUser_post().getPost_id(), null, null, null, null, null, 0, 0);
				
				User_details u=new User_details(p.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
				Post_like_Dislike pl=new Post_like_Dislike(p.getLike_dislike_id(), up, p.getLike_dislike_date(), u, p.getIs_like(), 0);
				
				lst.add(pl);
			}
		}
		return lst;
	}
	

}
