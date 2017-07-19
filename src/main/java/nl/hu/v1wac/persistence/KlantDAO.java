package nl.hu.v1wac.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.domain.Klant;

public class KlantDAO extends BaseDAO {
	private PreparedStatement preparedStatement = null;


	

	
	/***************************** READ methods *****************************/
	
	private List<Klant> selectKlanten(String query) {
		List<Klant> results = new ArrayList<Klant>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				String emailadres = dbResultSet.getString("emailadres");
				String achternaam = dbResultSet.getString("achternaam");
				String voornaam = dbResultSet.getString("voornaam");
				int telefoonnummer = dbResultSet.getInt("telefoonnummer");
	
				
				results.add(new Klant(emailadres, achternaam, voornaam, telefoonnummer ));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
	
	
	
	
	public List<Klant> findAllKlanten() {
		return selectKlanten("SELECT \"emailadres\", \"achternaam\", \"voornaam\", \"telefoonnummer\" FROM \"public\".\"Klant\"");
	}



	/***************************** CREATE & UPDATE methods *****************************/	
	
	
	
	public void saveKlant(Klant klant){
		String query = "INSERT INTO public.\"Klant\" (\"emailadres\", \"achternaam\", \"voornaam\", \"telefoonnummer\")  VALUES(?,?,?,?)"; 
		
		try (Connection con = super.getConnection()) {
			preparedStatement = con.prepareStatement(query);
		
			preparedStatement.setString(1, klant.getEmailadres()); 		
			preparedStatement.setString(2, klant.getAchternaam()); 
			preparedStatement.setString(3, klant.getVoornaam());
			preparedStatement.setInt(4, klant.getTelefoonnummer());
			
			preparedStatement.executeUpdate();	
			preparedStatement.close();

			System.out.println("Klant met Achternaam: " + klant.getAchternaam()  + " saved.");
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}	
}
	
	
}

