package mypackage.Services.Mastertable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Genders;
import mypackage.repositories.Mastertables.IGender_repositories;

@Service
public class GenderServices {

	@Autowired
	IGender_repositories genderrepo;
	
	public List<Genders>getgender()
	{
		List<Genders>lst=new ArrayList<Genders>();
		for(Genders g:genderrepo.findAll())
		{
			if(g.getFlag()==0)
			{
				Genders gs=new Genders(g.getGender_id(), g.getGender(), g.getFlag());
				lst.add(gs);
			}
		}
		return lst;
	}
	
	public void Addgender(Genders g)
	{
		genderrepo.save(g);
	}
	
	public void Updategender(Genders g)
	{
		genderrepo.save(g);
	}
	public Genders getbyid(int id)
	{
		Genders g=(Genders)genderrepo.findById(id).get();
		Genders gs=new Genders(g.getGender_id(), g.getGender(), g.getFlag());
		return (gs);
	}
	
	public void DeleteGender(int id)
	{
		Genders d=genderrepo.findById(id).get();
		d.setFlag(1);
		genderrepo.save(d);
	}
	
	public void RestoreGender(int id)
	{
		Genders d=genderrepo.findById(id).get();
		d.setFlag(0);
		genderrepo.save(d);
	}
	
}
