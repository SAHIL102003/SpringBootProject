package mypackage.model.Job_Opening;

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
@Table(name = "tbljob_opening")
public class Job_opening {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int opening_id;
	@Column(nullable = false)
	private String opening_date;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	private String company_name;
	private String job_title;
	private String experiance_required;
	@Column(name = "job_description",length = 1500)
	private String job_description;
	
	@Column(columnDefinition = "integer default 0")
	private int flag;
	@Column(columnDefinition = "integer default 0")
	private int is_active;
	
	@OneToMany(mappedBy = "job_opening",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("job_opening")
	private Set<Jobopening_post_comment>jobopening_post_comment;
	
	public Job_opening() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Job_opening(int opening_id, String opening_date, User_details user_details, String company_name,
			String job_title, String experiance_required, String job_description, int flag, int is_active) {
		super();
		this.opening_id = opening_id;
		this.opening_date = opening_date;
		this.user_details = user_details;
		this.company_name = company_name;
		this.job_title = job_title;
		this.experiance_required = experiance_required;
		this.job_description = job_description;
		this.flag = flag;
		this.is_active = is_active;
	}
	public int getOpening_id() {
		return opening_id;
	}
	public void setOpening_id(int opening_id) {
		this.opening_id = opening_id;
	}
	public String getOpening_date() {
		return opening_date;
	}
	public void setOpening_date(String opening_date) {
		this.opening_date = opening_date;
	}
	public User_details getUser_details() {
		return user_details;
	}
	public void setUser_details(User_details user_details) {
		this.user_details = user_details;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getExperiance_required() {
		return experiance_required;
	}
	public void setExperiance_required(String experiance_required) {
		this.experiance_required = experiance_required;
	}
	public String getJob_description() {
		return job_description;
	}
	public void setJob_description(String job_description) {
		this.job_description = job_description;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	
}
