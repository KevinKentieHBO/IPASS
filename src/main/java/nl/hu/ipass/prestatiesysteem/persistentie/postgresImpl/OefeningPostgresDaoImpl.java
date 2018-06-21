package nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.ipass.prestatiesysteem.persistentie.PostgresBaseDao;
import nl.hu.ipass.prestatiesysteem.persistentie.dao.OefeningDao;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Oefening;

public class OefeningPostgresDaoImpl  extends PostgresBaseDao implements OefeningDao {
	
	//deze methode geeft een lijst van alle oefeningen die in de database zijn te vinden
	@Override
	public List<Oefening> findAll() {
		List<Oefening> results = new ArrayList<Oefening>();
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM oefening");
			ResultSet dbResultSet = pstmt.executeQuery();
			while(dbResultSet.next()) {
				int oefeningnummer = dbResultSet.getInt("oefeningnummer");
				String oefeningnaam = dbResultSet.getString("oefeningnaam");
				String oefeningtype = dbResultSet.getString("oefeningtype");
				int intensiteit = dbResultSet.getInt("intensiteit");
				int calorieverbranding = dbResultSet.getInt("calorie_verbranding");
				String afbeelding = dbResultSet.getString("afbeelding");
				Oefening newOefening = new Oefening(oefeningnummer,oefeningnaam,oefeningtype,intensiteit,calorieverbranding,afbeelding);
				results.add(newOefening);
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return results;
	}
	
	//Deze methode geeft een individuele oefening uit de database door hem te zoeken bij de oefeningnaam
	public Oefening findByNaam(String naam) {
		Oefening o = null;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM oefening WHERE oefeningnaam = ?");
			pstmt.setString(1, naam);
			ResultSet dbResultSet = pstmt.executeQuery();
			while(dbResultSet.next()) {
				int oefeningnummer = dbResultSet.getInt("oefeningnummer");
				String oefeningnaam = dbResultSet.getString("oefeningnaam");
				String oefeningtype = dbResultSet.getString("oefeningtype");
				int intensiteit = dbResultSet.getInt("intensiteit");
				int calorieverbranding = dbResultSet.getInt("calorie_verbranding");
				String afbeelding = dbResultSet.getString("afbeelding");
				o = new Oefening(oefeningnummer,oefeningnaam,oefeningtype,intensiteit,calorieverbranding,afbeelding);
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return o;
	}
}
