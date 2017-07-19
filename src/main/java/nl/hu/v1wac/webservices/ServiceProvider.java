package nl.hu.v1wac.webservices;



import nl.hu.v1wac.webservices.ReserveringService;

public class ServiceProvider {
	private static ReserveringService reserveringService = new ReserveringService();
	private static KlantService klantservice=new KlantService();
	
	public static ReserveringService getReserveringService() {
		return reserveringService;
	}
	
	

	public static KlantService getKlantService() {
		return klantservice;
	}
}
