package mypackage.model.Updates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblupdates")
public class Updates {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int update_id;
	@Column(nullable = false)
	private String update_date;
	private String update_title;
	private String update_description;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	public Updates() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Updates(int update_id, String update_date, String update_title, String update_description, int flag) {
		super();
		this.update_id = update_id;
		this.update_date = update_date;
		this.update_title = update_title;
		this.update_description = update_description;
		this.flag = flag;
	}
	public int getUpdate_id() {
		return update_id;
	}
	public void setUpdate_id(int update_id) {
		this.update_id = update_id;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getUpdate_title() {
		return update_title;
	}
	public void setUpdate_title(String update_title) {
		this.update_title = update_title;
	}
	public String getUpdate_description() {
		return update_description;
	}
	public void setUpdate_description(String update_description) {
		this.update_description = update_description;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}
