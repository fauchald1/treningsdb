package application;   

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class DB
  {
   private Connection connect() {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e1) {
  		e1.printStackTrace();
    }
    // SQLite connection string
    String url = "jdbc:sqlite:trening.db";
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return conn;
  }

  public void getKategori() {
    String sql = "SELECT * FROM Kategori";

    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s

      ResultSet resultSet = statement.executeQuery(sql);
      while(resultSet.next()) {
        System.out.println("name = " + resultSet.getString("Navn"));
        System.out.println("id = " + resultSet.getInt("KategoriID"));
      }
    }
    catch(SQLException e){ System.err.println(e.getMessage()); }
  }

  public double getBest(String ovelse) {
    String sql = "SELECT * FROM Styrke INNER JOIN OktOvelse ON Styrke.OktOvelseID = OktOvelse.OktOvelseID WHERE OktOvelse.OvelseID = '" + getOvelseID(ovelse) + "'";
    Double best = 0.0;
    //List<double> data = new ArrayList<double>();
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      ResultSet resultSet = statement.executeQuery(sql);
      while(resultSet.next()) {
        Double current = resultSet.getDouble("belastning");
        if (current > best) {
          best = current;
        }
      }
    }
    catch(SQLException e){ System.err.println(e.getMessage()); }
    return best;
  }

  public double getStats() {
    String sql = "SELECT * FROM Styrke";
    List<Double> data = new ArrayList<Double>();
    Double avg = 0.0;
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      ResultSet resultSet = statement.executeQuery(sql);
      while(resultSet.next()) {
        data.add(resultSet.getDouble("belastning"));
      }
      Double sum = 0.0;
      if(!data.isEmpty()) {
        for (Double d : data) {
          sum += d;
        }
        avg = sum / data.size();
      }
    }
    catch(SQLException e){ System.err.println(e.getMessage()); }
    return avg;
  }

  public String getLast() {
    String last = "";
    String dato;
    String tidspunkt;
    String varighet;
    String notat;
    Integer id;
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      
      ResultSet resultSet = statement.executeQuery("SELECT * FROM Okt ORDER BY OktID DESC LIMIT 1");
      while (resultSet.next()) {    
          id = resultSet.getInt("OktID");
          dato = resultSet.getString("Dato");
          tidspunkt = resultSet.getString("Tidspunkt");
          varighet = resultSet.getString("Varighet");
          notat = resultSet.getString("Notat");
      }
    }
    catch(SQLException e){ System.out.println(e); }

    last += "Dato:" + dato + " | " + "Tidspunkt:" + tidspunkt + " | " + "Varighet:" + varighet + "\nNotat: " + notat; 

    String sql = "SELECT * FROM OktOvelse INNER JOIN Styrke ON OktOvelse.OktOvelseID=Styrke.OktOvelseID INNER JOIN Ovelse ON OktOvelse.OvelseID=Ovelse.OvelseID WHERE OktID ='" + id + "'";
    
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {    
          last += resultSet.getString("Navn") + ":\n Sett: " + resultSet.getString("Sett") + ":\n Reps: " + resultSet.getString("Repetisjoner") + ":\n Belastning: " + resultSet.getString("Belastning");
          last += ":\n Form: " + resultSet.getString("Form") + ":\n Prestasjon: " + resultSet.getString("Prestasjon") + "\n\n";
      }

    }
    catch(SQLException e){ System.out.println(e); }
    return last;
  }

  public Integer getOvelseID(String ovelse_navn) {
    String sql = "SELECT OvelseID FROM Ovelse WHERE Navn = '" + ovelse_navn + "'";
    Integer id = 0;
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      ResultSet resultSet = statement.executeQuery(sql);
      id = resultSet.getInt("OvelseID");
    }
    catch(SQLException e){ System.out.println(e); }
    return id;
  }

  public boolean checkOvelse(String ovelse_navn) {
    String sql = "SELECT * FROM Ovelse WHERE Navn = '" + ovelse_navn + "'";
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s

      ResultSet resultSet = statement.executeQuery(sql);
      if (resultSet.next()) {    
        return true;
      }
    }
    catch(SQLException e){ return false; }
    return false;
  }

  public Integer setOkt(String dato, String tidspunkt, String varighet, String notat) {
    String sql = "INSERT INTO Okt (Dato, Tidspunkt, Varighet, Notat) VALUES ('" + dato + "', '" + tidspunkt + "', '" + varighet + "', '" + notat  + "')";
    Integer id = 0;
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      statement.executeUpdate(sql);
    }
    catch(SQLException e){ System.out.println(e); }
    
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      
      ResultSet resultSet = statement.executeQuery("SELECT * FROM Okt ORDER BY OktID DESC LIMIT 1");
      while (resultSet.next()) {    
          id = resultSet.getInt("OktID");
      }
    }
    catch(SQLException e){ System.out.println(e); }
    
    return id;
  }

  public void setOvelse(String navn, String beskrivelse) {
    String sql = "INSERT INTO Ovelse (Navn, Beskrivelse) VALUES ('" + navn + "', '" + beskrivelse + "')";
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      statement.executeUpdate(sql);
    }
    catch(SQLException e){ System.out.println(e); }
  }

  public void setOktOvelse(Integer ovelse_id, Integer okt_id, Integer form, Integer prestasjon, Integer sett, Integer reps, Integer belastning) {
    String sql = "INSERT INTO OktOvelse (OvelseID, OktID, Form, Prestasjon) VALUES ('" + ovelse_id + "', '" + okt_id + "', '" + form + "', '" + prestasjon  + "')";
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      statement.executeUpdate(sql);
    }
    catch(SQLException e){ System.out.println(e); }

    // Get ID of new OktOvelse
    Integer id = 0;
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      
      ResultSet resultSet = statement.executeQuery("SELECT * FROM OktOvelse ORDER BY OktOvelseID DESC LIMIT 1");
      while (resultSet.next()) {    
        id = resultSet.getInt("OktOvelseID");
      }
    }
    catch(SQLException e){ System.out.println(e); }

    sql = "INSERT INTO Styrke (OktOvelseID, Sett, Repetisjoner, Belastning) VALUES ('" + id + "', '" + sett + "', '" + reps + "', '" + belastning  + "')";
    try (Connection connection = this.connect();) {
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(10);  // Timeout is 10s
      statement.executeUpdate(sql);
    }
    catch(SQLException e){ System.out.println(e); }
  }
}
