package mypackage.Services.Mastertable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Topics;
import mypackage.repositories.Mastertables.iTopics_repositories;

@Service
public class TopicServices {

	
	@Autowired
	iTopics_repositories topicrepo;
	
	public List<Topics>getalltopics(){
		List<Topics>lst=new ArrayList<Topics>();
		for(Topics t:topicrepo.findAll())
		{
			if(t.getFlag()==0)
			{
				Topics ts=new Topics(t.getTopic_id(), t.getTopic_name(),t.getFlag());
				lst.add(ts);
			}
		}
		return lst;
	}
	
	public void Addtopics(Topics t)
	{
		topicrepo.save(t);
	}
	public void Updatetopics(Topics t)
	{
		topicrepo.save(t);
	}
	
	public Topics gettopicbyid(int id)
	{
		Topics t=(Topics)topicrepo.findById(id).get();
		Topics ts=new Topics(t.getTopic_id(), t.getTopic_name(), t.getFlag());
		return (ts);
	}
	
	public void Deletetopics(int id)
	{
		Topics t= topicrepo.findById(id).get();
		t.setFlag(1);
		topicrepo.save(t);
	}
	
	public void Restoretopics(int id)
	{
		Topics t= topicrepo.findById(id).get();
		t.setFlag(0);
		topicrepo.save(t);
	}
}
