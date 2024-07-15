package mypackage.Services.Userpost;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mypackage.NlpPipeline;
import mypackage.SentimentModel;
import mypackage.model.Codes.Code_posts;
import mypackage.model.Userposts.Post_Comment_Model;
import mypackage.model.Userposts.Post_comment;
import mypackage.model.Userposts.Post_comment_reply;
import mypackage.model.Userposts.User_post_Model;
import mypackage.model.Userposts.User_posts;
import mypackage.model.user.User_details;
import mypackage.repositories.User.IUserdetailsRepositories;
import mypackage.repositories.Userposts.IUserpostRepositories;
import mypackage.repositories.Userposts.PostcommentRepositories;
import mypackage.repositories.Userposts.PostcommentrRepliesRepositories;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class UserPostServices {

	@Autowired
	IUserpostRepositories userrepo;
	@Autowired
	PostcommentRepositories commentrepo;
	
	@Autowired
	IUserdetailsRepositories userdetailrepo;
	@Autowired
	PostcommentrRepliesRepositories replyrepo;
	
//	public List<User_posts>getallpost()
//	{
//		List<User_posts>lst=new ArrayList<User_posts>();
//		for(User_posts u:userrepo.findAll())
//		{
//			if(u.getIs_active()==1 &&u.getFlag()==0)
//			{
//				User_details ud=new User_details(u.getUser_details().getUser_id(), u.getUser_details().getFirst_name(), null, u.getUser_details().getLast_name(), null, null, null,"upload/"+ u.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
//
//				User_posts up=new User_posts(u.getPost_id(), ud, u.getPost_date(), u.getPost_title(), u.getPost_description(),"upload/"+ u.getPhoto(), u.getIs_active(), u.getFlag());
//				lst.add(up);		
//			}
//		}
//		return lst;
//	}
//	
	
	public List<User_posts>GetAllPosts()
	{
		List<User_posts>lst=new ArrayList<User_posts>();
		List<User_posts> userposts=userrepo.findAll();
		
		  Collections.sort(userposts, new PersonComparator());
		   Collections.reverse(userposts);
		for(User_posts u:userposts)
		{
			 
//			List<Post_comment_reply>replys=new ArrayList<Post_comment_reply>();
//			
//		//	if(u.getIs_active()==1 &&u.getFlag()==0)
//			//{
//			Set<Post_comment>comments=new HashSet<Post_comment>();
//				List<Post_comment>commentlist=commentrepo.findAll();
//				for(Post_comment cm :commentlist) {
//				
//					if(cm.getUser_post().getPost_id()==u.getPost_id()) {
//					//	System.out.println(cm.getUser_post().getPost_id()+"----"+u.getPost_id());
//						User_details du=(User_details)userdetailrepo.findById(cm.getUser_details().getUser_id()).get();
//						User_details ud=new User_details(du.getUser_id(), du.getFirst_name(), du.getMiddle_name(), du.getLast_name(), null, null, null,"upload/"+ du.getUser_photo(), null, null, null, 0, null, 0, null, null, null);
//						 
//						Post_comment pm =new Post_comment(cm.getComment_id(), null, cm.getComment_date(), ud, cm.getComment_message(), "upload/"+cm.getComment_photo(), 0);
//						
//						
//						comments.add(pm);
//						
//					}
//		 	}
		
			//	System.out.println(comments.size());
				User_details ud=new User_details(u.getUser_details().getUser_id(), u.getUser_details().getFirst_name(), null, u.getUser_details().getLast_name(), null, null, null,"upload/"+ u.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
 		 	User_posts up=new User_posts(u.getPost_id(),ud, u.getPost_date(),u.getPost_title(), u.getPost_description(), "upload/"+u.getPhoto(), u.getIs_active(), null);
 			//	User_posts up=new User_posts(u.getPost_id(), ud, u.getPost_date(), u.getPost_title(), u.getPost_description(),"upload/"+ u.getPhoto(), u.getIs_active(), u.getFlag());
				lst.add(up);	
 			//	System.out.println(up);
			//}
		}
	 
		
//		for(User_posts p :lst) {
//			for(Post_comment c : p.getPost_comment()) {
//				System.out.println(c.getComment_date());
//			}
//		}
		return lst;
	}
	
	public List<User_posts>GetAllActivatedPostsWithComments()
	{
		List<User_posts>lst=new ArrayList<User_posts>();
		List<User_posts> userposts=userrepo.findAll();
		
		  Collections.sort(userposts, new PersonComparator());
		   Collections.reverse(userposts);
		for(User_posts u:userposts)
		{
			if(u.getIs_active()==1)
			{
			List<Post_comment_reply>replys=new ArrayList<Post_comment_reply>();
			
		//	if(u.getIs_active()==1 &&u.getFlag()==0)
			//{
			Set<Post_comment>comments=new HashSet<Post_comment>();
				List<Post_comment>commentlist=commentrepo.findAll();
				for(Post_comment cm :commentlist) {
				
					if(cm.getUser_post().getPost_id()==u.getPost_id()) {
					//	System.out.println(cm.getUser_post().getPost_id()+"----"+u.getPost_id());
						User_details du=(User_details)userdetailrepo.findById(cm.getUser_details().getUser_id()).get();
						User_details ud=new User_details(du.getUser_id(), du.getFirst_name(), du.getMiddle_name(), du.getLast_name(), null, null, null,"upload/"+ du.getUser_photo(), null, null, null, 0, null, 0, null, null, null);
						 
						Post_comment pm =new Post_comment(cm.getComment_id(), null, cm.getComment_date(), ud, cm.getComment_message(), "upload/"+cm.getComment_photo(), 0);
						
						
						comments.add(pm);
						
					}
		 	}
		
			//	System.out.println(comments.size());
				User_details ud=new User_details(u.getUser_details().getUser_id(), u.getUser_details().getFirst_name(), null, u.getUser_details().getLast_name(), null, null, null,"upload/"+ u.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
 		 	User_posts up=new User_posts(u.getPost_id(),ud, u.getPost_date(),u.getPost_title(), u.getPost_description(), "upload/"+u.getPhoto(), u.getIs_active(), comments);
 			//	User_posts up=new User_posts(u.getPost_id(), ud, u.getPost_date(), u.getPost_title(), u.getPost_description(),"upload/"+ u.getPhoto(), u.getIs_active(), u.getFlag());
				lst.add(up);	
 			//	System.out.println(up);
			//}
		}
		}
		
		for(User_posts p :lst) {
			for(Post_comment c : p.getPost_comment()) {
				System.out.println(c.getComment_date());
			}
		}
		return lst;
	}

	
	public List<User_post_Model>GetAllActivatedPostsWithSentimentComments()
	{
		List<User_post_Model>lst=new ArrayList<User_post_Model>();
		List<User_posts> userposts=userrepo.findAll();
		
		  Collections.sort(userposts, new PersonComparator());
		   Collections.reverse(userposts);
		for(User_posts u:userposts)
		{
			
		 
			if(u.getIs_active()==1)
			{
			List<Post_comment_reply>replys=new ArrayList<Post_comment_reply>();
			
		//	if(u.getIs_active()==1 &&u.getFlag()==0)
			//{
			Set<Post_Comment_Model>comments=new HashSet<Post_Comment_Model>();
				List<Post_comment>commentlist=commentrepo.findAll();
				int totalcomments=0, verypositovecount=0,positivecount=0,neutralcount=0,negativecount=0,verynegativecount=0;
				for(Post_comment cm :commentlist) {
				
					if(cm.getUser_post().getPost_id()==u.getPost_id()) {
					//	System.out.println(cm.getUser_post().getPost_id()+"----"+u.getPost_id());
						User_details du=(User_details)userdetailrepo.findById(cm.getUser_details().getUser_id()).get();
						User_details ud=new User_details(du.getUser_id(), du.getFirst_name(), du.getMiddle_name(), du.getLast_name(), null, null, null,"upload/"+ du.getUser_photo(), null, null, null, 0, null, 0, null, null, null);
						 
						 Post_comment p =new Post_comment(cm.getComment_id(), null, cm.getComment_date(), ud, cm.getComment_message(), "upload/"+cm.getComment_photo(), 0);
						 NlpPipeline.init();
						 List<SentimentModel>lstdata=   NlpPipeline.estimatingSentiment(p.getComment_message());
						 	SentimentModel s=lstdata.get(0);
						 	totalcomments++;
						 	if(s.getSentimentInt()==0) {
						 		verynegativecount++;
						 	}
						 	if(s.getSentimentInt()==1) {
						 		negativecount++;
						 	}
						 	if(s.getSentimentInt()==2) {
						 		neutralcount++;
						 	}
							if(s.getSentimentInt()==3) {
						 		positivecount++;
						 	}
							if(s.getSentimentInt()==4) {
								verypositovecount++;
						 	}
						 	System.out.println(s.getSentence()+" "+s.getSentimentInt()+" "+s.getSentimentName());
						
						Post_Comment_Model ps=new Post_Comment_Model(p.getComment_id(), null, p.getComment_date(), ud, p.getComment_message(),"upload/"+ p.getComment_photo(), p.getFlag(),s.getSentimentInt(),s.getSentimentName());
						
						comments.add(ps);
						
						 
						
					}
		 	}
		
			//	System.out.println(comments.size());
				User_details ud=new User_details(u.getUser_details().getUser_id(), u.getUser_details().getFirst_name(), null, u.getUser_details().getLast_name(), null, null, null,"upload/"+ u.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
				User_post_Model up=new User_post_Model(u.getPost_id(),ud, u.getPost_date(),u.getPost_title(), u.getPost_description(), "upload/"+u.getPhoto(), u.getIs_active(), comments,totalcomments,verypositovecount,positivecount,neutralcount,negativecount,verynegativecount);
 			//	User_posts up=new User_posts(u.getPost_id(), ud, u.getPost_date(), u.getPost_title(), u.getPost_description(),"upload/"+ u.getPhoto(), u.getIs_active(), u.getFlag());
				lst.add(up);	
 			//	System.out.println(up);
			//}
		}
		}
		
		 
		return lst;
	}

	

	public List<User_post_Model>GetAllPostsWithSentimentComments()
	{
		List<User_post_Model>lst=new ArrayList<User_post_Model>();
		List<User_posts> userposts=userrepo.findAll();
		
		  Collections.sort(userposts, new PersonComparator());
		   Collections.reverse(userposts);
		for(User_posts u:userposts)
		{
			
		 
			//if(u.getIs_active()==1)
			{
			List<Post_comment_reply>replys=new ArrayList<Post_comment_reply>();
			
		//	if(u.getIs_active()==1 &&u.getFlag()==0)
			//{
			Set<Post_Comment_Model>comments=new HashSet<Post_Comment_Model>();
				List<Post_comment>commentlist=commentrepo.findAll();
				int totalcomments=0, verypositovecount=0,positivecount=0,neutralcount=0,negativecount=0,verynegativecount=0;
				for(Post_comment cm :commentlist) {
				
					if(cm.getUser_post().getPost_id()==u.getPost_id()) {
					//	System.out.println(cm.getUser_post().getPost_id()+"----"+u.getPost_id());
						User_details du=(User_details)userdetailrepo.findById(cm.getUser_details().getUser_id()).get();
						User_details ud=new User_details(du.getUser_id(), du.getFirst_name(), du.getMiddle_name(), du.getLast_name(), null, null, null,"upload/"+ du.getUser_photo(), null, null, null, 0, null, 0, null, null, null);
						 
						 Post_comment p =new Post_comment(cm.getComment_id(), null, cm.getComment_date(), ud, cm.getComment_message(), ""+cm.getComment_photo(), 0);
						 NlpPipeline.init();
						 List<SentimentModel>lstdata=   NlpPipeline.estimatingSentiment(p.getComment_message());
						 	SentimentModel s=lstdata.get(0);
						 	totalcomments++;
						 	if(s.getSentimentInt()==0) {
						 		verynegativecount++;
						 	}
						 	if(s.getSentimentInt()==1) {
						 		negativecount++;
						 	}
						 	if(s.getSentimentInt()==2) {
						 		neutralcount++;
						 	}
							if(s.getSentimentInt()==3) {
						 		positivecount++;
						 	}
							if(s.getSentimentInt()==4) {
								verypositovecount++;
						 	}
						 	System.out.println(s.getSentence()+" "+s.getSentimentInt()+" "+s.getSentimentName());
						
						Post_Comment_Model ps=new Post_Comment_Model(p.getComment_id(), null, p.getComment_date(), ud, p.getComment_message(),"upload/"+ p.getComment_photo(), p.getFlag(),s.getSentimentInt(),s.getSentimentName());
						
						comments.add(ps);
						
						 
						
					}
		 	}
		
			//	System.out.println(comments.size());
				User_details ud=new User_details(u.getUser_details().getUser_id(), u.getUser_details().getFirst_name(), null, u.getUser_details().getLast_name(), null, null, null,"upload/"+ u.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
				User_post_Model up=new User_post_Model(u.getPost_id(),ud, u.getPost_date(),u.getPost_title(), u.getPost_description(), "upload/"+u.getPhoto(), u.getIs_active(), comments,totalcomments,verypositovecount,positivecount,neutralcount,negativecount,verynegativecount);
 			//	User_posts up=new User_posts(u.getPost_id(), ud, u.getPost_date(), u.getPost_title(), u.getPost_description(),"upload/"+ u.getPhoto(), u.getIs_active(), u.getFlag());
				lst.add(up);	
 			//	System.out.println(up);
			//}
		}
		}
		
		 
		return lst;
	}

	

	public User_post_Model GetPostsWiseSentimentComments(int postId)
	{
		List<User_post_Model> lst=GetAllPostsWithSentimentComments();
		User_post_Model p=null;
		
		for(User_post_Model m : lst) {
			if(m.getPost_id()==postId) {
				p=m;
				break;
			}
		 
		
	}
		return p;
	
	}
//    private Sort sortByIdAsc() {
//        return new Sort(Sort.Direction.ASC, "id");
//    }
	public String Addpost(User_posts u)
	{
		//System.out.println(u.getFlag()+" "+);
	User_posts p=	userrepo.save(u);

	System.out.println("Inserted Post = 		"+p.getPost_id());
		String imgpath="upload/"+u.getPhoto();
		String msg=	GetImageText(imgpath);
//		System.out.println(msg);
		
		 NlpPipeline.init();
		 List<SentimentModel>lstdata=   NlpPipeline.estimatingSentiment(msg);
		 	SentimentModel s=lstdata.get(0);
		 	 List<SentimentModel>lstdata2=   NlpPipeline.estimatingSentiment(u.getPost_description());
			 	SentimentModel s2=lstdata2.get(0);

			 	 List<SentimentModel>lstdata3=   NlpPipeline.estimatingSentiment(u.getPost_title());
				 	SentimentModel s3=lstdata3.get(0);

		 	if(s.getSentimentInt()<=1 ||  s2.getSentimentInt()<=1 || s3.getSentimentInt()<=1)
		 	{
		 		Rejectpost(p.getPost_id());
		 		return "Post Stored is Rejected for users";
		 	}
		 	else {
		 	
		 	Approvepost(p.getPost_id());
		 	return "Post Stored is Approved for users";
		 	}
//		 	System.out.println(s.getSentence());
//		 	System.out.println(s.getSentimentInt());
//		 	System.out.println(s.getSentimentName());
		 	
	}
	public void updatepost(User_posts u)
	{
		userrepo.save(u);
	}
	
	public User_posts Getuserpostbyid(int id)
	{
		User_posts u=(User_posts)userrepo.findById(id).get();
		
		User_details ud=new User_details(u.getUser_details().getUser_id(), null, null, null, null, null, null, null, null, null, null, 0, null, 0, null, null, null);

		User_posts up=new User_posts(u.getPost_id(), ud, u.getPost_date(), u.getPost_title(), u.getPost_description(), u.getPhoto(), u.getIs_active(), u.getFlag());
		return(up);	
		
	}
	
	public void Deleteuserpost(int id)
	{
		User_posts u=userrepo.findById(id).get();
		u.setFlag(1);
		userrepo.save(u);
	}
	

	public void Restoreuserpost(int id)
	{
		User_posts u=userrepo.findById(id).get();
		u.setFlag(1);
		userrepo.save(u);
	}
	
	public List<User_posts>Getpostsbyuserid(int id)
	{

		List<User_posts>lst=new ArrayList<User_posts>();
		List<User_posts> userposts=userrepo.findAll();
		
		  Collections.sort(userposts, new PersonComparator());
		   Collections.reverse(userposts);
		for(User_posts u:userposts)
		{
			
			List<Post_comment_reply>replys=new ArrayList<Post_comment_reply>();
			
	 if(u.getUser_details().getUser_id()==id)
			 {
			Set<Post_comment>comments=new HashSet<Post_comment>();
				List<Post_comment>commentlist=commentrepo.findAll();
				for(Post_comment cm :commentlist) {
				
					if(cm.getUser_post().getPost_id()==u.getPost_id()) {
					//	System.out.println(cm.getUser_post().getPost_id()+"----"+u.getPost_id());
						User_details du=(User_details)userdetailrepo.findById(cm.getUser_details().getUser_id()).get();
						User_details ud=new User_details(du.getUser_id(), du.getFirst_name(), du.getMiddle_name(), du.getLast_name(), null, null, null,"upload/"+ du.getUser_photo(), null, null, null, 0, null, 0, null, null, null);
						 
						Post_comment pm =new Post_comment(cm.getComment_id(), null, cm.getComment_date(), ud, cm.getComment_message(), "upload/"+cm.getComment_photo(), 0);
						comments.add(pm);
						
					}
		 	}
		
			//	System.out.println(comments.size());
				User_details ud=new User_details(u.getUser_details().getUser_id(), u.getUser_details().getFirst_name(), null, u.getUser_details().getLast_name(), null, null, null,"upload/"+ u.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);
 		 	User_posts up=new User_posts(u.getPost_id(),ud, u.getPost_date(),u.getPost_title(), u.getPost_description(), "upload/"+u.getPhoto(), u.getIs_active(), comments);
 			//	User_posts up=new User_posts(u.getPost_id(), ud, u.getPost_date(), u.getPost_title(), u.getPost_description(),"upload/"+ u.getPhoto(), u.getIs_active(), u.getFlag());
				lst.add(up);	
 			//	System.out.println(up);
			 }
		}
		
		for(User_posts p :lst) {
			for(Post_comment c : p.getPost_comment()) {
				System.out.println(c.getComment_date());
			}
		}
		return lst;
	}
	
	public List<User_posts>getpostapprovel()
	{
		List<User_posts>lst=new ArrayList<User_posts>();
		for(User_posts u:userrepo.findAll())
		{
			if(u.getIs_active()==0 &&u.getFlag()==0)
			{
				User_details ud=new User_details(u.getUser_details().getUser_id(), u.getUser_details().getFirst_name(), null, u.getUser_details().getLast_name(), null, null, null,"upload/"+ u.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);

				User_posts up=new User_posts(u.getPost_id(), ud, u.getPost_date(), u.getPost_title(), u.getPost_description(),"upload/"+ u.getPhoto(), u.getIs_active(), u.getFlag());
				lst.add(up);		
			}
		}
		return lst;
	}
	
	public void Approvepost(int id)
	{
		User_posts u=(User_posts)userrepo.findById(id).get();
		u.setIs_active(1);	
		userrepo.save(u);
	}
	public void Rejectpost(int id)
	{
		User_posts u=(User_posts)userrepo.findById(id).get();
		u.setIs_active(2);	
		userrepo.save(u);
	}
	
	public String GetImageText(String imgpath) {
		 ITesseract tesseract = new Tesseract(); 
		 String text="";
		   try { 

			          // the path of your tess data folder inside the extracted file
			          tesseract.setDatapath("C:\\Program Files\\Tesseract-OCR\\tessdata"); 

			          // path of your image file 
			         //   text = tesseract.doOCR(new File("C:\\Users\\CIIT\\OneDrive\\Desktop\\images\\dd.jpg"));         
			          text = tesseract.doOCR(new File(imgpath));  
			      //    
		 
			       } catch (TesseractException e) { 
			            e.printStackTrace(); 
			       }
		   
		   return text;
	}
	
	
	
	
	public List<User_posts>getallUserpost()
	{
		List<User_posts>lst=new ArrayList<User_posts>();
		for(User_posts u:userrepo.findAll())
		{
			if( u.getFlag()==0)
			{
				User_details ud=new User_details(u.getUser_details().getUser_id(), u.getUser_details().getFirst_name(), null, u.getUser_details().getLast_name(), null, null, null,"upload/"+ u.getUser_details().getUser_photo(), null, null, null, 0, null, 0, null, null, null);

				User_posts up=new User_posts(u.getPost_id(), ud, u.getPost_date(), u.getPost_title(), u.getPost_description(),"upload/"+ u.getPhoto(), u.getIs_active(), u.getFlag());
				lst.add(up);		
			}
		}
		return lst;
	}
	

}

class PersonComparator implements java.util.Comparator<User_posts> {
    @Override
    public int compare(User_posts a, User_posts b) {
        return a.getPost_id() - b.getPost_id();
    }
}
 
