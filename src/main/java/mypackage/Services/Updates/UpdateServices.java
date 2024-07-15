package mypackage.Services.Updates;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.Updates.Updates;
import mypackage.repositories.Updates.Iupdate_repositories;

@Service
public class UpdateServices {

	@Autowired
	Iupdate_repositories updaterepo;
	
	public List<Updates>getallupdates()
	{
		List<Updates>lst=new ArrayList<Updates>();
		for(Updates u:updaterepo.findAll())
		{
			if(u.getFlag()==0)
			{
				Updates us=new Updates(u.getUpdate_id(), u.getUpdate_date(), u.getUpdate_date(), u.getUpdate_description(), u.getFlag());
				lst.add(us);
			}
		}
		return lst;
	}
	
	public void Addupdates(Updates u)
	{
		updaterepo.save(u);
	}
	
	public void Updateupdates(Updates u)
	{
		updaterepo.save(u);
	}
	
	public Updates getupdatebyudateid(int id)
	{
		Updates u=(Updates)updaterepo.findById(id).get();
		
		Updates us=new Updates(u.getUpdate_id(), u.getUpdate_date(), u.getUpdate_date(), u.getUpdate_description(), u.getFlag());

		return (us);
	}
	
	public void Deleteupdate(int id)
	{
		Updates u=updaterepo.findById(id).get();
		u.setFlag(1);
		updaterepo.save(u);
	}

	public void Restoreupdate(int id)
	{
		Updates u=updaterepo.findById(id).get();
		u.setFlag(0);
		updaterepo.save(u);
	}
}
