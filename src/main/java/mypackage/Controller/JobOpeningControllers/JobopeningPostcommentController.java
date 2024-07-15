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
import mypackage.Services.JobopeningPost.JobOpeningPostCommentServices;
import mypackage.model.Job_Opening.Job_opening;
import mypackage.model.Job_Opening.Jobopening_post_comment;
import mypackage.model.user.User_details;
import mypackage.payload.UploadFileResponse;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class JobopeningPostcommentController {
	
	   private static final Logger logger = LoggerFactory.getLogger(JobopeningPostcommentController.class);


	@Autowired
	JobOpeningPostCommentServices commentservice;
	
	@Autowired
	private FileStorageServices fileStorageService;
	
	@GetMapping("Getalljobopeningpostcomments")
	public List<Jobopening_post_comment>getall()
	{
		return commentservice.getallcomments();
	}
	
	@GetMapping("Getcommentbyidonjobopeningpost/{id}")
	public Jobopening_post_comment getbyid(@PathVariable("id")int id)
	{
		return commentservice.getcommentbyid(id);
	}
	
	@PostMapping("Addcommentonjobopeningpost")
	public Jobopening_post_comment Addcomment(@RequestParam("file")MultipartFile file,@RequestParam("comment_date")String comment_date,@RequestParam("comment_message")String comment_message,
			@RequestParam("user_id")int user_id,@RequestParam("opening_id")int opening_id)
	{
        String fileName = fileStorageService.storeFile(file);

		
		User_details ud=new User_details(user_id,null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

		Job_opening jo=new Job_opening(opening_id, null, null, null, null, null, null, 0, 0);
		
		Jobopening_post_comment jop=new Jobopening_post_comment(0, jo, comment_date, ud, comment_message, fileName, 0);
		
		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(fileName)
	                .toUriString();
	        UploadFileResponse r= new UploadFileResponse(fileName, fileDownloadUri,
	                file.getContentType(), file.getSize());
	        
	        commentservice.Addcommentonpost(jop);
	        return jop;
	}
	  
	  @GetMapping("/downloadJobOpeningPostCommentFile/{fileName:.+}")
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

//	@PostMapping("Addcommentonjobopeningpost")
//	public String Add(@RequestBody Jobopening_post_comment j)
//	{
//		commentservice.Addcommentonpost(j);
//		return "Comment On Post Added Sucessfully";
//	}
	@PutMapping("Updatecommentonjoboprningpost")
	public String Update(@RequestBody Jobopening_post_comment j)
	{
		commentservice.UpdateCommentonpost(j);
		return "Comment on Post Updated Successfully";
	}
	
	@DeleteMapping("Deletecommentonjobopeningpost/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		commentservice.Deletecommentonpost(id);
		return "Comment Deleted Sucessfully";
	}
	
	@DeleteMapping("Restorecommentonjobopeningpost/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		commentservice.Restorecommentonpost(id);
		return "Comment Restored Sucessfully";
	}
	
}
