package mypackage.model.mastertables;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mypackage.model.user.User_details;

@Entity
@Table(name = "tblgenders")
public class Genders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gender_id;
	@Column(unique=true,nullable = false) 
	private String gender;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@OneToMany(mappedBy = "gender",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("gender")
	private Set<User_details>userdetails;
	
	public Genders() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Genders(int gender_id, String gender, int flag) {
		super();
		this.gender_id = gender_id;
		this.gender = gender;
		this.flag = flag;
	}
	public int getGender_id() {
		return gender_id;
	}
	public void setGender_id(int gender_id) {
		this.gender_id = gender_id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
