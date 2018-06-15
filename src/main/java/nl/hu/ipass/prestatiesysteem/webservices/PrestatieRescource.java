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

@Path("/prestatie")
public class PrestatieRescource {
	PrestatieService service = ServiceProvider.getPrestatieService();
	
	@GET
	@Path("/cardio{sportnummer}")
	@Produces("application/json")
	public String getPrestatie(@PathParam("sportnummer") int sportnummer) {
		PrestatieService service = ServiceProvider.getPrestatieService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (CardioPrestatie prestatie : service.getAllCardioPrestaties(sportnummer)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("prestatienummer", prestatie.getPrestatienummer());
			job.add("sportersnummer", prestatie.getSporter().getSportersnummer());
			job.add("oefeningnummer", prestatie.getOefening().getOefeningnummer());
			job.add("oefeningafbeelding", prestatie.getOefening().getAfbeelding());
			job.add("datum", prestatie.getDatum());
			job.add("sessieduur", prestatie.getSessieduur());
			job.add("snelheid", prestatie.getSnelheid());
			job.add("afstand", prestatie.getAfstand());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@GET
	@Path("/gewicht{sportnummer}")
	@Produces("application/json")
	public String getGewicht(@PathParam("sportnummer") int sportnummer) {
		PrestatieService service = ServiceProvider.getPrestatieService();
		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (GewichtPrestatie prestatie : service.getAllGewichtPrestaties(sportnummer)) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("prestatienummer", prestatie.getPrestatienummer());
			job.add("sportersnummer", prestatie.getSporter().getSportersnummer());
			job.add("oefeningnummer", prestatie.getOefening().getOefeningnummer());
			job.add("oefeningafbeelding", prestatie.getOefening().getAfbeelding());
			job.add("datum", prestatie.getDatum());
			job.add("volume", prestatie.getVolume());
			job.add("sets", prestatie.getSets());
			job.add("reps", prestatie.getReps());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
	
	@DELETE
	@Path("/gewicht{prestatienummer}")
	@Produces("application/json")
	public Response deleteGewichtPrestatie(@PathParam("prestatienummer") int prestatienummer) {
		if (!service.deleteGewichtPrestatie(prestatienummer)) {
			return Response.status(404).build();
		}
		Map<String, String> bericht = new HashMap<String, String>();
		bericht.put("resultaat", "Uitvoering gelukt");
		return Response.status(200).entity(bericht).build();
}
	
	@DELETE
	@Path("/cardio{prestatienummer}")
	@Produces("application/json")
	public Response deleteCardioPrestatie(@PathParam("prestatienummer") int prestatienummer) {
		if (!service.deleteCardioPrestatie(prestatienummer)) {
			return Response.status(404).build();
		}
		Map<String, String> bericht = new HashMap<String, String>();
		bericht.put("resultaat", "Uitvoering gelukt");
		return Response.status(200).entity(bericht).build();
}
	
	

}
