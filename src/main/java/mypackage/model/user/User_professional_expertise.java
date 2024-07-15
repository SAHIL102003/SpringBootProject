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
@Table(name = "tbluser_professional_expertise")
public class User_professional_expertise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expertise_id;
	@Column(length = 500)
	private String description;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "user_id",nullable = false)
	private User_details user_details;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "specialization_id",nullable = false)
	private Specializations specilization;

	public User_professional_expertise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User_professional_expertise(int expertise_id, String description, int flag, User_details user_details,
			Specializations specilization) {
		super();
		this.expertise_id = expertise_id;
		this.description = description;
		this.flag = flag;
		this.user_details = user_details;
		this.specilization = specilization;
	}

	public int getExpertise_id() {
		return expertise_id;
	}

	public void setExpertise_id(int expertise_id) {
		this.expertise_id = expertise_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
