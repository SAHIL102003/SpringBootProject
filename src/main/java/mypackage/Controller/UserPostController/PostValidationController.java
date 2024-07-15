package mypackage.Controller.UserPostController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mypackage.ExtraServices;
import mypackage.FileStorageService.FileStorageServices;
import mypackage.Services.Userpost.UserPostServices;
import mypackage.model.Userposts.User_posts;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")

public class PostValidationController {

	   private static final Logger logger = LoggerFactory.getLogger(UserPostController.class);
		@Autowired
		UserPostServices postservice;
		
		 
	 
		@Autowired
		private FileStorageServices fileStorageService;
		
//		@GetMapping("Getalluserposts")
//		public List<User_posts>getall()
//		{
//			return postservice.getallpost();
//		}	
		
//		@GetMapping("validatePost/{id}")
//		public String[] getbyid(@PathVariable("id")int id)
//		{
//			ExtraServices es=new ExtraServices();
//			String data=es.GetTextFromImage();
//			String[]st=data.split(" ");
//			return st;
//		}
		
		@GetMapping("validatePost/{id}")
		public String getbyid(@PathVariable("id")int id)
		{
			ExtraServices es=new ExtraServices();
			String data=es.GetTextFromImage();

			return data;
		}
		
		
}
