package klu.model;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import klu.repository.PetsRepository;
@Service
public class PetsManager 
{
	@Autowired
	PetsRepository JR;
	
	public String insertJob(Pets J) 
	{
		try
		{
			JR.save(J);
			return "200:: new pet added Successfully";
		}
		catch (Exception e) 
		{
			return "401::"+e.getMessage();
			
		}
	}
	public String readPets()
	{
		try 
		{
			List<Pets> PetList=JR.findAll();
			return new GsonBuilder().create().toJson(PetList);
			
		} 
		catch (Exception e) 
		{
			return "401::"+e.getMessage();
		}
	}
	public String getPetDetaisbyId(Long id) 
	{
		try 
		{
			Pets J=JR.findById(id).get();
			return new GsonBuilder().create().toJson(J);
		} 
		catch (Exception e) 
		{
			return "401::"+e.getMessage();
		}
		
	}
	public String updatePet(Pets J)
	{
		try 
		{
			JR.save(J);
			return "200::Pet details are updated:";
		} 
		catch (Exception e) 
		{
			return "401::"+e.getMessage();
		}
	}
	public String deletePet(Long id)
	{
		try 
		{
			JR.deleteById(id);
			return "200::Pet details are deleted:";
		} 
		catch (Exception e) 
		{
			return "401::"+e.getMessage();
		}
	}

}
