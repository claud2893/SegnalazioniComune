package segnalazioni;

public class Richiesta {
	private String id;
	private String cittadino;
	private String descrizione;
	
	public Richiesta(String id, String cittadino, String descrizione) {
		this.setId(id);
		this.setCittadino(cittadino);
		this.setDescrizione(descrizione);
	}
	
	public String getId() {
		return id;
	}
	
	public String getCittadino() {
		return cittadino;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setCittadino(String cittadino) {
		this.cittadino = cittadino;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public void stampaDettagli() {
		System.out.println("ID: " + getId());
		System.out.println("Cittadino: " + getCittadino());
		System.out.println("Descrizione: " + getDescrizione());
	}
}
