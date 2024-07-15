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

import mypackage.model.user.User_details;

@Entity
@Table(name = "tbllocations")
public class Locations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int location_id;
	private String location_name;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "city_id",nullable = false)
	private Cities city;
	
	@OneToMany(mappedBy = "location",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("location")
	private Set<User_details>userdetails;

	public Locations() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Locations(int location_id, String location_name, int flag, Cities city) {
		super();
		this.location_id = location_id;
		this.location_name = location_name;
		this.flag = flag;
		this.city = city;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Cities getCity() {
		return city;
	}

	public void setCity(Cities city) {
		this.city = city;
	}
	
	
}
