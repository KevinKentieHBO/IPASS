package nl.hu.ipass.prestatiesysteem.persistentie.objecten;

public class Sporter {
	private int sportersnummer;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private String e_mail;
	private String wachtwoord;
	private int telefoonnummer;
	private String geboortedatum;
	private int gewicht;
	
	public Sporter(int sportersnummer, String voornaam, String tussenvoegsel, String achternaam, String e_mail,
			String wachtwoord, int telefoonnummer, String geboortedatum, int gewicht) {
		super();
		this.sportersnummer = sportersnummer;
		this.voornaam = voornaam;
		this.tussenvoegsel = tussenvoegsel;
		this.achternaam = achternaam;
		this.e_mail = e_mail;
		this.wachtwoord = wachtwoord;
		this.telefoonnummer = telefoonnummer;
		this.geboortedatum = geboortedatum;
		this.gewicht = gewicht;
	}

	public int getSportersnummer() {
		return sportersnummer;
	}

	public void setSportersnummer(int sportersnummer) {
		this.sportersnummer = sportersnummer;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public int getTelefoonnummer() {
		return telefoonnummer;
	}

	public void setTelefoonnummer(int telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	public String getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(String geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public int getGewicht() {
		return gewicht;
	}

	public void setGewicht(int gewicht) {
		this.gewicht = gewicht;
	}
	
	

}
