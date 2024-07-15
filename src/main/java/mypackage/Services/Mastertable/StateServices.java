package mypackage.Services.Mastertable;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.States;
import mypackage.repositories.Mastertables.IStates_repositories;

@Service
public class StateServices {

	@Autowired
	IStates_repositories staterepo;
	
	public List<States>GetallStates(){
		List<States>lst=new ArrayList<States>();
		for(States s:staterepo.findAll())
		{
			if(s.getFlag()==0)
			{
				States st=new States(s.getState_id(), s.getState_name(), s.getFlag());
				lst.add(st);
			}
		}
		return lst;
	}
	
	public void Addstate(States s)
	{
		
		s.setFlag(0);
		staterepo.save(s);
	}
	
	public void updatestate(States s) 
	{
		staterepo.save(s);
	}
	
	public States GetstateByID(int id)
	{
		States s=(States)staterepo.findById(id).get();
		
		States st=new States(s.getState_id(), s.getState_name(), s.getFlag());
		return(st);
		
	}
	
	public void DeleteStates(int id)
	{
		States st=staterepo.findById(id).get();
		st.setFlag(1);
		staterepo.save(st);
	}
	public void RestoreStates(int id)
	{
		States st=staterepo.findById(id).get();
		st.setFlag(0);
		staterepo.save(st);
	}
}
