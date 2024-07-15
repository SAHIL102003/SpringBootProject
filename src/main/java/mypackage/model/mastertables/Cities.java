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

@Entity
@Table(name = "tblcities")
public class Cities {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int city_id;
	private String city_name;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "state_id",nullable = false)
	private States state;

	@OneToMany(mappedBy = "city",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("city")
	private Set<Locations>location;
	
	public Cities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cities(int city_id, String city_name, int flag, States state) {
		super();
		this.city_id = city_id;
		this.city_name = city_name;
		this.flag = flag;
		this.state = state;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}
}
