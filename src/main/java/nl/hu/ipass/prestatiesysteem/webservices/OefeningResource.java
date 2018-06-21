package nl.hu.ipass.prestatiesysteem.webservices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import nl.hu.ipass.prestatiesysteem.persistentie.objecten.CardioPrestatie;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.GewichtPrestatie;
import nl.hu.ipass.prestatiesysteem.persistentie.objecten.Oefening;
import nl.hu.ipass.prestatiesysteem.persistentie.services.OefeningService;
import nl.hu.ipass.prestatiesysteem.persistentie.services.PrestatieService;
import nl.hu.ipass.prestatiesysteem.persistentie.services.ServiceProvider;

@Path("/oefening")
public class OefeningResource {
	OefeningService service = ServiceProvider.getOefeningService();
	
	//get methode zegt ervoor dat er een lijst met alle oefeningen uit de database opgehaald word
	@GET
	@Produces("application/json")
	public String getOefening() {
		OefeningService service = ServiceProvider.getOefeningService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (Oefening oefening : service.getAllOefeningen()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("oefeningnummer", oefening.getOefeningnummer());
			job.add("oefeningnaam", oefening.getOefeningnaam());
			job.add("oefeningtype", oefening.getOefeningtype());
			job.add("intensiteit", oefening.getIntensiteit());
			job.add("calorie_verbranding", oefening.getCalorieverbranding());
			job.add("afbeelding", oefening.getAfbeelding());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	//insert een cardio prestatie in de database
	@POST
	@Path("/cardiooefening")
	@Produces("application/json")
	public Response addCardioPrestatie(	@Context SecurityContext sc,
								   @FormParam("oefeningnaam") String oefeningnaam,
								   @FormParam("sessieduur") int sessieduur,
								   @FormParam("afstand") int afstand,
								   @FormParam("username") String username,
								   @FormParam("snelheid") int snelheid) throws SQLException {
			boolean role = sc.isUserInRole("user");
			if(role) {
			CardioPrestatie cardioprestatie = service.voegCardioToe(username, oefeningnaam, sessieduur, afstand, snelheid);
			if (cardioprestatie == null) {
				System.out.println("help");
				Map<String, String> messages = new HashMap<String, String>();
				messages.put("error", "Country does not exist!");
				return Response.status(409).entity(messages).build();
			}
			return Response.ok().build();
		}
		Map<String, String> message = new HashMap<String, String>();
		message.put("error", "U bent niet gemachtigt");
		return Response.status(409).entity(message).build();
	}
	
	//intert een gewicht prestatie in de database
	@POST
	@Path("/gewichtoefening")
	@Produces("application/json")
	public Response addGewichtPrestatie(	@Context SecurityContext sc,
								   @FormParam("oefeningnaam") String oefeningnaam,
								   @FormParam("sets") int sets,
								   @FormParam("reps") int reps,
								   @FormParam("username") String username,
								   @FormParam("volume") int volume) throws SQLException {
			boolean role = sc.isUserInRole("user");
			if(role) {
			GewichtPrestatie gewichtprestatie = service.voegGewichtToe(username, oefeningnaam, volume, sets, reps);
			if (gewichtprestatie == null) {
				System.out.println("help");
				Map<String, String> messages = new HashMap<String, String>();
				messages.put("error", "Country does not exist!");
				return Response.status(409).entity(messages).build();
			}
			return Response.ok().build();
		}
		Map<String, String> message = new HashMap<String, String>();
		message.put("error", "U bent niet gemachtigt");
		return Response.status(409).entity(message).build();
	}

}
