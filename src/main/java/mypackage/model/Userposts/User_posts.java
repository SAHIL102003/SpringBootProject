package mypackage.model.Userposts;

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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import mypackage.model.user.User_details;

@Entity
@Table(name = "tbluser_posts")
public class User_posts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int post_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	private String post_date;
	private String post_title;
	private String post_description;
	private String photo;
	@Column(columnDefinition = "integer default 0")
	private int is_active;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@OneToMany(mappedBy = "user_post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user_post")
	private Set<Post_comment>post_comment;
	
	public User_posts(int post_id, User_details user_details, String post_date, String post_title,
			String post_description, String photo, int is_active, Set<Post_comment> post_comment) {
		super();
		this.post_id = post_id;
		this.user_details = user_details;
		this.post_date = post_date;
		this.post_title = post_title;
		this.post_description = post_description;
		this.photo = photo;
		this.is_active = is_active;
		this.post_comment = post_comment;
	}
	public Set<Post_comment> getPost_comment() {
		return post_comment;
	}
	public void setPost_comment(Set<Post_comment> post_comment) {
		this.post_comment = post_comment;
	}
	 
	public User_posts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User_posts(int post_id, User_details user_details, String post_date, String post_title,
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
