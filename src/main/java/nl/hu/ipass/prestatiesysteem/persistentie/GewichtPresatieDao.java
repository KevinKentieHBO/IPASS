package nl.hu.ipass.prestatiesysteem.persistentie;

import java.util.List;

import nl.hu.ipass.prestatiesysteem.domein.GewichtPrestatie;

public interface GewichtPresatieDao {
	//Opmerkingen over deze methodes staan in de GewichtPrestatieDaoPostgresImpl
	public boolean save(GewichtPrestatie gewichtprestatie);
	public List<GewichtPrestatie> findAllById(int sportersnummer);
	public boolean update(GewichtPrestatie gewichtprestatie);
	public boolean delete(GewichtPrestatie gewichtprestatie);
	public GewichtPrestatie findByNummer(int prestatienummer);
}
