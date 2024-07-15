package mypackage.Controller.UserPostController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import mypackage.Services.Userpost.PostCommentRepliesServices;
import mypackage.model.Userposts.Post_comment;
import mypackage.model.Userposts.Post_comment_reply;
import mypackage.model.user.User_details;
import mypackage.payload.UploadFileResponse;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class PostCommentRepliesController {
	
	   private static final Logger logger = LoggerFactory.getLogger(PostCommentRepliesController.class);


	@Autowired
	PostCommentRepliesServices replyservice;
	
	@Autowired
	private FileStorageServices fileStorageService;
	
	@GetMapping("GetallPostcommentReply")
	public List<Post_comment_reply>getall()
	{
		return replyservice.getallpostcommentreplies();
	}
	
	@GetMapping("Getpostcommentreplybyid/{id}")
	public Post_comment_reply getbyid(@PathVariable("id")int id)
	{
		return replyservice.getcommentrepliesbyid(id);
	}
	
	@PostMapping("AddPostCommentReply")
	public Post_comment_reply Addreply(@RequestParam("file")MultipartFile file, @RequestParam("reply_date")String reply_date,
			@RequestParam("reply_message")String reply_message,@RequestParam("user_id")int user_id,@RequestParam("comment_id")int comment_id)
	{
		 String fileName = fileStorageService.storeFile(file);
		
		User_details ud=new User_details(user_id, null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		
		Post_comment p=new Post_comment(comment_id, null, null, null, null, null, 0);
		
		Post_comment_reply pc=new Post_comment_reply(0, p, reply_date, ud, reply_message, fileName, 0);
		
		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();
	        UploadFileResponse r= new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
		
		replyservice.Addpostcommentreplies(pc);
		return pc;
	}
	
	 @GetMapping("/downloadUserpostCommentReplyPhoto/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	        	
	        	logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if(contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
	    }
		
	
	
	
	@PutMapping("UpdatepostcommentReply")
	public String Update(@RequestBody Post_comment_reply p)
	{
		replyservice.Updatepostcommentreplies(p);
		return "CommentReply Updated Sucessfully";
	}
	
	@DeleteMapping("DeletepostcommentReply/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		replyservice.Deletepostcommentreply(id);
		Post_comment_reply p=new Post_comment_reply(id, null, "", null, "", "", 0);
		return "Comment Reply Deleted Sucessfully";
	}
	
	@DeleteMapping("RestorepostcommentReply/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		replyservice.Restorepostcommentreply(id);
		Post_comment_reply p=new Post_comment_reply(id, null, "", null, "", "", 0);
		return "Comment Reply Restored Sucessfully";
	}
	
	@GetMapping("Getrepliesbycommentid/{id}")
	public List<Post_comment_reply>getrepliesbycommentid(@PathVariable("id")int id)
	{
		return replyservice.getallreplybypostid(id);
	}
	
}
