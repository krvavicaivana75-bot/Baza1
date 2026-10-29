import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.sql.DataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import static javax.swing.DropMode.INSERT;

/*import static javax.swing.DropMode.INSERT;*/


public class Vjezba22 {

    public static void main(String[] args) {

        ArrayList<String>drzave=new ArrayList<>(Arrays.asList("dr1","dr2","dr3","dr4","dr5","dr6","dr7","dr8","dr9","dr10"));

        //1. Kreiranje DataSource objekta i stvaranje konekcije
        DataSource dataSource = createDataSource();


        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Uspješno spojeni na bazu podataka!");


           PreparedStatement stmt;
            stmt = connection.prepareStatement("INSERT INTO Drzava (Naziv) VALUES(?)");
            for(String d : drzave) {
                stmt.setString(1,d);

                stmt.executeUpdate();
            }


        }catch (SQLException e) {
            System.err.println("Greška prilikom spajanja na bazu podataka:");
            e.printStackTrace();
        }
    }



    // Metoda za stvaranje DataSource objekta
    private static DataSource createDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("AdventureWorksOBP");
        dataSource.setUser("sa");
        dataSource.setPassword("sql");
        dataSource.setTrustServerCertificate(true);
        return dataSource;
    }
}