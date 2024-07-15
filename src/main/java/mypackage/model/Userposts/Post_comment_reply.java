package mypackage.model.Userposts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mypackage.model.user.User_details;

@Entity
@Table(name = "tblpost_comment_reply")
public class Post_comment_reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reply_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "comment_id",nullable = false)
	private Post_comment post_comment;
	
	private String reply_date;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	@Column(length = 5000)
	private String reply_message;
	private String reply_photo;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	
	public Post_comment_reply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post_comment_reply(int reply_id, Post_comment post_comment, String reply_date, User_details user_details,
			String reply_message, String reply_photo, int flag) {
		super();
		this.reply_id = reply_id;
		this.post_comment = post_comment;
		this.reply_date = reply_date;
		this.user_details = user_details;
		this.reply_message = reply_message;
		this.reply_photo = reply_photo;
		this.flag = flag;
	}
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public Post_comment getPost_comment() {
		return post_comment;
	}
	public void setPost_comment(Post_comment post_comment) {
		this.post_comment = post_comment;
	}
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = reply_date;
	}
	public User_details getUser_details() {
		return user_details;
	}
	public void setUser_details(User_details user_details) {
		this.user_details = user_details;
	}
	public String getReply_message() {
		return reply_message;
	}
	public void setReply_message(String reply_message) {
		this.reply_message = reply_message;
	}
	public String getReply_photo() {
		return reply_photo;
	}
	public void setReply_photo(String reply_photo) {
		this.reply_photo = reply_photo;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}
