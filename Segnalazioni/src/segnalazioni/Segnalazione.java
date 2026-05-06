package segnalazioni;

public class Segnalazione extends Richiesta{
	private String codice;
	private String zona;
	private CategoriaSegnalazione categoria;
	private Priorita priorita;
	private StatoSegnalazione stato;
	private int giorniAperti;
	private Boolean urgente;
	
	public Segnalazione(String id, String cittadino, String descrizione, String codice, String zona, CategoriaSegnalazione categoria, Priorita priorita, StatoSegnalazione stato, int giorniAperti, Boolean urgente) {
		super(id, cittadino, descrizione);
		this.setCodice(codice);
		this.setZona(zona);
		this.setCategoria(categoria);
		this.setPriorita(priorita);
		this.setStato(stato);
		this.setGiorniAperti(giorniAperti);
		this.setUrgente(urgente);
	}
	
	public void setCodice(String codice) {
		this.codice = codice;
	}
	
	public void setZona(String zona) {
		this.zona = zona;
	}
	
	public void setCategoria(CategoriaSegnalazione categoria) {
		this.categoria = categoria;
	}
	
	public void setPriorita(Priorita priorita) {
		this.priorita = priorita;
	}
	
	public void setStato(StatoSegnalazione stato) {
		this.stato = stato;
	}
	
	public void setGiorniAperti(int giorniAperti) {
		this.giorniAperti = giorniAperti;
	}
	
	public void setUrgente(Boolean urgente) {
		this.urgente = urgente;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public String getZona() {
		return zona;
	}
	
	public CategoriaSegnalazione getCategoria() {
		return categoria;
	}
	
	public Priorita getPriorita() {
		return priorita;
	}
	
	public StatoSegnalazione getStato() {
		return stato;
	}
	
	public int getGiorniAperti() {
		return giorniAperti;
	}
	
	public String getUrgente() {
		if (urgente) {
			return "Urgente";
		} else {
			return "Non urgente";
		}
	}
	
	@Override
	public void stampaDettagli() {
		super.stampaDettagli();
		System.out.println("Codice: " + getCodice());
		System.out.println("Zona: " + getZona());
		System.out.println("Categoria: " + getCategoria());
		System.out.println("Priorità: " + getPriorita());
		System.out.println("Stato: " + getStato());
		System.out.println("Giorni aperti: " + getGiorniAperti());
		System.out.println("Urgenza: " + getUrgente());
	}
	
	public void verificaSeAperta() {
		if (getStato() == StatoSegnalazione.APERTA || getStato() == StatoSegnalazione.IN_LAVORAZIONE) {
			System.out.println("La segnalazione è aperta.");
		} else {
			System.out.println("La segnalazione non è aperta.");
		}
	}
}
