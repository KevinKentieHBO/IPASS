package nl.hu.ipass.prestatiesysteem.resource;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostgresBaseDao {
  //Deze klas is voor het verbinding maken met de postgres database
  protected final Connection getConnection() {
    Connection result = null;

    try {
      InitialContext ic = new InitialContext();
      DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
      
      result = ds.getConnection();
    } catch (Exception ex) {
      throw new RuntimeException(ex);
    }

    return result;
  }
}

