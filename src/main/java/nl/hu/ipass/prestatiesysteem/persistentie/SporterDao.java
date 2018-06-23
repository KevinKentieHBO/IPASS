package nl.hu.ipass.prestatiesysteem.persistentie;

import java.sql.SQLException;

import nl.hu.ipass.prestatiesysteem.domein.Sporter;

public interface SporterDao {
	//Opmerkingen over deze methodes staan in de SporterDaoPostgresImpl
	public boolean save(Sporter sporter);
	public Sporter findByUsername(String username);
	public boolean update(Sporter sporter);
	public String findRoleForUser(String username, String password) throws SQLException;
}
