package mypackage.Services.Userpost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Userposts.Post_Shares;
import mypackage.model.Userposts.User_posts;
import mypackage.model.user.User_details;
import mypackage.repositories.Userposts.PostShareRepositories;

@Service
public class PostSharesServices {

	
	@Autowired
	PostShareRepositories shareepo;
	
	public List<Post_Shares>getallshares()
	{
		List<Post_Shares>lst=new ArrayList<Post_Shares>();
		
		for(Post_Shares p:shareepo.findAll())
		{
			if(p.getFlag()==0)
			{
				User_posts up=new User_posts(p.getUser_post().getPost_id(), null, null, null, null, null, 0, 0);
				
				User_details u=new User_details(p.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
				
				Post_Shares ps=new Post_Shares(p.getShare_id(), up, p.getShare_date(), u, p.getFlag());
				
				lst.add(ps);
			}
		}
		
		return lst;
	}
	
	public void Addshare(Post_Shares p)
	{
		shareepo.save(p);
	}
	
	public void Updateshare(Post_Shares p)
	{
		shareepo.save(p);
	}
	
	public Post_Shares Getshareid(int id)
	{
		Post_Shares p=(Post_Shares)shareepo.findById(id).get();
		
		User_posts up=new User_posts(p.getUser_post().getPost_id(), null, null, null, null, null, 0, 0);
		
		User_details u=new User_details(p.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		
		Post_Shares ps=new Post_Shares(p.getShare_id(), up, p.getShare_date(), u, p.getFlag());
	
		return(ps);
	}
	
	public void Deletesharepost(int id)
	{
		Post_Shares p=shareepo.findById(id).get();
		p.setFlag(1);
		shareepo.save(p);
	}
	
	public void Restoresharepost(int id)
	{
		Post_Shares p=shareepo.findById(id).get();
		p.setFlag(0);
		shareepo.save(p);
	}
}
