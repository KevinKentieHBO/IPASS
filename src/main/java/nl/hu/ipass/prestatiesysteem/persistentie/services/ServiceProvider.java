package nl.hu.ipass.prestatiesysteem.persistentie.services;

public class ServiceProvider {
	private static PrestatieService prestatieService = new PrestatieService();
	private static OefeningService oefeningService = new OefeningService();
	private static SporterService sporterService = new SporterService();
	
	//klasse waar alle Serviceklassen worden doorgegeven.
	
	public static PrestatieService getPrestatieService() {
		return prestatieService;
	}
	
	public static OefeningService getOefeningService() {
		return oefeningService;
	}
	
	public static SporterService getSporterService() {
		return sporterService;
	}

}
