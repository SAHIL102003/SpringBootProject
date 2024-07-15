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
import mypackage.model.mastertables.Specializations;


@Entity
@Table(name = "tbluser_qualification")
public class User_qualification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_qualification_id;
	@Column(nullable = false)
	private String university;
	@Column(nullable = false)
	private int passing_year;
	private String medium;
	private float percentage;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "specialization_id",nullable = false)
	private Specializations specilization;

	public User_qualification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User_qualification(int user_qualification_id, String university, int passing_year, String medium,
			float percentage, int flag, User_details user_details, Specializations specilization) {
		super();
		this.user_qualification_id = user_qualification_id;
		this.university = university;
		this.passing_year = passing_year;
		this.medium = medium;
		this.percentage = percentage;
		this.flag = flag;
		this.user_details = user_details;
		this.specilization = specilization;
	}

	public int getUser_qualification_id() {
		return user_qualification_id;
	}

	public void setUser_qualification_id(int user_qualification_id) {
		this.user_qualification_id = user_qualification_id;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public int getPassing_year() {
		return passing_year;
	}

	public void setPassing_year(int passing_year) {
		this.passing_year = passing_year;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
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

	public Specializations getSpecilization() {
		return specilization;
	}

	public void setSpecilization(Specializations specilization) {
		this.specilization = specilization;
	}
	
	
}
