package mypackage.Services.Mastertable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Cities;
import mypackage.model.mastertables.States;
import mypackage.repositories.Mastertables.IStates_repositories;
import mypackage.repositories.Mastertables.Icity_repository;

@Service
public class CitiesServices {

	@Autowired
	Icity_repository cityrepo;
	@Autowired
	IStates_repositories staterepo;
	
	public List<Cities>Getallcities(){
		List<Cities>lst=new ArrayList<Cities>();
		for(Cities c:cityrepo.findAll())
		{
			if(c.getFlag()==0)
			{
				States st=(States)staterepo.findById(c.getState().getState_id()).get();
				States s=new States(st.getState_id(),st.getState_name(), 0);
				Cities ct=new Cities(c.getCity_id(), c.getCity_name(), c.getFlag(), s);
				lst.add(ct);
			}
		}
		return lst;
	}
	public List<Cities>GetStateWiseCities(int state_id){
		List<Cities>lst=new ArrayList<Cities>();
		for(Cities c:cityrepo.findAll())
		{
			if(c.getFlag()==0)
			{
				if(c.getState().getState_id()==state_id)
				{
				States st=(States)staterepo.findById(c.getState().getState_id()).get();
				States s=new States(st.getState_id(),st.getState_name(), 0);
				Cities ct=new Cities(c.getCity_id(), c.getCity_name(), c.getFlag(), s);
				lst.add(ct);
			}
				}
		}
		return lst;
	}
	
	public void AddCities(Cities c)
	{
		cityrepo.save(c);
	}
	
	public void UpdateCities(Cities c)
	{
		cityrepo.save(c);
	}
	
	public Cities Getbyid(int id)
	{
	
		Cities c=(Cities)cityrepo.findById(id).get();
		
		States s=new States(c.getState().getState_id(), null, 0);
		Cities ct=new Cities(c.getCity_id(), c.getCity_name(), c.getFlag(), s);
		return(ct);
		
	}
	
	public void DeleteCity(int id)
	{
		Cities ct=cityrepo.findById(id).get();
		ct.setFlag(1);
		cityrepo.save(ct);
	}
	
	public void RestoreCity(int id)
	{
		Cities ct=cityrepo.findById(id).get();
		ct.setFlag(0);
		cityrepo.save(ct);
	}
}
