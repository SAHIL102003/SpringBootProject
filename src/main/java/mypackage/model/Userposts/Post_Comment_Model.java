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

public class Post_Comment_Model {

	private int comment_id;

	private User_posts user_post;
		
	private String comment_date;

	private User_details user_details;

	private String comment_message;
	
	private String comment_photo;

	private int flag;

	private int sentimentLevel;
	private String sentimentStatus;
	public int getSentimentLevel() {
		return sentimentLevel;
	}
	public void setSentimentLevel(int sentimentLevel) {
		this.sentimentLevel = sentimentLevel;
	}
	public String getSentimentStatus() {
		return sentimentStatus;
	}
	public void setSentimentStatus(String sentimentStatus) {
		this.sentimentStatus = sentimentStatus;
	}

	public Post_Comment_Model() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post_Comment_Model(int comment_id, User_posts user_post, String comment_date, User_details user_details,
			String comment_message, String comment_photo, int flag,int sentimentLevel,String sentimentStatus) {
		super();
		this.comment_id = comment_id;
		this.user_post = user_post;
		this.comment_date = comment_date;
		this.user_details = user_details;
		this.comment_message = comment_message;
		this.comment_photo = comment_photo;
		this.flag = flag;
		this.sentimentLevel=sentimentLevel;
		this.sentimentStatus=sentimentStatus;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public User_posts getUser_post() {
		return user_post;
	}
	public void setUser_post(User_posts user_post) {
		this.user_post = user_post;
	}
	public String getComment_date() {
		return comment_date;
	}
	public void setComment_date(String comment_date) {
		this.comment_date = comment_date;
	}
	public User_details getUser_details() {
		return user_details;
	}
	public void setUser_details(User_details user_details) {
		this.user_details = user_details;
	}
	public String getComment_message() {
		return comment_message;
	}
	public void setComment_message(String comment_message) {
		this.comment_message = comment_message;
	}
	public String getComment_photo() {
		return comment_photo;
	}
	public void setComment_photo(String comment_photo) {
		this.comment_photo = comment_photo;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
