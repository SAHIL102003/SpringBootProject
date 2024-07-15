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

import mypackage.model.Codes.Code_posts;
@Entity
@Table(name = "tbltopic_contents")
public class Topic_contents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int content_id;
	@Column(unique=true,nullable = false) 
	private String content_name;
	@Column(columnDefinition = "integer default 0")
	private int flag;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "topic_id",nullable = false)
	private Topics topics;
	
	@OneToMany(mappedBy = "topic_content",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("topic_content")
	private Set<Code_posts>code_post;
	
	public Topic_contents() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Topic_contents(int content_id, String content_name, int flag, Topics topics) {
		super();
		this.content_id = content_id;
		this.content_name = content_name;
		this.flag = flag;
		this.topics = topics;
	}

	public int getContent_id() {
		return content_id;
	}

	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}

	public String getContent_name() {
		return content_name;
	}

	public void setContent_name(String content_name) {
		this.content_name = content_name;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Topics getTopics() {
		return topics;
	}

	public void setTopics(Topics topics) {
		this.topics = topics;
	}

	
}
