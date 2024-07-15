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

@Entity
@Table(name = "tblqualifications")
public class Qualifications {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int qualification_id;
	
	@Column(unique=true,nullable = false) 
	private String qualification_name;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@OneToMany(mappedBy = "qualification",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("qualification")
	private Set<Specializations>specialization;

	public int getQualification_id() {
		return qualification_id;
	}

	public void setQualification_id(int qualification_id) {
		this.qualification_id = qualification_id;
	}

	public String getQualification_name() {
		return qualification_name;
	}

	public void setQualification_name(String qualification_name) {
		this.qualification_name = qualification_name;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Qualifications(int qualification_id, String qualification_name, int flag) {
		super();
		this.qualification_id = qualification_id;
		this.qualification_name = qualification_name;
		this.flag = flag;
	}

	public Qualifications() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
