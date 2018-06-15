package nl.hu.ipass.prestatiesysteem.webservices;

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
	
	@GET
	@Path("/eengewicht{prestatienummer}")
	@Produces("application/json")
	public String geefGewichtPrestatie(@PathParam("prestatienummer") int prestatienummer) {
		PrestatieService service = ServiceProvider.getPrestatieService();

		GewichtPrestatie prestatie = service.getGewichtPrestatie(prestatienummer) ;
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("prestatienummer", prestatie.getPrestatienummer());
			job.add("sportersnummer", prestatie.getSporter().getSportersnummer());
			job.add("oefeningnummer", prestatie.getOefening().getOefeningnummer());
			job.add("oefeningafbeelding", prestatie.getOefening().getAfbeelding());
			job.add("datum", prestatie.getDatum());
			job.add("volume", prestatie.getVolume());
			job.add("sets", prestatie.getSets());
			job.add("reps", prestatie.getReps());
		JsonObject object = job.build();
		return object.toString();
}
	
	@GET
	@Path("/eencardio{prestatienummer}")
	@Produces("application/json")
	public String geefCardioPrestatie(@PathParam("prestatienummer") int prestatienummer) {
		PrestatieService service = ServiceProvider.getPrestatieService();

		CardioPrestatie prestatie = service.getCardioPrestatie(prestatienummer) ;
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("prestatienummer", prestatie.getPrestatienummer());
			job.add("sportersnummer", prestatie.getSporter().getSportersnummer());
			job.add("oefeningnummer", prestatie.getOefening().getOefeningnummer());
			job.add("oefeningafbeelding", prestatie.getOefening().getAfbeelding());
			job.add("datum", prestatie.getDatum());
			job.add("afstand", prestatie.getAfstand());
			job.add("snelheid", prestatie.getSnelheid());
			job.add("sessieduur", prestatie.getSessieduur());
		JsonObject object = job.build();
		return object.toString();
}
	
	@PUT
	@Path("/eengewicht{prestatienummer}")
	@Produces("application/json")
	public Response updateGewichtPrestatie(
				@PathParam("prestatienummer") int prestatienummer, 
				@FormParam("gewichtprestatienummer") int gewichtpresatienummer,
				@FormParam("sets") int sets,
				@FormParam("reps") int reps,
				@FormParam("volume") int volume) throws SQLException  {
		GewichtPrestatie gw = service.updateGewicht(gewichtpresatienummer, volume, sets, reps);
		System.out.println("gw");
		if (gw == null) {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("error", "GewichtPrestatie bestaat niet!");
			return Response.status(409).entity(messages).build();
		}
		Map<String, String> bericht = new HashMap<String, String>();
		bericht.put("resultaat", "Succesvol bijgewerkt!");
		return Response.status(200).entity(bericht).build();
	}
	
	@PUT
	@Path("/eencardio{prestatienummer}")
	@Produces("application/json")
	public Response updateCardioPrestatie(
				@PathParam("prestatienummer") int prestatienummer, 
				@FormParam("gewichtprestatienummer") int gewichtpresatienummer,
				@FormParam("afstand") int afstand,
				@FormParam("snelheid") int snelheid,
				@FormParam("sessieduur") int sessieduur) throws SQLException  {
		CardioPrestatie cp = service.updateCardio(prestatienummer, sessieduur, snelheid, afstand);
		if (cp == null) {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("error", "GewichtPrestatie bestaat niet!");
			return Response.status(409).entity(messages).build();
		}
		Map<String, String> bericht = new HashMap<String, String>();
		bericht.put("resultaat", "Succesvol bijgewerkt!");
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
