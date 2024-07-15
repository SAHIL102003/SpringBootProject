package mypackage.Controller.UserPostController;
import java.io.File;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.asprise.ocr.Ocr;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import mypackage.*;
//import mypackage.ExtraServices;
import mypackage.FileStorageService.FileStorageServices;
import mypackage.Services.Userpost.PostCommentServices;
import mypackage.model.Userposts.Post_Comment_Model;
import mypackage.model.Userposts.Post_comment;
import mypackage.model.Userposts.User_posts;
import mypackage.model.user.User_details;
import mypackage.payload.UploadFileResponse;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")
public class PostCommentController{

	private static final Logger logger = LoggerFactory.getLogger(PostCommentController.class);

	@Autowired
	PostCommentServices commentservice;
	
	@Autowired
	private FileStorageServices fileStorageService;
	
	@GetMapping("GetallComments")
	public List<Post_comment>getall()
	{
		return commentservice.getallpostcomment();
	}
	
	@GetMapping("GetCommentbyCommentid/{id}")
	public Post_comment getbyid(@PathVariable("id")int id)
	{
		return commentservice.Getpostcommentbyid(id);
	}
	
	
	@PostMapping("AddCommentonPost")
	public Post_comment Addcomment(@RequestParam("user_id")int user_id,@RequestParam("post_id")int post_id,@RequestParam("comment_date")String comment_date,@RequestParam("comment_message")String comment_message)
	{
      //  String fileName = fileStorageService.storeFile(file);
 
    //    System.out.println(fileName+" "+user_id+" "+comment_date+" "+comment_message);
	// ExtraServices es=new ExtraServices();
		
		  StanfordCoreNLP pipeline = new StanfordCoreNLP("application.properties");

	        // Sample text for sentiment analysis
	        String text =comment_message;

	        // Perform sentiment analysis
	      //  String sentiment = es.getSentiment(text, pipeline);
		
	        String sentiment = getSentiment(text, pipeline);
			User_details ud=new User_details(user_id, null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
		
		User_posts up=new User_posts(post_id, null, null, null, null, null, 0, 0);
		
		Post_comment p=new Post_comment(0, up, comment_date, ud, comment_message, sentiment, 0);
		
//		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//	                .path("/downloadFile/")
//	                .path(fileName)
//	                .toUriString();
// 	        UploadFileResponse r= new UploadFileResponse(fileName, fileDownloadUri,
//                file.getContentType(), file.getSize());
		
		commentservice.AddCommenttopost(p);
		return p;
	}
		public String GetTextFromImage() {
		Ocr.setUp(); // one time setup
		Ocr ocr = new Ocr(); // create a new OCR engine
		ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
		String s = ocr.recognize(new File[] {new File("C:\\Users\\CIIT\\OneDrive\\Desktop\\images\\dd.jpg")},
		  Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT); // PLAINTEXT | XML | PDF | RTF
		System.out.println("Result: " + s);
		ocr.stopEngine();
		return s;
	}
	 public   String getSentiment(String text, StanfordCoreNLP pipeline) {
	        // Create an Annotation object with the input text
	        Annotation annotation = new Annotation(text);

	        // Run all the NLP annotators on the text
	        pipeline.annotate(annotation);

	        // Extract the sentiment from the annotation
	        CoreMap sentence = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);
	        String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);

	        return sentiment;
	    }
	
//	@PostMapping("AddCommenttopostbypostid/{id}")
//	public Post_comment Addcommentbypostid(@RequestParam("file")MultipartFile file,@RequestParam("user_id")int user_id,@RequestParam("post_id")int post_id,
//		@RequestParam("comment_date")String comment_date,@RequestParam("comment_message")String comment_message,@PathVariable("id")int id)
//	{
//        String fileName = fileStorageService.storeFile(file);
//
//		
//		User_details ud=new User_details(user_id, null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
//		
//		User_posts up=new User_posts(post_id, null, null, null, null, null, 0, 0);
//		
//		Post_comment p=new Post_comment(0, up, comment_date, ud, comment_message, fileName, 0);
//		
//		 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//	                .path("/downloadFile/")
//	                .path(fileName)
//	                .toUriString();
//	        UploadFileResponse r= new UploadFileResponse(fileName, fileDownloadUri,
//	                file.getContentType(), file.getSize());
//		
//		commentservice.Addpostcommentbypostid(id);
//		return p;
//	}
	
//	 @GetMapping("/downloadUserpostCommentPhoto/{fileName:.+}")
//	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
//	        // Load file as Resource
//	        Resource resource = fileStorageService.loadFileAsResource(fileName);
//
//	        // Try to determine file's content type
//	        String contentType = null;
//	        try {
//	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//	        } catch (IOException ex) {
//	        	
//	        	logger.info("Could not determine file type.");
//	        }
//
//	        // Fallback to the default content type if type could not be determined
//	        if(contentType == null) {
//	            contentType = "application/octet-stream";
//	        }
//
//	        return ResponseEntity.ok()
//	                .contentType(MediaType.parseMediaType(contentType))
//	                .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//	                .body(resource);
//	    }
//		

	@PutMapping("UpdateCommentpost")
	public String update(@RequestBody Post_comment p)
	{
		commentservice.UpdateCommentpost(p);
		return "Comment on Post Updated Sucessfully";
	}
	
	@DeleteMapping("DeletePostComment/{id}")
	public String Delete(@PathVariable("id")int id)
	{
		commentservice.Deletepostcomment(id);
		Post_comment p=new Post_comment(id, null, "", null, "", "", 0);
		return "Comment On Post Deleted Sucessfully";
	}
	
	@DeleteMapping("RestorePostComment/{id}")
	public String Restore(@PathVariable("id")int id)
	{
		commentservice.Restorepostcomment(id);
		return "Comment On Post Restored Sucessfully";
	}
//	@GetMapping("PostWiseComments/{id}")
//	public List<Post_comment>getpostwisweid(@PathVariable("id")int id)
//	{
//		return Post_comment p=
//		
////		return commentservice.getallpostcomment();
//	}
//	@GetMapping("Postwisecomments/{id}")
//	public List<Post_comment> getcom(@PathVariable("id")int id)
//	{
//		return commentservice.Getpostwisecomment(id);
//	}
	@GetMapping("GetallCommentsbypostid/{id}")
	public List<Post_comment>getallpost(@PathVariable("id")int id)
	{
		return commentservice.getallpostcommentbypostid(id);
	}
	
	@GetMapping("GetallCommentsSentimentbypostid/{id}")
	public List<Post_Comment_Model>getallCommentsWithSentiment(@PathVariable("id")int id)
	{
		return commentservice.getallpostcommentWitSentimentbypostid(id);
	}
}
