package mypackage.Controller.JobOpeningControllers;

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
import mypackage.Services.JobopeningPost.JobOpeningPostCommentRepliesServices;
import mypackage.model.Job_Opening.Jobopening_post_comment;
import mypackage.model.Job_Opening.Jobopening_post_comment_repies;
import mypackage.model.user.User_details;
import mypackage.payload.UploadFileResponse;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class JobOpeningPostCommentRepliesController {
	
	   private static final Logger logger = LoggerFactory.getLogger(JobOpeningPostCommentRepliesController.class);


	@Autowired
	JobOpeningPostCommentRepliesServices replyservice;
	
	@Autowired
	private FileStorageServices fileStorageService;
	
	@GetMapping("Getallrepliesonjobopeningpostcomment")
	public List<Jobopening_post_comment_repies>getall()
	{
		return replyservice.getallreplies();
	}
	
	@GetMapping("Getreplydetailsbyid/{id}")
	public Jobopening_post_comment_repies getbyid(@PathVariable("id")int id)
	{
		return replyservice.getreplybyid(id);
	}
	
	
	@PostMapping("Addreplyonjobopeningcomment")
	public Jobopening_post_comment_repies addreply(@RequestParam("file")MultipartFile file,@RequestParam("reply_date")String reply_date,@RequestParam("reply_message")String reply_message,
			@RequestParam("user_id")int user_id,@RequestParam("comment_id")int comment_id)
	{
        String fileName = fileStorageService.storeFile(file);

		User_details ud=new User_details(user_id,null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		
		Jobopening_post_comment jo=new Jobopening_post_comment(comment_id, null, null, null, null, null, 0);
		
		Jobopening_post_comment_repies jr=new Jobopening_post_comment_repies(0, jo, reply_date, ud, reply_message, fileName, 0);
		
		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();
	        UploadFileResponse r= new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
	        
	        replyservice.Addreplytocomment(jr);
	        return jr;
	}
	  
	  @GetMapping("/downloadJobOpeningPostCommentReplyFile/{fileName:.+}")
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
	
	
	
//	@PostMapping("Addreplyonjobopeningcomment")
//	public String add(@RequestBody Jobopening_post_comment_repies j)
//	{
//		replyservice.Addreplytocomment(j);
//		return "Reply Added Sucessfully";
//		
//	}
//	
	@PutMapping("Updatereplyonjobopeningcomment")
	public String Update(@RequestBody Jobopening_post_comment_repies j)
	{
		replyservice.Updatereplytocomment(j);
		return "Reply Updated Sucessfully";	
	}
	
	@DeleteMapping("Deletereplyonpost/{id}")
	public String delete(@PathVariable("id")int id)
	{
		replyservice.deletereply(id);
		Jobopening_post_comment_repies r=new Jobopening_post_comment_repies(id, null, "", null, "", "", 0);
		return "Reply Deleted Sucessfully";
	}
	
	@DeleteMapping("Restorereplyonpost/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		replyservice.Restorereply(id);
		Jobopening_post_comment_repies r=new Jobopening_post_comment_repies(id, null, "", null, "", "", 0);
		return "Reply Restored Sucessfully";
	}
}
