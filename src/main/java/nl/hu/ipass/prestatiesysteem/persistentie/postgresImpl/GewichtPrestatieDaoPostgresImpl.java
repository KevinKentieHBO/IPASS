package nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.prestatiesysteem.persistentie.PostgresBaseDao;
import nl.hu.ipass.prestatiesysteem.persistentie.dao.GewichtPresatieDao;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.GewichtPrestatie;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Oefening;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Sporter;

public class GewichtPrestatieDaoPostgresImpl extends PostgresBaseDao implements GewichtPresatieDao {

	//Deze methode insert een gewicht prestatie naar de database
	@Override
	public boolean save(GewichtPrestatie gewichtprestatie) {
		boolean resultaat = false;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO gewicht_prestatie VALUES (nextval('gewichtSQ'), ?, ?, current_date, ?, ?, ?)");
			pstmt.setInt(1, gewichtprestatie.getSporter().getSportersnummer());
			pstmt.setInt(2, gewichtprestatie.getOefening().getOefeningnummer());
			pstmt.setInt(3, gewichtprestatie.getVolume());
			pstmt.setInt(4, gewichtprestatie.getSets());
			pstmt.setInt(5, gewichtprestatie.getReps());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				resultaat = true;
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return resultaat;
	}

	//Deze methode zoekt alle gewichtprestaties van een sporter op via zijn sportersnummer
	@Override
	public List<GewichtPrestatie> findAllById(int sportersnummer) {
		List<GewichtPrestatie> results = new ArrayList<GewichtPrestatie>();
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM gewicht_prestatie gp, sporter s, oefening o WHERE gp.sportersnummer = s.sportersnummer AND gp.oefeningnummer = o.oefeningnummer AND s.sportersnummer = " + sportersnummer);
			ResultSet dbResultSet = pstmt.executeQuery();
			while(dbResultSet.next()) {
				int ssportersnummer = dbResultSet.getInt("sportersnummer");
				String voornaam = dbResultSet.getString("voornaam");
				String tussenvoegsel = dbResultSet.getString("tussenvoegsel");
				String achternaam = dbResultSet.getString("achternaam");
				String email = dbResultSet.getString("email");
				String wachtwoord = dbResultSet.getString("wachtwoord");
				int telefoonnummer = dbResultSet.getInt("telefoonnummer");
				String geboortedatum = dbResultSet.getString("geboortedatum");
				int gewicht = dbResultSet.getInt("gewicht");
				Sporter desporter = new Sporter(ssportersnummer,voornaam,tussenvoegsel,achternaam,email,wachtwoord,telefoonnummer,geboortedatum,gewicht);
				
				int oefeningnummer = dbResultSet.getInt("oefeningnummer");
				String oefeningnaam = dbResultSet.getString("oefeningnaam");
				String oefeningtype = dbResultSet.getString("oefeningtype");
				int intensiteit = dbResultSet.getInt("intensiteit");
				int calorieverbranding = dbResultSet.getInt("calorie_verbranding");
				String afbeelding = dbResultSet.getString("afbeelding");
				Oefening newOefening = new Oefening(oefeningnummer,oefeningnaam,oefeningtype,intensiteit,calorieverbranding,afbeelding);
				
				int prestatienummer = dbResultSet.getInt("prestatienummer");
				String datum = dbResultSet.getString("datum");
				int volume = dbResultSet.getInt("volume");
				int sets = dbResultSet.getInt("sets");
				int reps = dbResultSet.getInt("reps");
				GewichtPrestatie newCardioPrestatie = new GewichtPrestatie(prestatienummer, desporter, newOefening, datum, volume, sets, reps);
				results.add(newCardioPrestatie);
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return results;
	}

	//deze methode update de database wanneer er waardes van attributen veranderd moeten worden
	@Override
	public boolean update(GewichtPrestatie gewichtprestatie) {
		boolean resultaat = false;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("UPDATE gewicht_prestatie SET volume = ?, sets = ?, reps = ? WHERE prestatienummer = ?");
			pstmt.setInt(4, gewichtprestatie.getPrestatienummer());
			pstmt.setInt(1, gewichtprestatie.getVolume());
			pstmt.setInt(2, gewichtprestatie.getSets());
			pstmt.setInt(3, gewichtprestatie.getReps());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				resultaat = true;
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return resultaat;
	}

	//deze methode verwijderd een gewicht prestatie uit de database
	@Override
	public boolean delete(GewichtPrestatie gewichtprestatie) {
		boolean resultaat = false;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM gewicht_prestatie WHERE prestatienummer = ?");
			pstmt.setInt(1, gewichtprestatie.getPrestatienummer());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				resultaat = true;
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return resultaat;
	}
	
	//Deze methode zoekt een individuele gewicht prestatie op bij zijn prestatienummer, deze methode wordt onder andere
	//gebruikt voor de update methode
	@Override
	public GewichtPrestatie findByNummer(int prestatienummer) {
		GewichtPrestatie prestatie = null;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM gewicht_prestatie cp, sporter s, oefening o WHERE cp.sportersnummer = s.sportersnummer AND cp.oefeningnummer = o.oefeningnummer AND cp.prestatienummer = " + prestatienummer);
			ResultSet dbResultSet = pstmt.executeQuery();
			while(dbResultSet.next()) {
				int ssportersnummer = dbResultSet.getInt("sportersnummer");
				String voornaam = dbResultSet.getString("voornaam");
				String tussenvoegsel = dbResultSet.getString("tussenvoegsel");
				String achternaam = dbResultSet.getString("achternaam");
				String email = dbResultSet.getString("email");
				String wachtwoord = dbResultSet.getString("wachtwoord");
				int telefoonnummer = dbResultSet.getInt("telefoonnummer");
				String geboortedatum = dbResultSet.getString("geboortedatum");
				int gewicht = dbResultSet.getInt("gewicht");
				Sporter desporter = new Sporter(ssportersnummer,voornaam,tussenvoegsel,achternaam,email,wachtwoord,telefoonnummer,geboortedatum,gewicht);
				
				int oefeningnummer = dbResultSet.getInt("oefeningnummer");
				String oefeningnaam = dbResultSet.getString("oefeningnaam");
				String oefeningtype = dbResultSet.getString("oefeningtype");
				int intensiteit = dbResultSet.getInt("intensiteit");
				int calorieverbranding = dbResultSet.getInt("calorie_verbranding");
				String afbeelding = dbResultSet.getString("afbeelding");
				Oefening newOefening = new Oefening(oefeningnummer,oefeningnaam,oefeningtype,intensiteit,calorieverbranding,afbeelding);
				
				int pprestatienummer = dbResultSet.getInt("prestatienummer");
				String datum = dbResultSet.getString("datum");
				int volume = dbResultSet.getInt("volume");
				int sets = dbResultSet.getInt("sets");
				int reps = dbResultSet.getInt("reps");
				prestatie = new GewichtPrestatie(pprestatienummer, desporter, newOefening, datum, volume, sets, reps);
			}}catch(SQLException sqle) {sqle.printStackTrace();}
		
		return prestatie;
	}

}
