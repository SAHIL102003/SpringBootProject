package mypackage.Services.Mastertable;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Roles;
import mypackage.repositories.Mastertables.IRoles_repositories;

@Service
public class RolesServices {

	@Autowired
	IRoles_repositories rolrepo;
	
	public List<Roles>getallroles(){
		List<Roles>lst=new ArrayList<Roles>();
		for(Roles r:rolrepo.findAll())
		{
			if(r.getFlag()==0)
			{
				Roles rs=new Roles(r.getRole_id(), r.getRole(), r.getFlag());
				lst.add(rs);
			}
		}
		return lst;
	}
	
	public void Addrole(Roles r)
	{
		rolrepo.save(r);
	}
	public void Updaterole(Roles r)
	{
		rolrepo.save(r);
	}
	
	public Roles getrolesbyid(int id)
	{
		Roles r=(Roles)rolrepo.findById(id).get();
		Roles rs=new Roles(r.getRole_id(),r.getRole(), r.getFlag());
		return(rs);
	}
	
	public void Deleterole(int id)
	{
		Roles r=rolrepo.findById(id).get();
		r.setFlag(1);
		rolrepo.save(r);
	}
	
	public void Restorerole(int id)
	{
		Roles r=rolrepo.findById(id).get();
		r.setFlag(0);
		rolrepo.save(r);
	}
}
