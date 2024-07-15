package mypackage.Services.Mastertable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Qualifications;
import mypackage.repositories.Mastertables.IQualification_repositories;

@Service
public class QualificationServices {

	@Autowired
	IQualification_repositories qualirepo;
	
	public List<Qualifications>getallqualifications(){
		List<Qualifications>lst=new ArrayList<Qualifications>();
		for(Qualifications q:qualirepo.findAll())
		{
			if(q.getFlag()==0)
			{
				Qualifications qs=new Qualifications(q.getQualification_id(), q.getQualification_name(), q.getFlag());
				lst.add(qs);
			}
		}
		return lst;
	}
	
	public void Addqualification(Qualifications q)
	{
		qualirepo.save(q);
	}
	
	public void Updatequalification(Qualifications q)
	{
		qualirepo.save(q);
	}
	
	public Qualifications getqualificationbyid(int id)
	{
		Qualifications q=(Qualifications)qualirepo.findById(id).get();
		Qualifications qs=new Qualifications(q.getQualification_id(), q.getQualification_name(), q.getFlag());
		return(qs);
	}
	
	public void Deletequalification(int id)
	{
		Qualifications q=qualirepo.findById(id).get();
		q.setFlag(1);
		qualirepo.save(q);
	}
	public void Restorequalification(int id)
	{
		Qualifications q=qualirepo.findById(id).get();
		q.setFlag(0);
		qualirepo.save(q);
	}
	
}
