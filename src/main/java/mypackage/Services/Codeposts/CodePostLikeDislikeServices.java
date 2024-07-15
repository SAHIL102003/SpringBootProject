package mypackage.Services.Codeposts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Codes.Code_post_like_dislike;
import mypackage.model.Codes.Code_posts;
import mypackage.model.user.User_details;
import mypackage.repositories.Codes.Icode_post_like_dislike;

@Service
public class CodePostLikeDislikeServices {

	@Autowired
	Icode_post_like_dislike likerepo;
	
	public List<Code_post_like_dislike>getalllikedislike()
	{
		List<Code_post_like_dislike>lst=new ArrayList<Code_post_like_dislike>();
				for(Code_post_like_dislike c:likerepo.findAll())
				{
					if(c.getFlag()==0)
					{
						User_details ud=new User_details(c.getUser_details().getUser_id(), c.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
						Code_posts cp=new Code_posts(c.getCode_post().getPost_id(), null, null, null, null, null, null, 0, 0);
						Code_post_like_dislike cl=new Code_post_like_dislike(c.getLike_dislike_id(), cp, c.getLike_dislike_date(), ud, c.getIs_like(), c.getFlag());
						
						lst.add(cl);
					}
				}
		return lst;
	}
	
	public void Addlikedislike(Code_post_like_dislike c)
	{
		likerepo.save(c);
	}
	
	public void Updatelikedislike(Code_post_like_dislike c)
	{
		likerepo.save(c);
	}
	
	public Code_post_like_dislike getlikedislikdebyid(int id)
	{
		Code_post_like_dislike c=(Code_post_like_dislike)likerepo.findById(id).get();
		
		User_details ud=new User_details(c.getUser_details().getUser_id(), c.getUser_details().getFirst_name(), null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		Code_posts cp=new Code_posts(c.getCode_post().getPost_id(), null, null, null, null, null, null, 0, 0);
		Code_post_like_dislike cl=new Code_post_like_dislike(c.getLike_dislike_id(), cp, c.getLike_dislike_date(), ud, c.getIs_like(), c.getFlag());
		
		return(cl);
		
	}
	
	public void Deletelikesanddislikes(int id)
	{
		Code_post_like_dislike c=likerepo.findById(id).get();
		c.setFlag(1);
		likerepo.save(c);
	}
	
	public void Restorelikesanddislikes(int id)
	{
		Code_post_like_dislike c=likerepo.findById(id).get();
		c.setFlag(0);
		likerepo.save(c);
	}
	
	
}
