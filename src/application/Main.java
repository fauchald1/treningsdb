package application;   

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main
{
  public static void main(String[] args) throws ClassNotFoundException
  {
    // Load sqlite-JDBC driver
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try
    {
     // create a DB connection
     connection = DriverManager.getConnection("jdbc:sqlite:trening.db");

     Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);  // Timeout is 30s

         ResultSet resultSet = statement.executeQuery("SELECT * from Kategori");
         while(resultSet.next())
         {
          // iterate results
          System.out.println("name = " + resultSet.getString("Navn"));
          System.out.println("id = " + resultSet.getInt("KategoriID"));
        }
      }

      catch(SQLException e){  System.err.println(e.getMessage()); }       
      finally {         
        try {
          if(connection != null)
           connection.close();
       }
            catch(SQLException e) {         
             System.err.println(e); 
           }
         }
       }
     }