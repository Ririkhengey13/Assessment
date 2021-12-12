import java.sql.*;

public class Main {
   public static void main( String args[] ) {
      Create_db();
      Add_data();
      Display_data();
   }
   public static void Create_db(){
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "CREATE TABLE Fav_movies " +
                        "(ID INT PRIMARY KEY     NOT NULL," +
                        " Name           TEXT    NOT NULL, " + 
                        " Lead_actor            TEXT  NOT NULL, " + 
                        " Lead_actress        TEXT NOT NULL, " + 
                        " Year         DATE)"; 
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
   }

   public static void Add_data(){
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = "INSERT INTO Fav_movies (ID,Name,Lead_actor,Lead_actress,Year) " +
                        "VALUES (1, 'The Shawshank Redemtion', 'Tim Robbins', 'None', '1994' );"; 
         stmt.executeUpdate(sql);

         sql = "INSERT INTO Fav_movies (ID,Name,Lead_actor,Lead_actress,Year) " +
                  "VALUES (2, 'Innception', 'Leonardo DiCaprio', 'Elliot page', '2010' );"; 
         stmt.executeUpdate(sql);

         sql = "INSERT INTO Fav_movies (ID,Name,Lead_actor,Lead_actress,Year) " +
                  "VALUES (3, 'Coco', 'Anthony Gonzalez', 'Alanna Ubach', '2017' );"; 
         stmt.executeUpdate(sql);

         sql = "INSERT INTO Fav_movies (ID,Name,Lead_actor,Lead_actress,Year) " +
                  "VALUES (4, 'The Kid', 'Charless Chaplin', 'Edna Purviance ', '1921' );"; 
         stmt.executeUpdate(sql);

         stmt.close();
         c.commit();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
   }

   public static void Display_data(){
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:mulesoft.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM Fav_movies;" );
         
         while ( rs.next() ) {
            int id = rs.getInt("id");
            String  name = rs.getString("Name");
            String actor  = rs.getString("Lead_actor");
            String  actress = rs.getString("Lead_actress");
            int year = rs.getInt("Year");
            
            System.out.println( "ID = " + id );
            System.out.println( "Name = " + name );
            System.out.println( "Lead Actor = " + actor );
            System.out.println( "Lead Actress = " + actress );
            System.out.println( "Year of release = " + year );
            System.out.println();
         }
         rs.close();
         stmt.close();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
   }
}