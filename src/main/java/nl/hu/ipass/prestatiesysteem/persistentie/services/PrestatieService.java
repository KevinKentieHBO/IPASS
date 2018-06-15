package nl.hu.ipass.prestatiesysteem.persistentie.services;

import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.prestatiesysteem.persistentie.objecten.CardioPrestatie;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.GewichtPrestatie;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Oefening;
import nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl.CardioPrestatieDaoPostgresImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl.GewichtPrestatieDaoPostgresImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl.OefeningPostgresDaoImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl.SporterDaoPostgresImpl;

public class PrestatieService {
	public CardioPrestatieDaoPostgresImpl Cardiodao = new CardioPrestatieDaoPostgresImpl();
	public GewichtPrestatieDaoPostgresImpl Gewichtdao = new GewichtPrestatieDaoPostgresImpl();
	public OefeningPostgresDaoImpl Oefeningdao = new OefeningPostgresDaoImpl();
	public SporterDaoPostgresImpl Sporterdao = new SporterDaoPostgresImpl();
	
	public List<Oefening> getAllOefeningen(){
		return Oefeningdao.findAll();
	}
	
	public List<CardioPrestatie> getAllCardioPrestaties(int sportnummer){
		return Cardiodao.findAllById(sportnummer);
	}
	
	public GewichtPrestatie getGewichtPrestatie(int prestatienummer) {
		return Gewichtdao.findByNummer(prestatienummer);
	}
	
	public CardioPrestatie getCardioPrestatie(int prestatienummer) {
		return Cardiodao.findByNummer(prestatienummer);
	}
	
	public List<GewichtPrestatie> getAllGewichtPrestaties(int sportersnummer){
		return Gewichtdao.findAllById(sportersnummer);
	}
	
	public boolean deleteCardioPrestatie(int prestatienummer) {
		boolean resultaat = false;
		CardioPrestatie c = Cardiodao.findByNummer(prestatienummer);
		if (c != null) {
			resultaat = Cardiodao.delete(c);
		} else {
			throw new IllegalArgumentException("Nummer bestaat niet!");
		}
		return resultaat;
	}
	
	public boolean deleteGewichtPrestatie(int prestatienummer) {
		boolean resultaat = false;
		GewichtPrestatie g = Gewichtdao.findByNummer(prestatienummer);
		if (g != null) {
			resultaat = Gewichtdao.delete(g);
		} else {
			throw new IllegalArgumentException("Nummer bestaat niet!");
		}
		return resultaat;
	}
	
	public CardioPrestatie voegCardioToe(String username, String oefeningnaam, int sessieduur,
			int afstand, int snelheid) {
		CardioPrestatie c = new CardioPrestatie(Sporterdao.findByUsername(username),Oefeningdao.findByNaam(oefeningnaam),sessieduur,afstand,snelheid);
		System.out.println(c.getSporter().getSportersnummer());
		if(Cardiodao.save(c)) {
			return c;
		}
		return c;	
	}
	
	public GewichtPrestatie voegGewichtToe(String username, String oefeningnaam, int volume,
			int sets, int reps) {
		GewichtPrestatie g = new GewichtPrestatie(Sporterdao.findByUsername(username),Oefeningdao.findByNaam(oefeningnaam),volume,sets,reps);
		System.out.println(g.getSporter().getSportersnummer());
		if(Gewichtdao.save(g)) {
			return g;
		}
		return g;	
	}
	
	public CardioPrestatie updateCardio(int prestatienummer, int sessieduur, int snelheid, int afstand) {
		CardioPrestatie c = Cardiodao.findByNummer(prestatienummer);
		c.setAfstand(afstand);
		c.setSessieduur(sessieduur);
		c.setSnelheid(snelheid);
		if(Cardiodao.update(c)) {
			return Cardiodao.findByNummer(prestatienummer);
		}
		return c;
	}
	
	public GewichtPrestatie updateGewicht(int prestatienummer, int volume, int sets, int reps) {
		GewichtPrestatie g = Gewichtdao.findByNummer(prestatienummer);
		g.setVolume(volume);
		g.setSets(sets);
		g.setReps(reps);
		if(Gewichtdao.update(g)) {
			return Gewichtdao.findByNummer(prestatienummer);
		}
		return g;
	}

}
