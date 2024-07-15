package mypackage.model.Admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbladmin_details")
public class Admin_details {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private int admin_id;
	private String full_name;
	@Column(nullable = false,unique = true)
	private String admin_unique_name;
	public Admin_details() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin_details(int admin_id, String full_name, String admin_unique_name, String password, int flag) {
		super();
		this.admin_id = admin_id;
		this.full_name = full_name;
		this.admin_unique_name = admin_unique_name;
		this.password = password;
		this.flag = flag;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getAdmin_unique_name() {
		return admin_unique_name;
	}
	public void setAdmin_unique_name(String admin_unique_name) {
		this.admin_unique_name = admin_unique_name;
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
	@Column(nullable = false)
	private String password;
	private int flag;
	
	
	
}
