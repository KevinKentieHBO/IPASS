package nl.hu.ipass.prestatiesysteem.persistentie.dao;

import java.util.List;

import nl.hu.ipass.prestatiesysteem.persistentie.objecten.GewichtPrestatie;

public interface GewichtPresatieDao {
	public boolean save(GewichtPrestatie gewichtprestatie);
	public List<GewichtPrestatie> findAllById(int sportersnummer);
	public boolean update(GewichtPrestatie gewichtprestatie);
	public boolean delete(GewichtPrestatie gewichtprestatie);
	public GewichtPrestatie findByNummer(int prestatienummer);
}
