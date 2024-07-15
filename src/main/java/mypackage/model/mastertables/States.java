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
@Table(name = "tblstate")
public class States {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int state_id;
	
	@Column(unique=true,nullable = false) 
	private String State_name;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@OneToMany(mappedBy = "state",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("state")
	private Set<Cities>city;
	
	
	public States() {
		super();
		// TODO Auto-generated constructor stub
	}
	public States(int state_id, String state_name, int flag) {
		super();
		this.state_id = state_id;
		State_name = state_name;
		this.flag = flag;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public String getState_name() {
		return State_name;
	}
	public void setState_name(String state_name) {
		State_name = state_name;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
