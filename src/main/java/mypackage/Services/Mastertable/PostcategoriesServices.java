package mypackage.Services.Mastertable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Postcategories;
import mypackage.repositories.Mastertables.Ipostcategories_repositories;

@Service
public class PostcategoriesServices {

	@Autowired
	Ipostcategories_repositories postrepo;
	
	public List<Postcategories>getallpostcat(){
		List<Postcategories>lst=new ArrayList<Postcategories>();
		for(Postcategories p:postrepo.findAll())
		{
			if(p.getFlag()==0) 
			{
			Postcategories ps=new Postcategories(p.getCategory_id(), p.getCategory_name(), p.getFlag());
			lst.add(ps);
		}
	}
	return lst;
	}
	
	public void Addpostcategories(Postcategories p)
	{
		postrepo.save(p);
	}
	
	public void updatecat(Postcategories p)
	{
		postrepo.save(p);
	}
	
	public Postcategories getbyid(int id)
	{
		Postcategories p=(Postcategories)postrepo.findById(id).get();
		Postcategories ps=new Postcategories(p.getCategory_id(), p.getCategory_name(), p.getFlag());
		return(ps);
	}
	
	public void Deletecat(int id)
	{
		Postcategories p=postrepo.findById(id).get();
		p.setFlag(1);
		postrepo.save(p);
	}
	
	public void Restorecat(int id)
	{
		Postcategories p=postrepo.findById(id).get();
		p.setFlag(0);
		postrepo.save(p);
	}
}
