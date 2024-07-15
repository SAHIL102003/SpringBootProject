package mypackage;

 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import mypackage.Services.Userpost.PostCommentServices;
import mypackage.model.Userposts.Post_comment;
import mypackage.model.Userposts.User_posts;
import mypackage.model.user.User_details;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by frkn on 15/01/2017.
 */
@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT},allowedHeaders = "*")

public class MainController {

    @Autowired
    private FaceDetectionService faceDetectionService;
	@Autowired
	PostCommentServices commentservice;


    @ResponseBody
    @RequestMapping(value = "/faceDetect/image", method = RequestMethod.POST, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] detectFaceImage(@RequestParam("file") MultipartFile file,@RequestParam("user_id")int user_id,@RequestParam("post_id")int post_id) throws IOException {

       if ( !validateImage(file))
       {
          return new byte[1];
       }

        return faceDetectionService.detectFace(file).toImage();
    }

    @ResponseBody
    @RequestMapping(value = "/faceDetect/json", method = RequestMethod.POST)
    public List<FaceEntity> detectFaceJson(@RequestParam("file") MultipartFile file) throws IOException {

    	System.out.println("Inside");
    	System.out.println(file.getContentType());
        if ( !validateImage(file))
        {
            return new ArrayList<>();
        }

        return faceDetectionService.detectFace(file).toList();
    }


    
    @RequestMapping(value="/uploadImage2",method = RequestMethod.POST)
    public @ResponseBody String uploadImage2(@RequestParam("imageValue") String imageValue,@RequestParam("user_id")int user_id,@RequestParam("post_id")int post_id,HttpServletRequest request)
    {


        try
        {
        	System.out.println("Calling User Id="+user_id+"  Post Id="+post_id);
        	System.out.println(imageValue);
        		String []data=imageValue.split(",");

            //This will decode the String which is encoded by using Base64 class
            byte[] imageByte= Base64.getDecoder().decode(data[1]);

            System.out.println(imageByte.length);
//
            String directory="upload/sample.jpg";
//
            new FileOutputStream(directory).write(imageByte);
            File file=new File(directory);
            System.out.println(file.toPath());
            System.out.println(file.getName());
            System.out.println(file.getParent());
            System.out.println(file.length());
//            FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
//            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            FileItem fileItem = new DiskFileItemFactory().createItem("file",
       	    Files.probeContentType(file.toPath()), false, file.getName());

            	try (InputStream in = new FileInputStream(file); OutputStream out = fileItem.getOutputStream()) {
            	    in.transferTo(out);
            	} catch (Exception e) {
            	    System.out.println("Invalid file: " + e.getMessage());
            	}

            	CommonsMultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            
           String result=  faceDetectionService.detectSmileOnFace(multipartFile);
//            System.out.println("Total Faces="+lst.size());
            
          return result;
        }
        catch(Exception e)
        {
            return "error = "+e;
        }

    }    
    

    @RequestMapping(value="/commentbycamera",method = RequestMethod.POST)
    public @ResponseBody String uploadImage2(@RequestParam("imageValue") String imageValue,@RequestParam("user_id")int user_id,@RequestParam("post_id")int post_id,@RequestParam("comment_date")String comment_date,HttpServletRequest request)
    {


        try
        {
        	System.out.println("Calling User Id="+user_id+"  Post Id="+post_id);
        	System.out.println(imageValue);
        		String []data=imageValue.split(",");

            //This will decode the String which is encoded by using Base64 class
            byte[] imageByte= Base64.getDecoder().decode(data[1]);

            System.out.println(imageByte.length);
//
            String directory="upload/sample.jpg";
//
            new FileOutputStream(directory).write(imageByte);
            File file=new File(directory);
            System.out.println(file.toPath());
            System.out.println(file.getName());
            System.out.println(file.getParent());
            System.out.println(file.length());
//            FileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());
//            MultipartFile multipartFile = new CommonsMultipartFile(fileItem);

            FileItem fileItem = new DiskFileItemFactory().createItem("file",
            	    Files.probeContentType(file.toPath()), false, file.getName());

            	try (InputStream in = new FileInputStream(file); OutputStream out = fileItem.getOutputStream()) {
            	    in.transferTo(out);
            	} catch (Exception e) {
            	    System.out.println("Invalid file: " + e.getMessage());
            	}

            	CommonsMultipartFile multipartFile = new CommonsMultipartFile(fileItem);
            
           String result=  faceDetectionService.detectSmileOnFace(multipartFile);
//            System.out.println("Total Faces="+lst.size());
            System.out.println(result);
			User_details ud=new User_details(user_id, null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);
			
		User_posts up=new User_posts(post_id, null, null, null, null, null, 0, 0);
		String sentiment="",comment_message="";
		
		if(result.equals("Smile"))
		{
			System.out.println("Inside Smile");
			sentiment="Happy.png";
			comment_message="Awesome";
		}
		else {
			System.out.println("Inside Sad");
			sentiment="Sad.png";
			comment_message="Hate";
		}
		System.out.println(sentiment);
		Post_comment p=new Post_comment(0, up, comment_date, ud, comment_message, sentiment, 0);

		commentservice.AddCommenttopost(p);
          return result;
        }
        catch(Exception e)
        {
            return "error = "+e;
        }

    }    
    


//    public  String display(String imageValue)
//    {
//
//
//
//    	System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
//		  
//
//		  
//        // Storing the image in a Matrix object 
//        // of Mat type 
//        Mat src = Imgcodecs.imread(imageValue); 
//  
//        // New matrix to store the final image 
//        // where the input image is supposed to be written 
//        Mat dst = new Mat(); 
//  
//        // Scaling the Image using Resize function 
//        Imgproc.resize(src, dst, new Size(0, 0), 0.1, 0.1, 
//                       Imgproc.INTER_AREA); 
//  
//        // Writing the image from src to destination 
//        Imgcodecs.imwrite("upload/resized_image.jpg", dst); 
//  
//        // Display message to show that 
//        // image has been scaled 
//        System.out.println("Image Processed");
//        return "Image Processed";
//	
//
//    }
//    

    private Boolean validateImage(MultipartFile image) {
        return image.getContentType().equals("image/jpeg");
    }


}
