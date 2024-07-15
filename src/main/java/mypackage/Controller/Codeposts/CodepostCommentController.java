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

import mypackage.Controller.JobOpeningControllers.JobOpeningPostCommentRepliesController;
import mypackage.FileStorageService.FileStorageServices;
import mypackage.Services.Codeposts.CodepostCommentServices;
import mypackage.model.Codes.Code_post_comment;
import mypackage.model.Codes.Code_posts;
import mypackage.model.user.User_details;
import mypackage.payload.UploadFileResponse;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class CodepostCommentController {

	   private static final Logger logger = LoggerFactory.getLogger(CodepostCommentController.class);

	
	@Autowired
	CodepostCommentServices commentservice;
	
	@Autowired
	private FileStorageServices fileStorageService;
	
	@GetMapping("Getallcomments")
	public List<Code_post_comment>getall()
	{
		return commentservice.getallpostcomment();
	}
	
	@GetMapping("Getcommentbyid/{id}")
	public Code_post_comment getbyid(@PathVariable("id")int id)
	{
		return commentservice.getcommentbyid(id);
	}
	
	@PostMapping("Addcommentonpost")
	public Code_post_comment addcomment(@RequestParam("file")MultipartFile file,@RequestParam("date")String date,
			@RequestParam("user_id")int user_id,@RequestParam("post_id")int post_id,@RequestParam("comment_messgae")String comment_messgae)
	{
		String fileName=fileStorageService.storeFile(file);
		
		User_details ud=new User_details(user_id,null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

		Code_posts p=new Code_posts(post_id, null, null, null, null, null, null, 0, 0);
		
		Code_post_comment cd=new Code_post_comment(0, p, date, ud, comment_messgae, fileName, 0);
		
		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();
	        UploadFileResponse r= new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
	        
	        commentservice.Addcomment(cd);
	        return cd;
	}
	
	
	
//	
//	@PostMapping("Addcommentonpost")
//	public String Addcomment(@RequestBody Code_post_comment c)
//	{
//		commentservice.Addcomment(c);
//		return "Comment Added Sucessfully";
//	}
	
	@PutMapping("Updatecommentonpost")
	public String update(@RequestBody Code_post_comment c)
	{
		commentservice.updatecomment(c);
		return "Comment Updated Sucessfully";
	}
	
	@DeleteMapping("Deletecommentonpost/{id}")
	public String delete(@PathVariable("id")int id)
	{
		commentservice.Deletecomment(id);
		return "Comment Deleted Sucessfully";
	}
	@DeleteMapping("Restorecommentonpost/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		commentservice.Restorecomment(id);
		return "Comment Restored Sucessfully";
	}
	@GetMapping("GetCodepostcommentsbypostid/{id}")
	public List<Code_post_comment>getbypostcommentbypostid(@PathVariable("id")int id)
	{
		return commentservice.getcodepostcommentbypostid(id);
	}
}
