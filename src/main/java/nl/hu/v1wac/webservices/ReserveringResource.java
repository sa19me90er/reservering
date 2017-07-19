package nl.hu.v1wac.webservices;



import java.util.List;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import nl.hu.v1wac.domain.Reservering;
import nl.hu.v1wac.webservices.ReserveringService;
import nl.hu.v1wac.webservices.ServiceProvider;



@Path("/reserveringen")
public class ReserveringResource {
ReserveringService reserveringService = ServiceProvider.getReserveringService();

    @GET
    @Produces("application/json")
    public String getReservering() {
       // ReserveringService service = ServiceProvider.getReserveringService();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Reservering r : reserveringService.getAllReserveringen()){
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("Reserveringnummer", r.getReserveringnummer());
            job.add("Datum", r.getDatum().toString());
            job.add("AantalPeronen", r.getAantalPeronen());
            job.add("Opmerkingen", r.getOpmerkingen());
            job.add("Fk_klant", r.getFk_klant());
            job.add("Fk_tafel", r.getFk_tafel());
            jab.add(job);
        }
        JsonArray array = jab.build();
        return array.toString();
    }
    
    
	
   
      @POST
      @Produces("application/json")
      public String createReservering (@FormParam("Reserveringnummer") String nm,
                                   @FormParam("Datum") String dtm,
                                   @FormParam("AantalPeronen") String aant,
                                   @FormParam("Opmerkingen") String opmrk,
                                   @FormParam("Fk_klant") String fkk,
                                   @FormParam("Fk_tafel") String fkt) {
    	  
    	  
    	System.out.println("CREATE RESERVERING");  
    	
    	java.sql.Date date = null;
		try {
			date = new java.sql.Date(new SimpleDateFormat("dd-mm-yyyy").parse(dtm).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Reservering newReservering = new Reservering(Integer.parseInt(nm), date, Integer.parseInt(aant),opmrk,fkk,Integer.parseInt(fkt));
        reserveringService.SaveReservering(newReservering);
        return reserveringToJson(newReservering).build().toString();
      }

      private JsonObjectBuilder reserveringToJson(Reservering r) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("Reserveringnummer", r.getReserveringnummer());
        job.add("Datum",r.getDatum().toString());
        job.add("AantalPeronen", r.getAantalPeronen());
        job.add("Opmerkingen", r.getOpmerkingen());
        job.add("Fk_klant", r.getFk_klant());
        job.add("Fk_tafel", r.getFk_tafel());
        return job;
      }
      
 // Delete reservering
      
      @DELETE
      @Path("{reserveringnummer}")
      public Response deleteCustomer(@PathParam("reserveringnummer") int reserveringnummer) {
        Reservering found = null;
        
        for (Reservering r : reserveringService.getAllReserveringen()) {
          if (r.getReserveringnummer() == reserveringnummer) {
            found = r; break;
          }
        }

        if (found == null) {
          return Response.status(Response.Status.NOT_FOUND).build();
        } else {
          reserveringService.deleteReservering(reserveringnummer);
          return Response.ok().build();
        }
      }
      
 // Get by ID     
      
      @GET

  	@Path("{reserveringnummer}")
  	@Produces("application/json")
  	public String getReserveringInfo(@PathParam("reserveringnummer") int reserveringnummer){
  		ReserveringService service = ServiceProvider.getReserveringService();
  		Reservering r = service.findById(reserveringnummer);
  		
  		if(r == null){
  			throw new WebApplicationException("Reservering bestaat niet!");
  		}
  		JsonObjectBuilder job = Json.createObjectBuilder();
  	  job.add("Reserveringnummer", r.getReserveringnummer());
      job.add("Datum", r.getDatum().toString());
      job.add("AantalPeronen", r.getAantalPeronen());
      job.add("Opmerkingen", r.getOpmerkingen());
      job.add("Fk_klant", r.getFk_klant());
      job.add("Fk_tafel", r.getFk_tafel());
  		return job.build().toString();
  	} 

 // Get by date
     
      @GET

    	@Path("/datum/{datum}")
    	@Produces("application/json")
    	public String getReserveringByDate(@PathParam("datum") String datum){
    	 
    	  java.sql.Date date = null;
  		try {
  			date = new java.sql.Date(new SimpleDateFormat("dd-mm-yyyy").parse(datum).getTime());
  		} catch (ParseException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}	  
    
    	  
    //	  SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    //	    Date dateObj = sdf.parse(datum);
    	  
    	  ReserveringService service = ServiceProvider.getReserveringService();
    		Reservering r = service.findByDatum(date);
    		
    		
    		if(r == null){
    			throw new WebApplicationException("Geen reserveringen gepland op deze datums!");
    		}
    		JsonObjectBuilder job = Json.createObjectBuilder();
    	  job.add("Reserveringnummer", r.getReserveringnummer());
        job.add("Datum", r.getDatum().toString());
        job.add("AantalPeronen", r.getAantalPeronen());
        job.add("Opmerkingen", r.getOpmerkingen());
        job.add("Fk_klant", r.getFk_klant());
        job.add("Fk_tafel", r.getFk_tafel());
    		return job.build().toString();
    	} 
/*
      
// Update
      
 

        @PUT
        @Path("/put/{reserveringnummer}")
        @Produces("application/json")
        public String updateCustomer(@FormParam("Reserveringnummer") int reserveringnummer,
                @FormParam("Datum") String datum,
                @FormParam("AantalPeronen") String aantalPeronen,
                @FormParam("Opmerkingen") String opmerkingen,
                @FormParam("Fk_klant") String fk_klant,
                @FormParam("Fk_tafel") String fk_tafel) {


        			java.sql.Date date = null;
        				try {
        					date = new java.sql.Date(new SimpleDateFormat("dd-mm-yyyy").parse(datum).getTime());
        				} catch (ParseException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
          Reservering found = null;

          for (Reservering r : reserveringService.getAllReserveringen()) {
            if  (r.getReserveringnummer() == reserveringnummer) {
            		
            		r.getDatum()  = datum;
            		r.getAantalPeronen() = aantalPeronen;
            		
              return reserveringToJson(found).build().toString();
            }
          }
          throw new WebApplicationException("Customer not found!");
        }
    
      
      
  */    
 
}