package nl.hu.ipass.prestatiesysteem.persistentie.dao;

import java.util.List;

import nl.hu.ipass.prestatiesysteem.persistentie.objecten.CardioPrestatie;

public interface CardioPresatieDao {
	public boolean save(CardioPrestatie cardioprestatie);
	public boolean update(CardioPrestatie cardioprestatie);
	public boolean delete(CardioPrestatie cardioprestatie);
	public CardioPrestatie findByNummer(int prestatienummer);
	public List<CardioPrestatie> findAllById(int sportersnummer);
}
