package nl.hu.v1wac.webservices;



import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import nl.hu.v1wac.domain.Klant;



@Path("/klanten")
public class KlantResource {
KlantService klantService = ServiceProvider.getKlantService();

    @GET
    @Produces("application/json")
    public String getKlant() {
       // ReserveringService service = ServiceProvider.getReserveringService();
        JsonArrayBuilder jab = Json.createArrayBuilder();
        for (Klant k : klantService.getAllKlanten()){
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("emailadres", k.getEmailadres());
            job.add("achternaam", k.getAchternaam().toString());
            job.add("voornaam", k.getVoornaam());
            job.add("telefoonnummer", k.getTelefoonnummer());
            jab.add(job);
        }
        JsonArray array = jab.build();
        return array.toString();
    }
    
    
	
  
      @POST
      @Produces("application/json")
      public String createReservering (@FormParam("emailadres") String eml,
                                   @FormParam("achternaam") String achnm,
                                   @FormParam("voornaam") String vrnm,
                                   @FormParam("telefoonnummer") int tel
                                ) {
    	  
    	  
    	System.out.println("CREATE RESERVERING");  
    	
    	
        Klant newKlant = new Klant(eml, achnm, vrnm , tel );
        klantService.SaveKlant(newKlant);
        return klantToJson(newKlant).build().toString();
      }

      private JsonObjectBuilder klantToJson(Klant k) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("emailadres", k.getEmailadres());
        job.add("achternaam", k.getAchternaam().toString());
        job.add("voornaam", k.getVoornaam());
        job.add("telefoonnummer", k.getTelefoonnummer());
      
        return job;
      }
      
}