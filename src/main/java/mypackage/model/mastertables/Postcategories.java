package mypackage.model.mastertables;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tblpostcategories")
public class Postcategories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int category_id;
	@Column(unique=true,nullable = false) 
	private String category_name;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	public Postcategories() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Postcategories(int category_id, String category_name, int flag) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.flag = flag;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
