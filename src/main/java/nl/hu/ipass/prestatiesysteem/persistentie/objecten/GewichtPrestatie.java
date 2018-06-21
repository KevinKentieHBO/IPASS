package nl.hu.ipass.prestatiesysteem.persistentie.objecten;

public class GewichtPrestatie {
	private int prestatienummer;
	private Sporter sporter;
	private Oefening oefening;
	private String datum;
	private int volume;
	private int sets;
	private int reps;
	
	//constructor voor het maken van een GewichtPrestatie object vanuit de database
	public GewichtPrestatie(int prestatienummer, Sporter sporter, Oefening oefening, String datum, int volume, int sets,
			int reps) {
		super();
		this.prestatienummer = prestatienummer;
		this.sporter = sporter;
		this.oefening = oefening;
		this.datum = datum;
		this.volume = volume;
		this.sets = sets;
		this.reps = reps;
	}
	
	//constructor voor de POST statement richting de database
	public GewichtPrestatie(Sporter sporter, Oefening oefening, int volume, int sets,
			int reps) {
		super();
		this.sporter = sporter;
		this.oefening = oefening;
		this.volume = volume;
		this.sets = sets;
		this.reps = reps;
	}
	
	//hieronder zijn alle getters en setters te vinden
	public int getPrestatienummer() {
		return prestatienummer;
	}
	public void setPrestatienummer(int prestatienummer) {
		this.prestatienummer = prestatienummer;
	}
	public Sporter getSporter() {
		return sporter;
	}
	public void setSporter(Sporter sporter) {
		this.sporter = sporter;
	}
	public Oefening getOefening() {
		return oefening;
	}
	public void setOefening(Oefening oefening) {
		this.oefening = oefening;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	
	

}
