package nl.hu.ipass.prestatiesysteem.resource;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import nl.hu.ipass.prestatiesysteem.domein.CardioPrestatie;
import nl.hu.ipass.prestatiesysteem.domein.GewichtPrestatie;
import nl.hu.ipass.prestatiesysteem.domein.Oefening;
import nl.hu.ipass.prestatiesysteem.domein.Sporter;


@Path("/sporter")
public class SporterRescource {
	SporterService service = ServiceProvider.getSporterService();
	
	//Insert een sporter (gebruiker) in de database
	@POST
	@Produces("application/json")
	public Response addSporter(
			@FormParam("voornaam") String voornaam,
			@FormParam("tussenvoegsel") String tussenvoegsel,
			@FormParam("achternaam") String achternaam,
			@FormParam("email") String email,
			@FormParam("wachtwoord") String wachtwoord,
			@FormParam("telefoonnummer") int telefoonnummer,
			@FormParam("geboortedatum") String geboortedatum,
			@FormParam("gewicht") int gewicht) throws SQLException {
			boolean s1 = service.voegSporterToe(voornaam, tussenvoegsel, achternaam, email, wachtwoord, telefoonnummer, geboortedatum, gewicht);
			if (voornaam == null) {
				System.out.println("help");
				Map<String, String> messages = new HashMap<String, String>();
				messages.put("error", "sporter does not exist!");
				return Response.status(409).entity(messages).build();
			}
			return Response.ok().build();
		}

	//geeft een sporter uit de database
	@GET
	@Path("{username}")
	@Produces("application/json")
	public String getPrestatie(@PathParam("username") String username) {
		SporterService service = ServiceProvider.getSporterService();

		Sporter s =  service.getSporter(username);
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("sportersnummer", s.getSportersnummer());
		job.add("gewicht", s.getGewicht());

		JsonObject jsobject = job.build();
		return jsobject.toString();
	}
}
