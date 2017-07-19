package nl.hu.v1wac.domain;

import java.sql.Time;

import javax.json.JsonValue;

import java.sql.Date;

public class Reservering {
	private int reserveringnummer;
	private Date datum;
	private int aantalPeronen;
	private String opmerkingen;
	private String fk_klant;
	private int fk_tafel;
	
	public Reservering (int reserveringnummer, Date datum, int aantalPeronen, String opmerkingen, String fk_klant,int fk_tafel ){
		this.reserveringnummer=reserveringnummer;
		this.datum=datum;
		this.aantalPeronen=aantalPeronen;
		this.opmerkingen=opmerkingen;
		this.fk_klant=fk_klant;
		this.fk_tafel=fk_tafel;

	}

	public int getReserveringnummer() {
		
		return reserveringnummer;
	}

	public Date getDatum() {
		
		return datum;
	}



	public int getAantalPeronen() {
		// TODO Auto-generated method stub
		return aantalPeronen;
	}

	public String getOpmerkingen() {
		// TODO Auto-generated method stub
		return opmerkingen;
	}
	
	public String getFk_klant() {
		// TODO Auto-generated method stub
		return fk_klant;
	}

	public int getFk_tafel() {
		// TODO Auto-generated method stub
		return fk_tafel;
	}

	
	}

	
