package mypackage.Services.Mastertable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Cities;
import mypackage.model.mastertables.Locations;
import mypackage.repositories.Mastertables.ILocations_repositories;
import mypackage.repositories.Mastertables.Icity_repository;

@Service
public class LocationsServices {

	@Autowired
	ILocations_repositories locationrepo;
	@Autowired
	Icity_repository cityrepo;
	public List<Locations>getalllocation()
	{
		List<Locations>lst=new ArrayList<Locations>();
		for(Locations l:locationrepo.findAll())
		{
			if(l.getFlag()==0)
			{
				Cities ct=(Cities)cityrepo.findById(l.getCity().getCity_id()).get();
				Cities c=new Cities(ct.getCity_id(),ct.getCity_name(), 0, null);
				Locations ls=new Locations(l.getLocation_id(), l.getLocation_name(), l.getFlag(), c);
				lst.add(ls);
			}
		}
		return lst;
	}
	public List<Locations>getCityWiseLocations(int city_id)
	{
		List<Locations>lst=new ArrayList<Locations>();
		for(Locations l:locationrepo.findAll())
		{
			if(l.getFlag()==0)
			{
				if(l.getCity().getCity_id()==city_id)
				{
				Cities ct=(Cities)cityrepo.findById(l.getCity().getCity_id()).get();
				Cities c=new Cities(ct.getCity_id(),ct.getCity_name(), 0, null);
				Locations ls=new Locations(l.getLocation_id(), l.getLocation_name(), l.getFlag(), c);
				lst.add(ls);
			}
			}
		}
		return lst;
	}
	public void Addlocation(Locations l)
	{
		locationrepo.save(l);
	}
	public void Updatelocation(Locations l)
	{
		locationrepo.save(l);
	}
	
	public Locations getbyid(int id)
	{
		Locations l=(Locations)locationrepo.findById(id).get();
		Cities c=new Cities(l.getCity().getCity_id(), null, 0, null);
		Locations ls=new Locations(l.getLocation_id(), l.getLocation_name(), l.getFlag(), c);
		return(ls);
	}
	
	public void Deletelocation(int id)
	{
		Locations l=locationrepo.findById(id).get();
		l.setFlag(1);
		locationrepo.save(l);
	}
	
	public void Restorelocation(int id)
	{
		Locations l=locationrepo.findById(id).get();
		l.setFlag(0);
		locationrepo.save(l);
	}
}
