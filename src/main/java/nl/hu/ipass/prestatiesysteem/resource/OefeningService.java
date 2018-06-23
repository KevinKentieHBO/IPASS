package nl.hu.ipass.prestatiesysteem.resource;

import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.prestatiesysteem.domein.CardioPrestatie;
import nl.hu.ipass.prestatiesysteem.domein.GewichtPrestatie;
import nl.hu.ipass.prestatiesysteem.domein.Oefening;
import nl.hu.ipass.prestatiesysteem.persistentie.CardioPrestatieDaoPostgresImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.GewichtPrestatieDaoPostgresImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.OefeningPostgresDaoImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.SporterDaoPostgresImpl;

public class OefeningService {
	public CardioPrestatieDaoPostgresImpl Cardiodao = new CardioPrestatieDaoPostgresImpl();
	public GewichtPrestatieDaoPostgresImpl Gewichtdao = new GewichtPrestatieDaoPostgresImpl();
	public OefeningPostgresDaoImpl Oefeningdao = new OefeningPostgresDaoImpl();
	public SporterDaoPostgresImpl Sporterdao = new SporterDaoPostgresImpl();
	
	//Zoekt alle oefeningen uit de database
	public List<Oefening> getAllOefeningen(){
		return Oefeningdao.findAll();
	}
	
	//Zoekt alle cardioprestaties van een individuele sporter
	public List<CardioPrestatie> getAllCardioPrestaties(int sportersnummer){
		return Cardiodao.findAllById(sportersnummer);
	}
	
	//Zoekt alle gewichtprestaties van een individuele sporter
	public List<GewichtPrestatie> getAllGewichtPrestaties(int sportersnummer){
		return Gewichtdao.findAllById(sportersnummer);
	}
	
	//Zoekt alle prestaties in een lijst van het type Object
	public List<Object> getAllPrestaties(int sportersnummer){
		List<Object> allePrestaties = new ArrayList<Object>(Cardiodao.findAllById(sportersnummer));
		allePrestaties.addAll(Gewichtdao.findAllById(sportersnummer));
		return allePrestaties;
	}
	
	//Delete een cardioprestatie wanneer er een cardioprestatie gevonden kan worden met het prestatienummer dat mee gegeven wordt
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
	
	//Delete een gewichtprestatie wanneer er een gewichtprestatie gevonden kan worden met het prestatienummer dat mee gegeven wordt
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
	
	//voegt een cardioprestatie toe aan de database
	public CardioPrestatie voegCardioToe(String username, String oefeningnaam, int sessieduur,
			int afstand, int snelheid) {
		CardioPrestatie c = new CardioPrestatie(Sporterdao.findByUsername(username),Oefeningdao.findByNaam(oefeningnaam),sessieduur,afstand,snelheid);
		System.out.println(c.getSporter().getSportersnummer());
		if(Cardiodao.save(c)) {
			return c;
		}
		return c;	
	}
	
	//voegt een gewichtprestatie toe aan de database
	public GewichtPrestatie voegGewichtToe(String username, String oefeningnaam, int volume,
			int sets, int reps) {
		GewichtPrestatie g = new GewichtPrestatie(Sporterdao.findByUsername(username),Oefeningdao.findByNaam(oefeningnaam),volume,sets,reps);
		System.out.println(g.getSporter().getSportersnummer());
		if(Gewichtdao.save(g)) {
			return g;
		}
		return g;	
	}
	
	//Update een cardio prestatie in de database
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
	
	//update een gewichtprestatie in de database
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
