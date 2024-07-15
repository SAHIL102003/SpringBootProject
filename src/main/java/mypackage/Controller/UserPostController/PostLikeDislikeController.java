package mypackage.Controller.UserPostController;

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

import mypackage.Services.Userpost.PostLikeDislikeServices;
import mypackage.model.Userposts.Post_like_Dislike;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class PostLikeDislikeController {

	@Autowired
	PostLikeDislikeServices likeservice;
	
	@GetMapping("GetPostLikedislikes")
	public List<Post_like_Dislike>getall()
	{
		return likeservice.getalllikedislikes();
	}
	
	@GetMapping("GetPostLikedislikesbyid/{id}")
	public Post_like_Dislike getbyid(@PathVariable("id")int id)
	{
		return likeservice.getlikedislikebyid(id);
	}
	
	@PostMapping("AddPostLikedislikes")
	public String Addpostlike(@RequestBody Post_like_Dislike p)
	{
		likeservice.AddLikedisliketopost(p);
		return "Post Reaction Added Sucessfully";
	}
	@PutMapping("UpdatePostLikedislikes")
	public String update(@RequestBody Post_like_Dislike p)
	{
		likeservice.Updatelikedislike(p);
		return "Post Reaction Updated Sucessfully";
	}
	@DeleteMapping("DeletePostLikedislikes/{id}")
	public String delete(@PathVariable("id")int id)
	{
		likeservice.Deletelikedislike(id);
		Post_like_Dislike p=new Post_like_Dislike(id, null, "", null, 0, 0);
		return "Reaction Deleted Sucessfully";
	}
	
	@DeleteMapping("RestorePostLikedislikes/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		likeservice.Restorelikedislike(id);
		Post_like_Dislike p=new Post_like_Dislike(id, null, "", null, 0, 0);
		return "Reaction Restored Sucessfully";
	}
	
	@GetMapping("GetLikesbypostid/{id}")
	public List<Post_like_Dislike>getbypostid(@PathVariable("id")int id)
	{
		return likeservice.Getlikebypostid(id);
	}
	
}
