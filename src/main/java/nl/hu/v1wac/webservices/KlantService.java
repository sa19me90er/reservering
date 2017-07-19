package nl.hu.v1wac.webservices;




import java.sql.Date;
import java.util.List;

import nl.hu.v1wac.domain.Klant;
import nl.hu.v1wac.persistence.KlantDAO;

public class KlantService {
	private KlantDAO klantDAO = new KlantDAO();
	
// get all	
	public  List<Klant> getAllKlanten() {
		return klantDAO.findAllKlanten();
	}
	

	
// post	
	
	public void SaveKlant(Klant klant){
		if (klant != null){
			klantDAO.saveKlant(klant);
		}
		else throw new IllegalArgumentException("kan niet opslaan");
	} 
	
	
	
} 
