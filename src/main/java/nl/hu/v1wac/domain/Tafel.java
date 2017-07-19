package nl.hu.v1wac.domain;


public class Tafel {
	private int tafelnummer;
	private String positie;
	private String aanschuifMet;
	
public Tafel (int tafelNummer, String positie, String aanschuifMet){
	this.tafelnummer=tafelnummer;
	this.positie=positie;
	this.aanschuifMet=aanschuifMet;
}
public int getTafelnummer(){
	return tafelnummer;
}
public String getPositie(){
	return positie;
}
public String getAanschuifMet(){
	return aanschuifMet;
}
}
