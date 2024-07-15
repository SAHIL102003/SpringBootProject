package mypackage.model.user;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import mypackage.model.mastertables.Designations;


@Entity
@Table(name = "tblexperience_details")
public class Experience_Details {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int experience_id;
	@Column(nullable = false)
	private String company_name;
	@Column(nullable = false)
	private int from_year;
	@Column(nullable = false)
	private int to_year;
	@Column(length = 2500)
	private String Job_descrption;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "designation_id",nullable = false)
	private Designations designation;

	public Experience_Details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Experience_Details(int experience_id, String company_name, int from_year, int to_year, String job_descrption,
			int flag, User_details user_details, Designations designation) {
		super();
		this.experience_id = experience_id;
		this.company_name = company_name;
		this.from_year = from_year;
		this.to_year = to_year;
		Job_descrption = job_descrption;
		this.flag = flag;
		this.user_details = user_details;
		this.designation = designation;
	}

	public int getExperience_id() {
		return experience_id;
	}

	public void setExperience_id(int experience_id) {
		this.experience_id = experience_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public int getFrom_year() {
		return from_year;
	}

	public void setFrom_year(int from_year) {
		this.from_year = from_year;
	}

	public int getTo_year() {
		return to_year;
	}

	public void setTo_year(int to_year) {
		this.to_year = to_year;
	}

	public String getJob_descrption() {
		return Job_descrption;
	}

	public void setJob_descrption(String job_descrption) {
		Job_descrption = job_descrption;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public User_details getUser_details() {
		return user_details;
	}

	public void setUser_details(User_details user_details) {
		this.user_details = user_details;
	}

	public Designations getDesignation() {
		return designation;
	}

	public void setDesignation(Designations designation) {
		this.designation = designation;
	}
	
	

}
