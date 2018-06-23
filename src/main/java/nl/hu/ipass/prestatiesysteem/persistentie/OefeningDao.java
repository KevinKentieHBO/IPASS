package nl.hu.ipass.prestatiesysteem.persistentie;

import java.util.List;

import nl.hu.ipass.prestatiesysteem.domein.Oefening;

public interface OefeningDao {
	//Opmerkingen over deze methodes staan in de OefeningDaoPostgresImpl
	public List<Oefening> findAll();
	public Oefening findByNaam(String naam);
}
