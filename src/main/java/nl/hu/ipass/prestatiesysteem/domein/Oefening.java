package nl.hu.ipass.prestatiesysteem.domein;

public class Oefening {
	
	private int oefeningnummer;
	private String oefeningnaam;
	private String oefeningtype;
	private int intensiteit;
	private int calorieverbranding;
	private String afbeelding;
	
	//constructor voor het maken van een Oefening object vanuit de database
	public Oefening(int oefeningnummer, String oefeningnaam, String oefeningtype, int intensiteit,
			int calorieverbranding, String afbeelding) {
		super();
		this.oefeningnummer = oefeningnummer;
		this.oefeningnaam = oefeningnaam;
		this.oefeningtype = oefeningtype;
		this.intensiteit = intensiteit;
		this.calorieverbranding = calorieverbranding;
		this.afbeelding = afbeelding;
	}
	
	//hieronder zijn alle getters en setters te vinden
	public int getOefeningnummer() {
		return oefeningnummer;
	}

	public void setOefeningnummer(int oefeningnummer) {
		this.oefeningnummer = oefeningnummer;
	}

	public String getOefeningnaam() {
		return oefeningnaam;
	}

	public void setOefeningnaam(String oefeningnaam) {
		this.oefeningnaam = oefeningnaam;
	}

	public String getOefeningtype() {
		return oefeningtype;
	}

	public void setOefeningtype(String oefeningtype) {
		this.oefeningtype = oefeningtype;
	}

	public int getIntensiteit() {
		return intensiteit;
	}

	public void setIntensiteit(int intensiteit) {
		this.intensiteit = intensiteit;
	}

	public int getCalorieverbranding() {
		return calorieverbranding;
	}

	public void setCalorieverbranding(int calorieverbranding) {
		this.calorieverbranding = calorieverbranding;
	}

	public String getAfbeelding() {
		return afbeelding;
	}

	public void setAfbeelding(String afbeelding) {
		this.afbeelding = afbeelding;
	}
	
	

}
