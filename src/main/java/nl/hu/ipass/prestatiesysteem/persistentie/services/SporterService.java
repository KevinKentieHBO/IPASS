package nl.hu.ipass.prestatiesysteem.persistentie.services;

import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.prestatiesysteem.persistentie.objecten.CardioPrestatie;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.GewichtPrestatie;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Oefening;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Sporter;
import nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl.CardioPrestatieDaoPostgresImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl.GewichtPrestatieDaoPostgresImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl.OefeningPostgresDaoImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl.SporterDaoPostgresImpl;

public class SporterService {
	public CardioPrestatieDaoPostgresImpl Cardiodao = new CardioPrestatieDaoPostgresImpl();
	public GewichtPrestatieDaoPostgresImpl Gewichtdao = new GewichtPrestatieDaoPostgresImpl();
	public OefeningPostgresDaoImpl Oefeningdao = new OefeningPostgresDaoImpl();
	public SporterDaoPostgresImpl Sporterdao = new SporterDaoPostgresImpl();
	
	public Sporter getSporter(String username){
		return Sporterdao.findByUsername(username);
	}

}
