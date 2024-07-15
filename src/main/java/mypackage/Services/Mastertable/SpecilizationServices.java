package mypackage.Services.Mastertable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mypackage.model.mastertables.Qualifications;
import mypackage.model.mastertables.Specializations;
import mypackage.repositories.Mastertables.IQualification_repositories;
import mypackage.repositories.Mastertables.ISpecialization_repositories;

@Service
public class SpecilizationServices {

	@Autowired
	ISpecialization_repositories specialrepo;
	@Autowired
	IQualification_repositories qualirepo;
	
	public List<Specializations>getallspecial()
	{
		List<Specializations>lst=new ArrayList<Specializations>();
		for(Specializations s:specialrepo.findAll())
		{
			if(s.getFlag()==0)
			{
				Qualifications ql=(Qualifications)qualirepo.findById(s.getQualification().getQualification_id()).get();
				Qualifications q=new Qualifications(ql.getQualification_id(),ql.getQualification_name(), 0);
				Specializations sp=new Specializations(s.getSpecialization_id(), s.getSpecialization_name(), s.getFlag(), q);
				lst.add(sp);
			}
		}
		return lst;
	}
	public List<Specializations>getQualificationWiseSpecializations(int qualification_id)
	{
		List<Specializations>lst=new ArrayList<Specializations>();
		for(Specializations s:specialrepo.findAll())
		{
			if(s.getFlag()==0)
			{
				if(s.getQualification().getQualification_id()==qualification_id)
				{
				Qualifications ql=(Qualifications)qualirepo.findById(s.getQualification().getQualification_id()).get();
				Qualifications q=new Qualifications(ql.getQualification_id(),ql.getQualification_name(), 0);
				Specializations sp=new Specializations(s.getSpecialization_id(), s.getSpecialization_name(), s.getFlag(), q);
				lst.add(sp);
				}
			}
		}
		return lst;
	}
	
	public void Addspecialization(Specializations s)
	{
		specialrepo.save(s);
	}
	public void Updatespecilization(Specializations s)
	{
		specialrepo.save(s);
	}
	
	public Specializations getspecilizationbyid(int id)
	{
		Specializations s=(Specializations)specialrepo.findById(id).get();
		Qualifications q=new Qualifications(s.getQualification().getQualification_id(), null, 0);
		Specializations sp=new Specializations(s.getSpecialization_id(), s.getSpecialization_name(), s.getFlag(), q);
		return(sp);
		
	}
	
	public void Deletespecilization(int id)
	{
		Specializations s=specialrepo.findById(id).get();
		s.setFlag(1);
		specialrepo.save(s);
	}
	public void Restorespecilization(int id)
	{
		Specializations s=specialrepo.findById(id).get();
		s.setFlag(0);
		specialrepo.save(s);
	}
}
