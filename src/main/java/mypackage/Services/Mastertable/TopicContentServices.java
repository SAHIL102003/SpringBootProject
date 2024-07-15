package mypackage.Services.Mastertable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Topic_contents;
import mypackage.model.mastertables.Topics;
import mypackage.repositories.Mastertables.ITopiccontent_repositories;
import mypackage.repositories.Mastertables.iTopics_repositories;

@Service
public class TopicContentServices {

	@Autowired
	ITopiccontent_repositories topiccontentrepo;
	@Autowired
	iTopics_repositories topicrepo;
	
	public List<Topic_contents>getalltopiccontent()
	{
		List<Topic_contents>lst=new ArrayList<Topic_contents>();
		for(Topic_contents t:topiccontentrepo.findAll())
		{
			if(t.getFlag()==0)
			{
				Topics tp=(Topics)topicrepo.findById(t.getTopics().getTopic_id()).get();
				Topics to=new Topics(tp.getTopic_id(), tp.getTopic_name(), 0);
				Topic_contents ts=new Topic_contents(t.getContent_id(), t.getContent_name(), t.getFlag(), to);
				lst.add(ts);
			}
		}
		return lst;
	}
	public List<Topic_contents>getTopicWiseContents(int topic_id)
	{
		List<Topic_contents>lst=new ArrayList<Topic_contents>();
		for(Topic_contents t:topiccontentrepo.findAll())
		{
			if(t.getFlag()==0)
			{
				if(t.getTopics().getTopic_id()==topic_id)
				{
				Topics tp=(Topics)topicrepo.findById(t.getTopics().getTopic_id()).get();
				Topics to=new Topics(tp.getTopic_id(), tp.getTopic_name(), 0);
				Topic_contents ts=new Topic_contents(t.getContent_id(), t.getContent_name(), t.getFlag(), to);
				lst.add(ts);
			}
				}
		}
		return lst;
	}
	public void Addtopiccontent(Topic_contents t)
	{
		topiccontentrepo.save(t);
	}
	
	public void Updatetopiccontent(Topic_contents t)
	{
		topiccontentrepo.save(t);
	}
	
	public Topic_contents gettopiccontentbyid(int id)
	{
		Topic_contents t=(Topic_contents)topiccontentrepo.findById(id).get();
		Topics to=new Topics(t.getTopics().getTopic_id(), null, 0);
		Topic_contents ts=new Topic_contents(t.getContent_id(), t.getContent_name(), t.getFlag(), to);
		return (ts);
		
	}
	
	public void Deletetopicontent(int id)
	{
		Topic_contents t=topiccontentrepo.findById(id).get();
		t.setFlag(1);
		topiccontentrepo.save(t);
	}
	public void Restoretopicontent(int id)
	{
		Topic_contents t=topiccontentrepo.findById(id).get();
		t.setFlag(0);
		topiccontentrepo.save(t);
	}
}
