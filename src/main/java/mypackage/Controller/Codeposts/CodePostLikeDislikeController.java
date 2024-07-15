package mypackage.Controller.Codeposts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mypackage.Services.Codeposts.CodePostLikeDislikeServices;
import mypackage.model.Codes.Code_post_like_dislike;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class CodePostLikeDislikeController {

	@Autowired
	CodePostLikeDislikeServices likeservices;
	
	@GetMapping("GetalllikeDislikes")
	public List<Code_post_like_dislike>getall()
	{
		return likeservices.getalllikedislike();
	}
	
	@GetMapping("GetlikeDislikebyid/{id}")
	public Code_post_like_dislike getbyid(@PathVariable("id")int id)
	{
		return likeservices.getlikedislikdebyid(id);
	}
	
	@PostMapping("Addlikedislikes")
	public String Addlikes(@RequestBody Code_post_like_dislike c)
	{
		likeservices.Addlikedislike(c);
		return "Reaction Added Sucessfully";
	}
	
	@PutMapping("Updatelikedislikes")
	public String Update(@RequestBody Code_post_like_dislike c)
	{
		likeservices.Updatelikedislike(c);
		return "Reaction Updated Successfully";
	}
	
	@DeleteMapping("DeleteLikedislike/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		likeservices.Deletelikesanddislikes(id);
		Code_post_like_dislike c=new Code_post_like_dislike(id, null, "", null, 0, 0);
		return "Reaction Deleted Successfully";
	}
	
	@DeleteMapping("RestoreLikedislike/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		likeservices.Restorelikesanddislikes(id);
		Code_post_like_dislike c=new Code_post_like_dislike(id, null, "", null, 0, 0);
		return "Reaction Restored Successfully";
	}
	

}
