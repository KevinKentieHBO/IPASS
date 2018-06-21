package nl.hu.ipass.prestatiesysteem.persistentie.dao;

import java.util.List;

import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Oefening;

public interface OefeningDao {
	//Opmerkingen over deze methodes staan in de OefeningDaoPostgresImpl
	public List<Oefening> findAll();
	public Oefening findByNaam(String naam);
}
