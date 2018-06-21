package nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.prestatiesysteem.persistentie.PostgresBaseDao;
import nl.hu.ipass.prestatiesysteem.persistentie.dao.CardioPresatieDao;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.CardioPrestatie;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Oefening;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Sporter;

public class CardioPrestatieDaoPostgresImpl extends PostgresBaseDao implements CardioPresatieDao {
	
	//Deze methode insert een cardio prestatie naar de database
	@Override
	public boolean save(CardioPrestatie cardioprestatie) {
		boolean resultaat = false;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO cardio_prestatie VALUES (nextval('cardioSQ'), ?, ?, current_date, ?, ?, ?)");
			pstmt.setInt(1, cardioprestatie.getSporter().getSportersnummer());
			pstmt.setInt(2, cardioprestatie.getOefening().getOefeningnummer());
			pstmt.setInt(3, cardioprestatie.getSessieduur());
			pstmt.setInt(4, cardioprestatie.getAfstand());
			pstmt.setInt(5, cardioprestatie.getSnelheid());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				resultaat = true;
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return resultaat;
	}
	
	//Deze methode zoekt alle cardioprestaties van een sporter op via zijn sportersnummer
	@Override
	public List<CardioPrestatie> findAllById(int sportersnummer) {
		List<CardioPrestatie> results = new ArrayList<CardioPrestatie>();
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM cardio_prestatie cp, sporter s, oefening o WHERE cp.sportersnummer = s.sportersnummer AND cp.oefeningnummer = o.oefeningnummer AND s.sportersnummer = " + sportersnummer);
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
				int sessieduur = dbResultSet.getInt("sessieduur");
				int afstand = dbResultSet.getInt("afstand");
				int snelheid = dbResultSet.getInt("snelheid");
				CardioPrestatie newCardioPrestatie = new CardioPrestatie(prestatienummer, desporter, newOefening, datum, sessieduur, afstand, snelheid);
				results.add(newCardioPrestatie);
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return results;
	}
	
	//deze methode update de database wanneer er waardes van attributen veranderd moeten worden
	@Override
	public boolean update(CardioPrestatie cardioprestatie) {
		boolean resultaat = false;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("UPDATE cardio_prestatie SET sessieduur = ?, afstand = ?, snelheid = ? WHERE prestatienummer = ?");
			pstmt.setInt(4, cardioprestatie.getPrestatienummer());
			pstmt.setInt(1, cardioprestatie.getSessieduur());
			pstmt.setInt(2, cardioprestatie.getAfstand());
			pstmt.setInt(3, cardioprestatie.getSnelheid());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				resultaat = true;
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return resultaat;
	}
	
	//deze methode verwijderd een cardioprestatie uit de database
	@Override
	public boolean delete(CardioPrestatie cardioprestatie) {
		boolean resultaat = false;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM cardio_prestatie WHERE prestatienummer = ?");
			pstmt.setInt(1, cardioprestatie.getPrestatienummer());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				resultaat = true;
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return resultaat;
	}
	
	//Deze methode zoekt een individuele cardioprestatie op bij zijn prestatienummer, deze methode wordt onder andere
	//gebruikt voor de update methode
	@Override
	public CardioPrestatie findByNummer(int prestatienummer) {
		CardioPrestatie prestatie = null;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM cardio_prestatie cp, sporter s, oefening o WHERE cp.sportersnummer = s.sportersnummer AND cp.oefeningnummer = o.oefeningnummer AND cp.prestatienummer = " + prestatienummer);
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
				int sessieduur = dbResultSet.getInt("sessieduur");
				int afstand = dbResultSet.getInt("afstand");
				int snelheid = dbResultSet.getInt("snelheid");
				prestatie = new CardioPrestatie(pprestatienummer, desporter, newOefening, datum, sessieduur, afstand, snelheid);
			}}catch(SQLException sqle) {sqle.printStackTrace();}
		
		return prestatie;
	}

}
