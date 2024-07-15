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
@Table(name = "tblpost_like_dislike")
public class Post_like_Dislike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int like_dislike_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "post_id",nullable = false)
	private User_posts user_post;
	
	private String like_dislike_date;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	private int is_like;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	public Post_like_Dislike() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Post_like_Dislike(int like_dislike_id, User_posts user_post, String like_dislike_date,
			User_details user_details, int is_like, int flag) {
		super();
		this.like_dislike_id = like_dislike_id;
		this.user_post = user_post;
		this.like_dislike_date = like_dislike_date;
		this.user_details = user_details;
		this.is_like = is_like;
		this.flag = flag;
	}
	public int getLike_dislike_id() {
		return like_dislike_id;
	}
	public void setLike_dislike_id(int like_dislike_id) {
		this.like_dislike_id = like_dislike_id;
	}
	public User_posts getUser_post() {
		return user_post;
	}
	public void setUser_post(User_posts user_post) {
		this.user_post = user_post;
	}
	public String getLike_dislike_date() {
		return like_dislike_date;
	}
	public void setLike_dislike_date(String like_dislike_date) {
		this.like_dislike_date = like_dislike_date;
	}
	public User_details getUser_details() {
		return user_details;
	}
	public void setUser_details(User_details user_details) {
		this.user_details = user_details;
	}
	public int getIs_like() {
		return is_like;
	}
	public void setIs_like(int is_like) {
		this.is_like = is_like;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
