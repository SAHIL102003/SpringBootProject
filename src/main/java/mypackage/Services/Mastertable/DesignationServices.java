package mypackage.Services.Mastertable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Designations;
import mypackage.repositories.Mastertables.Idesignation_repositories;

@Service
public class DesignationServices {

	@Autowired
	Idesignation_repositories desrepo;
	
	public List<Designations>getalldes(){
		List<Designations>lst=new ArrayList<Designations>();
		for(Designations d:desrepo.findAll())
		{
			if(d.getFlag()==0)
			{
				Designations ds=new Designations(d.getDesignation_id(), d.getDesignation(), d.getFlag());
				lst.add(ds);
			}
		}
		return lst;
	}
	
	public void Adddesignation(Designations d)
	{
		desrepo.save(d);
	}
	public void updatedesignation(Designations d)
	{
		desrepo.save(d);
	}
	
	public Designations getbyid(int id)
	{
		Designations d=(Designations)desrepo.findById(id).get();
		
		Designations ds=new Designations(d.getDesignation_id(), d.getDesignation(), d.getFlag());
		return(ds);
	}
	
	public void Deletedesignation(int id)
	{
		Designations d=desrepo.findById(id).get();
		d.setFlag(1);
		desrepo.save(d);
	}
	public void Restoredesignation(int id)
	{
		Designations d=desrepo.findById(id).get();
		d.setFlag(0);
		desrepo.save(d);
	}
}
