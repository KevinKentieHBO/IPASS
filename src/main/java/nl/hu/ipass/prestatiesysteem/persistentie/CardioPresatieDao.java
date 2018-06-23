package nl.hu.ipass.prestatiesysteem.persistentie;

import java.util.List;

import nl.hu.ipass.prestatiesysteem.domein.CardioPrestatie;

public interface CardioPresatieDao {
	//Opmerkingen over deze methodes staan in de CardioPrestatieDaoPostgresImpl
	public boolean save(CardioPrestatie cardioprestatie);
	public boolean update(CardioPrestatie cardioprestatie);
	public boolean delete(CardioPrestatie cardioprestatie);
	public CardioPrestatie findByNummer(int prestatienummer);
	public List<CardioPrestatie> findAllById(int sportersnummer);
}
