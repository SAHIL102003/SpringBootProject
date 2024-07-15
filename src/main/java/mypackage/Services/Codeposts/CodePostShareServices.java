package mypackage.Services.Codeposts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Codes.Code_post_comment;
import mypackage.model.Codes.Code_post_share;
import mypackage.model.Codes.Code_posts;
import mypackage.model.user.User_details;
import mypackage.repositories.Codes.Icode_post_share;

@Service
public class CodePostShareServices {

	@Autowired
	Icode_post_share postshare;
	
	public List<Code_post_share>getallshares()
	{
		List<Code_post_share>lst=new ArrayList<Code_post_share>();
		for(Code_post_share c:postshare.findAll())
		{
			if(c.getFlag()==0)
			{
				User_details ud=new User_details(c.getUser_details().getUser_id(), c.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
				Code_posts cp=new Code_posts(c.getCode_post().getPost_id(), null, null, null, null, null, null, 0, 0);

				Code_post_share cs=new Code_post_share(c.getShare_id(), cp, c.getShare_date(), ud, c.getFlag());
				lst.add(cs);
			}
		}
		return lst;
	}
	
	public void Addpostshare(Code_post_share c)
	{
		postshare.save(c);
	}
	
	public void Updatepostshare(Code_post_share c)
	{
		postshare.save(c);
	}
	
	public Code_post_share getsharebyid(int id)
	{
		Code_post_share c=(Code_post_share)postshare.findById(id).get();
		
		User_details ud=new User_details(c.getUser_details().getUser_id(), c.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		Code_posts cp=new Code_posts(c.getCode_post().getPost_id(), null, null, null, null, null, null, 0, 0);

		Code_post_share cs=new Code_post_share(c.getShare_id(), cp, c.getShare_date(), ud, c.getFlag());
		
		return(cs);
		
	}
	
	public void Deleteshare(int id)
	{
		Code_post_share c=postshare.findById(id).get();
		c.setFlag(1);
		postshare.save(c);
	}
	
	public void Restoreshare(int id)
	{
		Code_post_share c=postshare.findById(id).get();
		c.setFlag(0);
		postshare.save(c);
	}
}
