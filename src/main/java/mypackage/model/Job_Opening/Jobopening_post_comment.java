package mypackage.model.Job_Opening;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mypackage.model.user.User_details;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "tbljob_opening_post_comment")
public class Jobopening_post_comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comment_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "opening_id",nullable = false)
	private Job_opening job_opening;
	
	private String comment_date;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	@Column(length = 2000)
	private String comment_message;
	private String comment_photo;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@OneToMany(mappedBy = "job_opening_post_comment",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("job_opening_post_comment")
	private Set<Jobopening_post_comment_repies>opening_comment_repies;
	
	public Jobopening_post_comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Jobopening_post_comment(int comment_id, Job_opening job_opening, String comment_date,
			User_details user_details, String comment_message, String comment_photo, int flag) {
		super();
		this.comment_id = comment_id;
		this.job_opening = job_opening;
		this.comment_date = comment_date;
		this.user_details = user_details;
		this.comment_message = comment_message;
		this.comment_photo = comment_photo;
		this.flag = flag;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public Job_opening getJob_opening() {
		return job_opening;
	}
	public void setJob_opening(Job_opening job_opening) {
		this.job_opening = job_opening;
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
