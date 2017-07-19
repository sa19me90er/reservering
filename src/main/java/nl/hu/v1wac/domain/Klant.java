package nl.hu.v1wac.domain;

public class Klant {
	private String emailadres;
	private String achternaam;
	private String voornaam;
	private int telefoonnummer;
	
	public Klant(String emailadres, String achternaam, String voornaam, int telefoonnummer){
		this.emailadres=emailadres;
		this.achternaam=achternaam;
		this.voornaam=voornaam;
		this.telefoonnummer=telefoonnummer;
	}
public String getEmailadres(){
	return emailadres;
}
public String getAchternaam(){
	return achternaam;
}
public String getVoornaam(){
	return voornaam;
}
public int getTelefoonnummer(){
	return telefoonnummer;
}
}
