package mypackage.model.mastertables;

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

import mypackage.model.user.User_qualification;

@Entity
@Table(name = "tblspecializations")
public class Specializations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int specialization_id;
	private String specialization_name;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "qualification_id",nullable = false)
	private Qualifications qualification;
	
	@OneToMany(mappedBy = "specilization",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("specilization")
	private Set<User_qualification>user_qualification;

	public int getSpecialization_id() {
		return specialization_id;
	}

	public void setSpecialization_id(int specialization_id) {
		this.specialization_id = specialization_id;
	}

	public String getSpecialization_name() {
		return specialization_name;
	}

	public void setSpecialization_name(String specialization_name) {
		this.specialization_name = specialization_name;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Qualifications getQualification() {
		return qualification;
	}

	public void setQualification(Qualifications qualification) {
		this.qualification = qualification;
	}

	public Specializations(int specialization_id, String specialization_name, int flag, Qualifications qualification) {
		super();
		this.specialization_id = specialization_id;
		this.specialization_name = specialization_name;
		this.flag = flag;
		this.qualification = qualification;
	}

	public Specializations() {
		super();
		// TODO Auto-generated constructor stub
	}


	}

