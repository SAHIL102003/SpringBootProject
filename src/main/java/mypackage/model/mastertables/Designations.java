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
import mypackage.model.user.Experience_Details;

@Entity
@Table(name = "tbldesignations")
public class Designations {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int designation_id;
	
	@Column(unique=true,nullable = false) 
	private String designation;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@OneToMany(mappedBy = "designation",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("designation")
	private Set<Experience_Details>experince_details;
	
	
	public Designations() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Designations(int designation_id, String designation, int flag) {
		super();
		this.designation_id = designation_id;
		this.designation = designation;
		this.flag = flag;
	}
	public int getDesignation_id() {
		return designation_id;
	}
	public void setDesignation_id(int designation_id) {
		this.designation_id = designation_id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
