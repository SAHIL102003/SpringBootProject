package mypackage.model.Userposts;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mypackage.model.user.User_details;

public class User_post_Model {
 
	private int post_id;
	
	 
	private User_details user_details;
	
	private String post_date;
	private String post_title;
	private String post_description;
	private String photo;
	 
	private int is_active;
	 
	private int flag;
	private int total_comments;
	private int total_very_positive_comments;
	private int total_positive_comments;
	private int total_neutral_comments;
	private int total_negative_comments;
	private int total_very_negative_comments;
	  
	public int getTotal_comments() {
		return total_comments;
	}
	public void setTotal_comments(int total_comments) {
		this.total_comments = total_comments;
	}
	public int getTotal_very_positive_comments() {
		return total_very_positive_comments;
	}
	public void setTotal_very_positive_comments(int total_very_positive_comments) {
		this.total_very_positive_comments = total_very_positive_comments;
	}
	public int getTotal_positive_comments() {
		return total_positive_comments;
	}
	public void setTotal_positive_comments(int total_positive_comments) {
		this.total_positive_comments = total_positive_comments;
	}
	public int getTotal_neutral_comments() {
		return total_neutral_comments;
	}
	public void setTotal_neutral_comments(int total_neutral_comments) {
		this.total_neutral_comments = total_neutral_comments;
	}
	public int getTotal_negative_comments() {
		return total_negative_comments;
	}
	public void setTotal_negative_comments(int total_negative_comments) {
		this.total_negative_comments = total_negative_comments;
	}
	public int getTotal_very_negative_comments() {
		return total_very_negative_comments;
	}
	public void setTotal_very_negative_comments(int total_very_negative_comments) {
		this.total_very_negative_comments = total_very_negative_comments;
	}
	private Set<Post_Comment_Model>post_comment;
	
	public User_post_Model(int post_id, User_details user_details, String post_date, String post_title,
			String post_description, String photo, int is_active, Set<Post_Comment_Model> post_comment,int total_comments,int total_very_positive_comments,int total_positive_comments,
			int total_neutral_comments,int total_negative_comments,int total_very_negative_comments) {
		super();
		this.post_id = post_id;
		this.user_details = user_details;
		this.post_date = post_date;
		this.post_title = post_title;
		this.post_description = post_description;
		this.photo = photo;
		this.is_active = is_active;
		this.post_comment = post_comment;
		this.total_comments=total_comments;
		this.total_very_positive_comments=total_very_positive_comments;
		this.total_positive_comments=total_positive_comments;
		this.total_neutral_comments=total_neutral_comments;
		this.total_negative_comments=total_negative_comments;
		this.total_very_negative_comments=total_very_negative_comments;
	}
	public Set<Post_Comment_Model> getPost_comment() {
		return post_comment;
	}
	public void setPost_comment(Set<Post_Comment_Model> post_comment) {
		this.post_comment = post_comment;
	}
	 
	public User_post_Model() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User_post_Model(int post_id, User_details user_details, String post_date, String post_title,
			String post_description, String photo, int is_active, int flag) {
		super();
		this.post_id = post_id;
		this.user_details = user_details;
		this.post_date = post_date;
		this.post_title = post_title;
		this.post_description = post_description;
		this.photo = photo;
		this.is_active = is_active;
		this.flag = flag;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public User_details getUser_details() {
		return user_details;
	}
	public void setUser_details(User_details user_details) {
		this.user_details = user_details;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getPost_description() {
		return post_description;
	}
	public void setPost_description(String post_description) {
		this.post_description = post_description;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
