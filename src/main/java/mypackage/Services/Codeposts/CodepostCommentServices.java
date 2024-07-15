package mypackage.Services.Codeposts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Codes.Code_post_comment;
import mypackage.model.Codes.Code_posts;
import mypackage.model.user.User_details;
import mypackage.repositories.Codes.Icode_post_comment;

@Service
public class CodepostCommentServices {

	@Autowired
	Icode_post_comment codecommentrepo;
	
	public List<Code_post_comment>getallpostcomment()
	{
		List<Code_post_comment>lst=new ArrayList<Code_post_comment>();
		for(Code_post_comment c:codecommentrepo.findAll())
		{
			if(c.getFlag()==0)
			{
				User_details ud=new User_details(c.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
				
				Code_posts cp=new Code_posts(c.getCode_post().getPost_id(), null, null, null, null, null, null, 0, 0);
				
				Code_post_comment cpc=new Code_post_comment(c.getComment_id(), cp, c.getDate(), ud, c.getComment_messgae(), c.getComment_photo(), c.getFlag());
				
				lst.add(cpc);
			}
		}
		
		return lst;
	}
	
	public void Addcomment(Code_post_comment c)
	{
		codecommentrepo.save(c);
	}
	
	public void updatecomment(Code_post_comment c)
	{
		codecommentrepo.save(c);
	}
	
	public Code_post_comment getcommentbyid(int id)
	{
		Code_post_comment c=(Code_post_comment)codecommentrepo.findById(id).get();
		
		User_details ud=new User_details(c.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		
		Code_posts cp=new Code_posts(c.getCode_post().getPost_id(), null, null, null, null, null, null, 0, 0);
		
		Code_post_comment cpc=new Code_post_comment(c.getComment_id(), cp, c.getDate(), ud, c.getComment_messgae(), c.getComment_photo(), c.getFlag());
		
		return (cpc);
	}
	
	public void Deletecomment(int id)
	{
		Code_post_comment c=codecommentrepo.findById(id).get();
		c.setFlag(1);
		codecommentrepo.save(c);
	}
	
	public void Restorecomment(int id)
	{
		Code_post_comment c=codecommentrepo.findById(id).get();
		c.setFlag(0);
		codecommentrepo.save(c);
	}
	
	public List<Code_post_comment>getcodepostcommentbypostid(int id)
	{
		List<Code_post_comment>lst=new ArrayList<Code_post_comment>();
		for(Code_post_comment p:codecommentrepo.findAll())
		{
			if(p.getCode_post().getPost_id()==id)
			{
						User_details ud=new User_details(p.getUser_details().getUser_id(), p.getUser_details().getFirst_name(), null, p.getUser_details().getLast_name(), null, null, null,"upload/"+p.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
				
				Code_posts cp=new Code_posts(p.getCode_post().getPost_id(), null, null, null, null, null, null, 0, 0);
				
				Code_post_comment cpc=new Code_post_comment(p.getComment_id(), cp, p.getDate(), ud, p.getComment_messgae(), "upload/"+p.getComment_photo(), p.getFlag());
				lst.add(cpc);
			}
		}
		return lst;
		
	}
}
