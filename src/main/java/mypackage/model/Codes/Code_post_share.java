package mypackage.model.Codes;

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
@Table(name = "tblcode_post_share")
public class Code_post_share {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int share_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "post_id",nullable = false)
	private Code_posts code_post;
	
	private String share_date;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	@Column(columnDefinition = "integer default 0")
	private int flag;

	public Code_post_share() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Code_post_share(int share_id, Code_posts code_post, String share_date, User_details user_details, int flag) {
		super();
		this.share_id = share_id;
		this.code_post = code_post;
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

	public Code_posts getCode_post() {
		return code_post;
	}

	public void setCode_post(Code_posts code_post) {
		this.code_post = code_post;
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
