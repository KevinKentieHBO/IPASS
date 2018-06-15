package nl.hu.ipass.prestatiesysteem.persistentie.dao;

import java.util.List;

import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Oefening;

public interface OefeningDao {
	public List<Oefening> findAll();
	public Oefening findByNaam(String naam);
}
