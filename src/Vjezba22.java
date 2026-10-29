import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.sql.DataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;


public class Vjezba22 {

    public static void main(String[] args) {
        Scanner ulaz=new Scanner(System.in);
        int odabir;

        //1. Kreiranje DataSource objekta i stvaranje konekcije
        DataSource dataSource = createDataSource();


        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Uspješno spojeni na bazu podataka!");
            System.out.println("Odaberi:\n1-nova drzava\n2-izmjena postojece drzave\n3-brisanje postojeće drzave\n4-prikaz sortiranih drzava\n5-kraj");
            System.out.println("Upišite odabir: ");
            odabir=ulaz.nextInt();
            ulaz.nextLine();
            Statement stmt=null;
            int rowsAffected=0;
            String naziv=null;
            switch(odabir){
                case(1):
                    System.out.println("Upisi ime drzave");
                    naziv=ulaz.nextLine();
                    String sqlInsert = "INSERT INTO Drzava (Naziv) VALUES ('" + naziv + "')";

                    stmt = connection.createStatement();
                    rowsAffected = stmt.executeUpdate(sqlInsert);
                    System.out.println("Država je uspješno dodana!");
                    break;
                case(2):
                    System.out.println("Upisi id drzave i novi naziv drzave");
                    int id=ulaz.nextInt();
                    ulaz.nextLine();
                    naziv=ulaz.nextLine();
                    stmt = connection.createStatement();
                    rowsAffected = stmt.executeUpdate("UPDATE Drzava SET Naziv = '" + naziv + "' WHERE idDrzava = " + id);

                    System.out.println("Država je uspješno preimenovana!");
                    break;

            }


            /*2. preimenovanje države Hrvatska->Croatia
            Statement stmt = connection.createStatement();
            int rowsAffected = stmt.executeUpdate("UPDATE Drzava SET Naziv= 'Croatia' WHERE idDrzava=1");
            System.out.println("Država je uspješno preimenovana!");

            //3. dohvaćanje svih država
            ResultSet rs = stmt.executeQuery("SELECT IdDrzava, Naziv FROM Drzava");
            while( rs.next() ) {
                System.out.printf("%d %s\n", rs.getInt("idDrzava"), rs.getString("Naziv"));
            }*/


        } catch (SQLException e) {
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