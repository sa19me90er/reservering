package nl.hu.v1wac.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.domain.Reservering;

	public class ReserveringDAO extends BaseDAO {
		private PreparedStatement preparedStatement = null;


		

		
		/***************************** READ methods *****************************/
		
		private List<Reservering> selectReserveringen(String query) {
			List<Reservering> results = new ArrayList<Reservering>();
			
			try (Connection con = super.getConnection()) {
				Statement stmt = con.createStatement();
				ResultSet dbResultSet = stmt.executeQuery(query);
				
				while (dbResultSet.next()) {
					int reserveringnummer = dbResultSet.getInt("Reserveringnummer");
					Date datum = dbResultSet.getDate("Datum");
					int aantalPeronen = dbResultSet.getInt("AantalPeronen");
					String opmerkingen = dbResultSet.getString("Opmerkingen");
					String fk_klant = dbResultSet.getString("Fk_klant");
					int fk_tafel = dbResultSet.getInt("Fk_tafel");  
                   				     
					
			
					
					results.add(new Reservering(reserveringnummer, datum, aantalPeronen, opmerkingen,fk_klant, fk_tafel ));
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			
			return results;
		}
		
		public Reservering findById(int reserveringnummer) {
			return selectReserveringen("SELECT \"Reserveringnummer\", \"Datum\", \"AantalPeronen\", \"Opmerkingen\" , \"Fk_klant\", \"Fk_tafel\" FROM \"public\".\"Reservering\" " + " WHERE \"Reserveringnummer\" = " + reserveringnummer ).get(0);
		}

		
		
		public Reservering findDatum(String datum) {
			return selectReserveringen("SELECT \"Reserveringnummer\", \"Datum\", \"AantalPeronen\", \"Opmerkingen\" , \"Fk_klant\", \"Fk_tafel\" FROM \"public\".\"Reservering\" " + " WHERE \"Datum\" =  '" + datum + "'" ).get(0);
		}

		
		
		
		public List<Reservering> findAllReserveringen() {
			return selectReserveringen("SELECT \"Reserveringnummer\", \"Datum\", \"AantalPeronen\", \"Opmerkingen\", \"Fk_klant\", \"Fk_tafel\" FROM \"public\".\"Reservering\"");
		}



		/***************************** CREATE & UPDATE methods *****************************/	
		
		
		
		public void saveReservering(Reservering reservering){
			String query = "INSERT INTO public.\"Reservering\" (\"Reserveringnummer\", \"Datum\", \"AantalPeronen\", \"Opmerkingen\",\"Fk_klant\", \"Fk_tafel\")  VALUES(?,?,?,?,?,?)"; 
			
			try (Connection con = super.getConnection()) {
				preparedStatement = con.prepareStatement(query);
			
				preparedStatement.setInt(1, reservering.getReserveringnummer()); 		
				preparedStatement.setDate(2, reservering.getDatum()); 
				preparedStatement.setInt(3, reservering.getAantalPeronen());
				preparedStatement.setString(4, reservering.getOpmerkingen());
				preparedStatement.setString(5, reservering.getFk_klant());
				preparedStatement.setInt(6, reservering.getFk_tafel());
				preparedStatement.executeUpdate();	
				preparedStatement.close();

				System.out.println("reservering op: " + reservering.getDatum()  + " saved.");
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}	
	}
		
		
		/***************************** Delete method *****************************/
		
		public boolean deleteReservering(Reservering reservering){
			boolean result = false;
			boolean reserveringExists = findById(reservering.getReserveringnummer()) != null;
			
			if(reserveringExists){
				
				String query = "DELETE FROM public.\"Reservering\" WHERE \"Reserveringnummer\" = "+reservering.getReserveringnummer()+"";
				
				try(Connection con = super.getConnection()){
					Statement stmt = con.createStatement();
					ResultSet dbResultSet = stmt.executeQuery(query);
				} 
				catch (SQLException sqle){
					sqle.printStackTrace(); }
			}
				return result;
		}
	
			
	   
		
	
	  // update
    
    
    public boolean updateReservering(Reservering reservering){ 
		boolean result = false;
		boolean ReserveringExists = findById(reservering.getReserveringnummer()) != null;
		
		//update Reservering
		if(ReserveringExists){ 
			String query = "UPDATE public.\"Reservering\" "
			+ " SET \"Reserveringnummer\" = '" 		+ reservering.getReserveringnummer()			+"'," 
			+ "  \"Datum\" = '" 	+ reservering.getDatum()	+"'," 
			+ "  \"AantalPeronen\" = '" 		+  reservering.getAantalPeronen()		+"'," 
			+ " \"Opmerkingen\" = " + reservering.getOpmerkingen() +","
			+ " \"Fk_klant\" = "+  reservering.getFk_klant() +","
			+ " \"Fk_tafel\" = " 		+ reservering.getFk_tafel()
			+ " WHERE \"Reserveringnummer\" = " 		+ reservering.getReserveringnummer();
			
			try(Connection con = super.getConnection()){
				Statement stmt = con.createStatement();
				if(stmt.executeUpdate(query) == 1){
					result = true;
				}
			}
			
			catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}
			return result;
		
	}     

}
	
	

	
	
