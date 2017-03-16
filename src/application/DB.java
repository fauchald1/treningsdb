package application;   

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}