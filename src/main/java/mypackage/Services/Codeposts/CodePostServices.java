package mypackage.Services.Codeposts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Codes.Code_posts;
import mypackage.model.mastertables.Topic_contents;
import mypackage.model.user.User_details;
import mypackage.repositories.Codes.Icode_posts;

@Service
public class CodePostServices {

	@Autowired
	Icode_posts codepost;
	
	public List<Code_posts>getallUnapprovedpost()
	{
		List<Code_posts>lst=new ArrayList<Code_posts>();
		for(Code_posts c:codepost.findAll())
		{
			if(c.getFlag()==0 && c.getIs_active()==0)
			{
				User_details ud=new User_details(c.getUser_details().getUser_id(), c.getUser_details().getFirst_name(), null, c.getUser_details().getLast_name(), null, null, null,"upload/"+c.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);

				Topic_contents t=new Topic_contents(c.getTopic_content().getContent_id(), c.getTopic_content().getContent_name(), 0, null);
				
				Code_posts cp=new Code_posts(c.getPost_id(), ud, c.getDate(), t, c.getQuestion(), c.getCode(), c.getDescription(), c.getIs_active(), c.getFlag());
				
				lst.add(cp);
			}
		}
		return lst;
	}
	
	public void Addcodepost(Code_posts c)
	{
		codepost.save(c);
	}
	
	public void Updatecodepost(Code_posts c)
	{
		codepost.save(c);
	}
	
	public Code_posts getcodepostbyid(int id)
	{
		Code_posts c=(Code_posts)codepost.findById(id).get();
		
		User_details ud=new User_details(c.getUser_details().getUser_id(), c.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

		Topic_contents t=new Topic_contents(c.getTopic_content().getContent_id(), c.getTopic_content().getContent_name(), 0, null);
		
		Code_posts cp=new Code_posts(c.getPost_id(), ud, c.getDate(), t, c.getQuestion(), c.getCode(), c.getDescription(), c.getIs_active(), c.getFlag());
		
		return(cp);
	}
	
	public void Deletecodepost(int id)
	{
		Code_posts c=codepost.findById(id).get();
		c.setFlag(1);
		codepost.save(c);
	}
	
	public void Restorecodepost(int id)
	{
		Code_posts c=codepost.findById(id).get();
		c.setFlag(1);
		codepost.save(c);
	}
	
	public List<Code_posts>getallaprovedpost()
	{
		List<Code_posts>lst=new ArrayList<Code_posts>();
		for(Code_posts c:codepost.findAll())
		{
			if(c.getFlag()==0 && c.getIs_active()==1)
			{
				User_details ud=new User_details(c.getUser_details().getUser_id(), c.getUser_details().getFirst_name(), null, c.getUser_details().getLast_name(), null, null, null,"upload/"+c.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);

				Topic_contents t=new Topic_contents(c.getTopic_content().getContent_id(), c.getTopic_content().getContent_name(), 0, null);
				
				Code_posts cp=new Code_posts(c.getPost_id(), ud, c.getDate(), t, c.getQuestion(), c.getCode(), c.getDescription(), c.getIs_active(), c.getFlag());
				
				lst.add(cp);
			}
		}
		return lst;
	}
	
	public void Approvecodepost(int id)
	{
		Code_posts p=codepost.findById(id).get();
		p.setIs_active(1);
		codepost.save(p);
	}
	
	public List<Code_posts>getallCodeposts()
	{
		List<Code_posts>lst=new ArrayList<Code_posts>();
		for(Code_posts c:codepost.findAll())
		{
			if(c.getFlag()==0)
			{
				User_details ud=new User_details(c.getUser_details().getUser_id(), c.getUser_details().getFirst_name(), null, c.getUser_details().getLast_name(), null, null, null,"upload/"+c.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);

				Topic_contents t=new Topic_contents(c.getTopic_content().getContent_id(), c.getTopic_content().getContent_name(), 0, null);
				
				Code_posts cp=new Code_posts(c.getPost_id(), ud, c.getDate(), t, c.getQuestion(), c.getCode(), c.getDescription(), c.getIs_active(), c.getFlag());
				
				lst.add(cp);
			}
		}
		return lst;
	}
	
}
