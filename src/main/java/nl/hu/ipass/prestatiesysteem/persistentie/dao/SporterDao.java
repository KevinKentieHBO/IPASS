package nl.hu.ipass.prestatiesysteem.persistentie.dao;

import java.sql.SQLException;

import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Sporter;

public interface SporterDao {
	public boolean save(Sporter sporter);
	public Sporter findByUsername(String username);
	public boolean update(Sporter sporter);
	public String findRoleForUser(String username, String password) throws SQLException;
}
