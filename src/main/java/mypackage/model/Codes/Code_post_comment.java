package mypackage.model.Codes;
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
@Table(name = "tblcode_post_comment")
public class Code_post_comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comment_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "post_id",nullable = false)
	private Code_posts code_post;
	
	private String date;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	private String comment_messgae;
	private String comment_photo;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@OneToMany(mappedBy = "code_comment",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("code_comment")
	private Set<Code_post_comment_replies>post_replies;
	
	public Code_post_comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Code_post_comment(int comment_id, Code_posts code_post, String date, User_details user_details,
			String comment_messgae, String comment_photo, int flag) {
		super();
		this.comment_id = comment_id;
		this.code_post = code_post;
		this.date = date;
		this.user_details = user_details;
		this.comment_messgae = comment_messgae;
		this.comment_photo = comment_photo;
		this.flag = flag;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public Code_posts getCode_post() {
		return code_post;
	}
	public void setCode_post(Code_posts code_post) {
		this.code_post = code_post;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public User_details getUser_details() {
		return user_details;
	}
	public void setUser_details(User_details user_details) {
		this.user_details = user_details;
	}
	public String getComment_messgae() {
		return comment_messgae;
	}
	public void setComment_messgae(String comment_messgae) {
		this.comment_messgae = comment_messgae;
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
