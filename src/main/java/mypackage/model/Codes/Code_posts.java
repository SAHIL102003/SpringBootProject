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
import mypackage.model.mastertables.Topic_contents;
import mypackage.model.user.User_details;

@Entity
@Table(name = "tblcodes_post")
public class Code_posts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int post_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	private String date;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "content_id",nullable = false)
	private Topic_contents topic_content;
	
	private String question;
	private String code;
	private String description;
	@Column(columnDefinition = "integer default 0")
	private int is_active;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@OneToMany(mappedBy = "code_post",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("code_post")
	private Set<Code_post_comment>code_post_comment;
	
	public Code_posts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Code_posts(int post_id, User_details user_details, String date, Topic_contents topic_content,
			String question, String code, String description, int is_active, int flag) {
		super();
		this.post_id = post_id;
		this.user_details = user_details;
		this.date = date;
		this.topic_content = topic_content;
		this.question = question;
		this.code = code;
		this.description = description;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Topic_contents getTopic_content() {
		return topic_content;
	}
	public void setTopic_content(Topic_contents topic_content) {
		this.topic_content = topic_content;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
