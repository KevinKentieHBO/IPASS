package nl.hu.ipass.prestatiesysteem.persistentie.objecten;

public class CardioPrestatie {
	private int prestatienummer;
	private Sporter sporter;
	private Oefening oefening;
	private String datum;
	private int sessieduur;
	private int afstand;
	private int snelheid;
	
	//constructor voor het maken van een CardioPrestatie object vanuit de database
	public CardioPrestatie(int prestatienummer, Sporter sporter, Oefening oefening, String datum, int sessieduur,
			int afstand, int snelheid) {
		super();
		this.prestatienummer = prestatienummer;
		this.sporter = sporter;
		this.oefening = oefening;
		this.datum = datum;
		this.sessieduur = sessieduur;
		this.afstand = afstand;
		this.snelheid = snelheid;
	}
	
	//constructor voor de POST statement richting de database
	public CardioPrestatie(Sporter sporter, Oefening oefening, int sessieduur,
			int afstand, int snelheid) {
		super();
		this.sporter = sporter;
		this.oefening = oefening;
		this.sessieduur = sessieduur;
		this.afstand = afstand;
		this.snelheid = snelheid;
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

	public int getSessieduur() {
		return sessieduur;
	}

	public void setSessieduur(int sessieduur) {
		this.sessieduur = sessieduur;
	}

	public int getAfstand() {
		return afstand;
	}

	public void setAfstand(int afstand) {
		this.afstand = afstand;
	}

	public int getSnelheid() {
		return snelheid;
	}

	public void setSnelheid(int snelheid) {
		this.snelheid = snelheid;
	}
	
	
}
