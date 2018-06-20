package nl.hu.ipass.prestatiesysteem.persistentie.postgresImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import nl.hu.ipass.prestatiesysteem.persistentie.PostgresBaseDao;
import nl.hu.ipass.prestatiesysteem.persistentie.dao.SporterDao;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Sporter;

public class SporterDaoPostgresImpl extends PostgresBaseDao implements SporterDao {
	
	@Override
	public Sporter findByUsername(String username) {
		Sporter desporter = null;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM sporter WHERE email = '" + username +"'");
			ResultSet dbResultSet = pstmt.executeQuery();
			while(dbResultSet.next()) {
			int sportersnummer = dbResultSet.getInt("sportersnummer");
			String voornaam = dbResultSet.getString("voornaam");
			String tussenvoegsel = dbResultSet.getString("tussenvoegsel");
			String achternaam = dbResultSet.getString("achternaam");
			String email = dbResultSet.getString("email");
			String wachtwoord = dbResultSet.getString("wachtwoord");
			int telefoonnummer = dbResultSet.getInt("telefoonnummer");
			String geboortedatum = dbResultSet.getString("geboortedatum");
			int gewicht = dbResultSet.getInt("gewicht");
			desporter = new Sporter(sportersnummer,voornaam,tussenvoegsel,achternaam,email,wachtwoord,telefoonnummer,geboortedatum,gewicht);
			}
			}catch(SQLException sqle) {sqle.printStackTrace();}
		return desporter;
	}
	
	@Override
	public boolean save(Sporter sporter) {
		boolean resultaat = false;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO sporter VALUES (nextval('sportersq'), ?, ?, ?, ?, ?, ?, ?, ?,'user')");
			pstmt.setString(1, sporter.getVoornaam());
			pstmt.setString(2, sporter.getTussenvoegsel());
			pstmt.setString(3, sporter.getAchternaam());
			pstmt.setString(4, sporter.getE_mail());
			pstmt.setString(5, sporter.getWachtwoord());
			pstmt.setInt(6, sporter.getTelefoonnummer());
			pstmt.setString(7, sporter.getGeboortedatum());
			pstmt.setInt(8, sporter.getGewicht());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				resultaat = true;
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return resultaat;
	}
	
	@Override
	public boolean update(Sporter sporter) {
		boolean resultaat = false;
		try(Connection con = super.getConnection()){
			PreparedStatement pstmt = con.prepareStatement("UPDATE sporter SET voornaam = ?, tussenvoegsel = ?, achternaam = ?, email = ?,wachtwoord = ?,telefoonnummer = ?,geboortedatum = ?,gewicht = ?)");
			pstmt.setString(1, sporter.getVoornaam());
			pstmt.setString(2, sporter.getTussenvoegsel());
			pstmt.setString(3, sporter.getAchternaam());
			pstmt.setString(4, sporter.getE_mail());
			pstmt.setString(5, sporter.getWachtwoord());
			pstmt.setInt(6, sporter.getTelefoonnummer());
			pstmt.setString(7, sporter.getGeboortedatum());
			pstmt.setInt(8, sporter.getGewicht());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				resultaat = true;
			}
		}catch(SQLException sqle) {sqle.printStackTrace();}
		return resultaat;
	}
	
	@Override
	public String findRoleForUser(String username, String password) throws SQLException {
		String rol = null;
		try
			(Connection con = super.getConnection()){
				Statement stmt = con.createStatement();
				ResultSet dbResultSet = stmt.executeQuery("SELECT ROLE FROM sporter WHERE email ='" + username + "' AND wachtwoord ='" + password + "'");
				while (dbResultSet.next()) {
					String dbRol = dbResultSet.getString("role");
					
					rol = dbRol;
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		System.out.println(rol);
		return rol;
	}

}
