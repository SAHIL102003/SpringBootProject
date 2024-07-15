package mypackage.model.user;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import mypackage.model.mastertables.Genders;
import mypackage.model.mastertables.Locations;
import mypackage.model.mastertables.Roles;

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

@Entity
@Table(name = "tbluserdetails")
public class User_details {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	@Column(nullable = false)
	private String first_name;
	@Column(nullable = false)
	private String middle_name;
	@Column(nullable = false)
	private String last_name;
	@Column(nullable = false)
	private String local_address;
	@Column(nullable = false)
	private String birth_date;
	@Column(nullable = false)
	private String joining_date;
	private String user_photo;
	private String mobile_no;
	private String email_address;
	@Column(nullable = false,unique = true)
	private String user_name;
	@Column(columnDefinition = "integer default 0")
	private int is_premium;
	@Column(nullable = false)
	private String password;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "gender_id",nullable = false)
	private Genders gender;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "location_id",nullable = false)
	private Locations location;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "role_id",nullable = false)
	private Roles roles;
	
	@OneToMany(mappedBy = "user_details",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("user_details")
	private Set<User_qualification>user_qualification;

	public User_details() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User_details(int user_id, String first_name, String middle_name, String last_name, String local_address,
			String birth_date, String joining_date, String user_photo, String mobile_no, String email_address,
			String user_name, int is_premium, String password, int flag, Genders gender, Locations location,
			Roles roles) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.middle_name = middle_name;
		this.last_name = last_name;
		this.local_address = local_address;
		this.birth_date = birth_date;
		this.joining_date = joining_date;
		this.user_photo = user_photo;
		this.mobile_no = mobile_no;
		this.email_address = email_address;
		this.user_name = user_name;
		this.is_premium = is_premium;
		this.password = password;
		this.flag = flag;
		this.gender = gender;
		this.location = location;
		this.roles = roles;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getLocal_address() {
		return local_address;
	}

	public void setLocal_address(String local_address) {
		this.local_address = local_address;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getJoining_date() {
		return joining_date;
	}

	public void setJoining_date(String joining_date) {
		this.joining_date = joining_date;
	}

	public String getUser_photo() {
		return user_photo;
	}

	public void setUser_photo(String user_photo) {
		this.user_photo = user_photo;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getIs_premium() {
		return is_premium;
	}

	public void setIs_premium(int is_premium) {
		this.is_premium = is_premium;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Genders getGender() {
		return gender;
	}

	public void setGender(Genders gender) {
		this.gender = gender;
	}

	public Locations getLocation() {
		return location;
	}

	public void setLocation(Locations location) {
		this.location = location;
	}

	public Roles getRoles() {
		return roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	
	
	
}
