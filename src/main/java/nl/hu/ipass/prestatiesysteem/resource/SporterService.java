package nl.hu.ipass.prestatiesysteem.resource;

import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.prestatiesysteem.domein.CardioPrestatie;
import nl.hu.ipass.prestatiesysteem.domein.GewichtPrestatie;
import nl.hu.ipass.prestatiesysteem.domein.Oefening;
import nl.hu.ipass.prestatiesysteem.domein.Sporter;
import nl.hu.ipass.prestatiesysteem.persistentie.CardioPrestatieDaoPostgresImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.GewichtPrestatieDaoPostgresImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.OefeningPostgresDaoImpl;
import nl.hu.ipass.prestatiesysteem.persistentie.SporterDaoPostgresImpl;

public class SporterService {
	public CardioPrestatieDaoPostgresImpl Cardiodao = new CardioPrestatieDaoPostgresImpl();
	public GewichtPrestatieDaoPostgresImpl Gewichtdao = new GewichtPrestatieDaoPostgresImpl();
	public OefeningPostgresDaoImpl Oefeningdao = new OefeningPostgresDaoImpl();
	public SporterDaoPostgresImpl Sporterdao = new SporterDaoPostgresImpl();
	
	//Geeft een sporter gezocht met zijn username/email
	public Sporter getSporter(String username){
		return Sporterdao.findByUsername(username);
	}
	
	//voegt een sporter toe aan de database
	public boolean voegSporterToe(String voornaam, String tussenvoegsel, String achternaam, String email, String wachtwoord, int telefoonnummer, String geboortedatum, int gewicht) {
		Sporter s = new Sporter(voornaam, tussenvoegsel, achternaam, email, wachtwoord, telefoonnummer, geboortedatum, gewicht);
		return Sporterdao.save(s);
	}

}
