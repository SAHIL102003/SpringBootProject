package mypackage.Controller.Codeposts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mypackage.FileStorageService.FileStorageServices;
import mypackage.Services.Codeposts.CodepostCommentRepliesServices;
import mypackage.model.Codes.Code_post_comment;
import mypackage.model.Codes.Code_post_comment_replies;
import mypackage.model.user.User_details;
import mypackage.payload.UploadFileResponse;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class CodepostCommentRepliesController {
	
	   private static final Logger logger = LoggerFactory.getLogger(CodepostCommentRepliesController.class);


	@Autowired
	CodepostCommentRepliesServices replyservice;
	
	@Autowired
	FileStorageServices filestorageservice;
	
	@GetMapping("Getallrepliesoncomment")
	public List<Code_post_comment_replies>getall()
	{
		return replyservice.getallreplies();
	}
	
	@GetMapping("Getreplybyid/{id}")
	public Code_post_comment_replies getbyid(@PathVariable("id")int id)
	{
		return replyservice.getcommentreplybyid(id);
	}
	
	@PostMapping("Addreplyoncomment")
	public Code_post_comment_replies addreply(@RequestParam("file")MultipartFile file,
			@RequestParam("reply_message")String reply_message,
			@RequestParam("user_id")int user_id,@RequestParam("comment_id")int comment_id,@RequestParam("reply_date")String reply_date)
	{
		String fileName=filestorageservice.storeFile(file);
	
		User_details ud=new User_details(user_id,null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

		Code_post_comment c=new Code_post_comment(comment_id, null, null, null, null, null, 0);
		
		Code_post_comment_replies cr=new Code_post_comment_replies(0, c, reply_date, ud, reply_message, fileName, 0);
		
		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();
	        UploadFileResponse r= new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
	    
	        replyservice.Addcommentreply(cr);
	        	return cr;
	}
	
	
	
//	@PostMapping("Addreplyoncomment")
//	public String Add(@RequestBody Code_post_comment_replies c)
//	{
//		replyservice.Addcommentreply(c);
//		return "Reply On Comment Added Sucessfully";
//	}
	
	@PutMapping("Updatereplyoncomment")
	public String update(@RequestBody Code_post_comment_replies c)
	{
		replyservice.Updatecommentreply(c);
		return "Reply On Comment Updated Sucessfully";
	}
	
	@DeleteMapping("Deletereply/{id}")
	public String delete(@PathVariable("id")int id)
	{
		replyservice.DeleteCommentreply(id);
		Code_post_comment_replies c=new Code_post_comment_replies(id, null, "", null, "", "", 0);
		return "Reply Deleted Sucessfully";
	}
	@DeleteMapping("Restorereply/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		replyservice.RestoreCommentreply(id);
		Code_post_comment_replies c=new Code_post_comment_replies(id, null, "", null, "", "", 0);
		return "Reply Restored Sucessfully";
	}
}
