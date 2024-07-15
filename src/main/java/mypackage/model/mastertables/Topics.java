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
@Table(name="tbltopics")
public class Topics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int topic_id;
	@Column(unique=true,nullable = false) 
	private String topic_name;
	@Column(columnDefinition = "integer default 0")
	private int flag;

	@OneToMany(mappedBy = "topics",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("topics")
	private Set<Topic_contents>topic_content;
	
	
	public Topics() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Topics(int topic_id, String topic_name, int flag) {
		super();
		this.topic_id = topic_id;
		this.topic_name = topic_name;
		this.flag = flag;
	}
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public String getTopic_name() {
		return topic_name;
	}
	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
	
}
