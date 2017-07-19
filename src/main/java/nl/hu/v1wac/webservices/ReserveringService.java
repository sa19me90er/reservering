package nl.hu.v1wac.webservices;



import java.sql.Date;
import java.util.List;

import nl.hu.v1wac.domain.Reservering;
import nl.hu.v1wac.persistence.ReserveringDAO;

public class ReserveringService {
	private ReserveringDAO reserveringDAO = new ReserveringDAO();
	
// get all	
	public  List<Reservering> getAllReserveringen() {
		return reserveringDAO.findAllReserveringen();
	}
	
	
// get by id	

	
	public Reservering findById(int reserveringnummer){
		Reservering result = null;
		
		for (Reservering r : reserveringDAO.findAllReserveringen()){
			if(r.getReserveringnummer() == (reserveringnummer)  ){
				result = r;
				break;
			}
		}
		return result;
	}	
	
	
//get by datum
	
	public Reservering findByDatum(Date datum){
		Reservering result = null;
		
		for (Reservering r : reserveringDAO.findAllReserveringen()){
			if(r.getDatum() == (datum)  ){
				result = r;
				break;
			}
		}
		return result;
	}	
	
	
// post	
	
	public void SaveReservering(Reservering reservering){
		if (reservering != null){
			reserveringDAO.saveReservering(reservering);
		}
		else throw new IllegalArgumentException("kan niet opslaan");
	} 
	
	
// Delete
	
	public void deleteReservering(int Reserveringnummer){
		Reservering r  = reserveringDAO.findById(Reserveringnummer);
		
		if(r != null){
		reserveringDAO.deleteReservering(r);
		} else throw new IllegalArgumentException("Reserveringnummer bestaat niet!");
	}	
	

// update
	
	public void updateReservering(Reservering reservering){
		if (reservering != null){
			reserveringDAO.updateReservering(reservering);
		}
		
		else throw new IllegalArgumentException("kan niet updaten");
		
	}
	
	
} 
