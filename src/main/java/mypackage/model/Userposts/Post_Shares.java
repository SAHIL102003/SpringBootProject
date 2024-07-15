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
@Table(name = "tblpost_shares")
public class Post_Shares {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int share_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "post_id",nullable = false)
	private User_posts user_post;
	
	private String share_date;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	@Column(columnDefinition = "integer default 0")
	private int flag;

	public Post_Shares() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post_Shares(int share_id, User_posts user_post, String share_date, User_details user_details, int flag) {
		super();
		this.share_id = share_id;
		this.user_post = user_post;
		this.share_date = share_date;
		this.user_details = user_details;
		this.flag = flag;
	}

	public int getShare_id() {
		return share_id;
	}

	public void setShare_id(int share_id) {
		this.share_id = share_id;
	}

	public User_posts getUser_post() {
		return user_post;
	}

	public void setUser_post(User_posts user_post) {
		this.user_post = user_post;
	}

	public String getShare_date() {
		return share_date;
	}

	public void setShare_date(String share_date) {
		this.share_date = share_date;
	}

	public User_details getUser_details() {
		return user_details;
	}

	public void setUser_details(User_details user_details) {
		this.user_details = user_details;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
