package mypackage.Controller.UserPostController;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import org.slf4j.Logger;
import mypackage.FileStorageService.FileStorageServices;
import mypackage.Services.Userpost.UserPostServices;
import mypackage.model.Userposts.Post_comment;
import mypackage.model.Userposts.User_post_Model;
import mypackage.model.Userposts.User_posts;
import mypackage.model.user.User_details;
import mypackage.payload.UploadFileResponse;
@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class UserPostController {

	   private static final Logger logger = LoggerFactory.getLogger(UserPostController.class);
	@Autowired
	UserPostServices postservice;
	@Autowired
	private FileStorageServices fileStorageService;
	//	@GetMapping("Getalluserposts")
//	public List<User_posts>getall()
//	{
//		return postservice.getallpost();
//	}	
	
	@GetMapping("GetuserpostByPostid/{id}")
	public User_posts getbyid(@PathVariable("id")int id)
	{
		return postservice.Getuserpostbyid(id);
	}
	@PostMapping("AddUserpost")
	public String Addpost(@RequestParam("file")MultipartFile file,@RequestParam("user_id")int user_id,@RequestParam("post_description")String post_description,
			@RequestParam("post_date")String post_date,@RequestParam("post_title")String post_title)
	{
        String fileName = fileStorageService.storeFile(file);
        User_details ud=new User_details(user_id, null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		User_posts u=new User_posts(0, ud, post_date, post_title, post_description, fileName, 0, 0);
				 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			                .path("/downloadFile/")
			                .path(fileName)
			                .toUriString();
			        UploadFileResponse r= new UploadFileResponse(fileName, fileDownloadUri,
			                file.getContentType(), file.getSize());
			      
			     String msg=   postservice.Addpost(u);
			    //    return u;
			     return msg;
	}
//	@PostMapping("AddUserpost")
//	public String Addpost(@RequestBody User_posts u)
//	{
//		postservice.Addpost(u);
//		return "User Post Added Sucessfully";
//	}
	 @GetMapping("/downloadUserpostPhoto/{fileName:.+}")
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
	
	
	@PutMapping("UpdateUserpost")
	public String Updatepost(@RequestBody User_posts u)
	{
		postservice.updatepost(u);
		return "User Post Updated Sucessfully";
	}
	@DeleteMapping("DeleteUserpost/{id}")
	public String delete(@PathVariable("id")int id)
	{
		postservice.Deleteuserpost(id);
		User_posts u=new User_posts(id, null, "", "", "", "", 0, 0);
		return "User Post Deleted Sucessfully";
	}
	
	@DeleteMapping("RestoreUserpost/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		postservice.Restoreuserpost(id);
		User_posts u=new User_posts(id, null, "", "", "", "", 0, 0);
		return "User Post Restored Sucessfully";
	}
	
	@GetMapping("GetallPostbyUserid/{id}")
	public List<User_posts>getallpostbyuserid(@PathVariable("id")int id)
	{
		return postservice.Getpostsbyuserid(id);
	}
	
	@PostMapping("api/postapprovel/{id}")
	public String approve(@PathVariable("id")int id)
	{
		postservice.Approvepost(id);
		return "Post Approved Sucessfully";
	}
	
	@GetMapping("api/getaprovelpost")
	public List<User_posts>getapproval()
	{
		return postservice.getpostapprovel();																								
	}
	@GetMapping("api/getallPosts")
	public List<User_posts>getallPosts()
	{
		return postservice.GetAllPosts();
	}	
	@GetMapping("api/Getalluserposts")
	public List<User_posts>getallUserposts()
	{
		return postservice.GetAllActivatedPostsWithComments();
	}	
	@GetMapping("api/getallpostswithcomment")
	public List<User_posts>getallUserpostsWithComments()
	{
		return postservice.GetAllActivatedPostsWithComments();
	}	
	@GetMapping("api/getallpostswithsentimentcomment")
	public List<User_post_Model>getallUserpostsWithSentimentComments()
	{
//		return postservice.GetAllPostsWithSentimentComments();
		return postservice.GetAllPostsWithSentimentComments();

	}	
	@GetMapping("api/getallapprovedpostswithsentimentcomment")
	public List<User_post_Model>getallUserpostsdWithSentimentComments()
	{
//		return postservice.GetAllPostsWithSentimentComments();
		return postservice.GetAllActivatedPostsWithSentimentComments();

	}	

	
	@GetMapping("api/getpostWisesentimentcomment/{id}")
	public  User_post_Model getPostWiseSentimntComments(@PathVariable("id")int id)
	{
		return postservice.GetPostsWiseSentimentComments(id);
	}	
	@PutMapping("api/approvepost/{id}")
	public String ApprovePost(@PathVariable("id")int id)
	{
		System.out.println(id);
		postservice.Approvepost(id);
		return "Post Approved Sucessfully";
	}
	@PutMapping("api/rejectpost/{id}")
	public String DeActivateUser(@PathVariable("id")int id)
	{
		postservice.Rejectpost(id);
		return "Post Rejected Sucessfully";
	}
	
	@GetMapping("api/textpost")
	public String GetImageText() {
		return postservice.GetImageText("C:\\Users\\CIIT\\OneDrive\\Desktop\\images\\dd.jpg");
	}
}
