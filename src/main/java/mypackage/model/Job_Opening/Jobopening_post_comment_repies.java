package mypackage.model.Job_Opening;

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
@Table(name = "tbljobopening_post_comment_repies")
public class Jobopening_post_comment_repies {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reply_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "comment_id",nullable = false)
	private Jobopening_post_comment job_opening_post_comment;
	
	private String reply_date;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	@Column(length = 2500)
	private String reply_message;
	private String comment_photo;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	public Jobopening_post_comment_repies() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Jobopening_post_comment_repies(int reply_id, Jobopening_post_comment job_opening_post_comment,
			String reply_date, User_details user_details, String reply_message, String comment_photo, int flag) {
		super();
		this.reply_id = reply_id;
		this.job_opening_post_comment = job_opening_post_comment;
		this.reply_date = reply_date;
		this.user_details = user_details;
		this.reply_message = reply_message;
		this.comment_photo = comment_photo;
		this.flag = flag;
	}
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public Jobopening_post_comment getJob_opening_post_comment() {
		return job_opening_post_comment;
	}
	public void setJob_opening_post_comment(Jobopening_post_comment job_opening_post_comment) {
		this.job_opening_post_comment = job_opening_post_comment;
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
