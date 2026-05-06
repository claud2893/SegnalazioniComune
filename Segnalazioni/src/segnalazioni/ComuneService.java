package segnalazioni;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ComuneService {
	private ArrayList<Segnalazione> segnalazioni;
	private String path;
	
	public ComuneService(String path) {
		this.segnalazioni = new ArrayList<Segnalazione>();
		this.path = path;
	}
	
	public void aggiungiDaCsv() {
		String line = "";
		String separatore = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			while ((line = br.readLine()) != null) {
            	if (line.trim().isEmpty()) {
                    continue; 
                }
				String[] dati = line.split(separatore);
				if (dati.length == 10) {
					segnalazioni.add(new Segnalazione(dati[0], dati[2], 
							dati[4], dati[1], dati[3], CategoriaSegnalazione.valueOf(dati[5]), 
							Priorita.valueOf(dati[6]), StatoSegnalazione.valueOf(dati[7]), Integer.parseInt(dati[8]), 
							Boolean.parseBoolean(dati[9])));
				}
			}
			System.out.println("Segnalazioni caricate con successo!");
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file.");
			e.printStackTrace();
		}
	}	
	
	public void visualizzaSegnalazioni() {
		for (Segnalazione segnalazione : segnalazioni) {
			System.out.println("\n");
			segnalazione.stampaDettagli();
		}
	}
	
	public ArrayList<Segnalazione> cercaSegnalazioniPerCittadino() {
		Scanner in = new Scanner(System.in);
		ArrayList<Segnalazione> segnalazioniCittadino = new ArrayList<>();
		System.out.println("Inserisci il cittadino da applicare come filtro per la ricerca: ");
		String cittadino = in.nextLine();
		while (cittadino.trim().isEmpty()) {
			System.out.println("Il nome del cittadino non può essere vuoto. Riprovare o digitare 'annulla' per uscire: ");
			cittadino = in.nextLine();
			if (cittadino.equalsIgnoreCase("annulla")) {
				return null;
			}
		}
		for (Segnalazione segnalazione : segnalazioni) {
			if (segnalazione.getCittadino().toLowerCase().contains(cittadino.toLowerCase())) {
				segnalazioniCittadino.add(segnalazione);
			}
			/*
			 * ArrayList<String> nomeScomposto = new
			 * ArrayList<>(Arrays.asList(segnalazione.getCittadino().split(" ")));
			 * nomeScomposto.add(segnalazione.getCittadino()); for (String parte :
			 * nomeScomposto) { if (parte.equalsIgnoreCase(cittadino)) {
			 * segnalazioniCittadino.add(segnalazione); } }
			 */
		}
		if (segnalazioniCittadino.isEmpty()) {
			System.out.println("Nessuna segnalazione trovata per il cittadino: " + cittadino);
		}
		return segnalazioniCittadino;
	}
	
	public ArrayList<Segnalazione> cercaSegnalazioniPerParolaChiave() {
		Scanner in = new Scanner(System.in);
		ArrayList<Segnalazione> segnalazioniParolaChiave = new ArrayList<>();
		System.out.println("Inserisci la parola chiave da applicare come filtro per la ricerca: ");
		String parolaChiave = in.nextLine();
		while (parolaChiave.trim().isEmpty()) {
			System.out.println("La parola chiave non può essere vuota. Riprovare o digitare 'annulla' per uscire: ");
			parolaChiave = in.nextLine();
			if (parolaChiave.equalsIgnoreCase("annulla")) {
				return null;
			}
		}
		for (Segnalazione segnalazione : segnalazioni) {
			if (segnalazione.getDescrizione().toLowerCase().contains(parolaChiave.toLowerCase())) {
				segnalazioniParolaChiave.add(segnalazione);
			}
		}
		if (segnalazioniParolaChiave.isEmpty()) {
			System.out.println("Nessuna segnalazione trovata per la parola chiave: " + parolaChiave);
		}
		return segnalazioniParolaChiave;
	}
	
	public ArrayList<Segnalazione> cercaSegnalazioniPerPrefisso() {
		Scanner in = new Scanner(System.in);
		ArrayList<Segnalazione> segnalazioniPrefisso = new ArrayList<>();
		System.out.println("Inserisci il prefisso da applicare come filtro per la ricerca: ");
		String prefisso = in.nextLine();
		while (prefisso.trim().isEmpty()) {
			System.out.println("Il prefisso non può essere vuoto. Riprovare o digitare 'annulla' per uscire: ");
			prefisso = in.nextLine();
			if (prefisso.equalsIgnoreCase("annulla")) {
				return null;
			}
		}
		for (Segnalazione segnalazione : segnalazioni) {
			String inizioCodice = segnalazione.getCodice().split("-")[0];
			if (inizioCodice.toLowerCase().startsWith(prefisso.toLowerCase())) {
				segnalazioniPrefisso.add(segnalazione);
			}
		}
		if (segnalazioniPrefisso.isEmpty()) {
			System.out.println("Nessuna segnalazione trovata per il prefisso: " + prefisso);
		}
		return segnalazioniPrefisso;
	}
	
	public ArrayList<Segnalazione> visualizzaSegnalazioniAperte() {
		ArrayList<Segnalazione> segnalazioniAperte = new ArrayList<>();
		for (Segnalazione segnalazione : segnalazioni) {
			if (segnalazione.getStato() == StatoSegnalazione.APERTA || segnalazione.getStato() == StatoSegnalazione.IN_LAVORAZIONE) {
				segnalazioniAperte.add(segnalazione);
			}
		}
		if (segnalazioniAperte.isEmpty()) {
			System.out.println("Nessuna segnalazione aperta trovata.");
		}
		return segnalazioniAperte;
	}
	
	public ArrayList<Segnalazione> visualizzaSegnalazioniUrgenti() {
		ArrayList<Segnalazione> segnalazioniUrgenti = new ArrayList<>();
		for (Segnalazione segnalazione : segnalazioni) {
			if (segnalazione.getUrgente().equals("Urgente")) {
				segnalazioniUrgenti.add(segnalazione);
			}
		}
		if (segnalazioniUrgenti.isEmpty()) {
			System.out.println("Nessuna segnalazione urgente trovata.");
		}
		return segnalazioniUrgenti;
	}
	
	public ArrayList<Segnalazione> filtraSegnalazioniPerCategoria() {
		Scanner in = new Scanner(System.in);
		ArrayList<Segnalazione> segnalazioniCategoria = new ArrayList<>();
		System.out.println("Inserisci la categoria da applicare come filtro per la ricerca: "
				+ "\n1 - STRADE\n2 - ILLUMINAZIONE\n3 - RIFIUTI \n4 - VERDE_PUBBLICO\n5 - SICUREZZA\nLa tua scelta: ");
		int scelta = in.nextInt();
		while (scelta < 1 || scelta > 5) {
			System.out.println("Scelta non valida. Riprova");
			scelta = in.nextInt();
		}
		CategoriaSegnalazione categoria = null;
		switch(scelta) {
		case 1:
			categoria = CategoriaSegnalazione.STRADE;
			break;
		case 2:
			categoria = CategoriaSegnalazione.ILLUMINAZIONE;
			break;
		case 3:
			categoria = CategoriaSegnalazione.RIFIUTI;
			break;
		case 4:
			categoria = CategoriaSegnalazione.VERDE_PUBBLICO;
			break;
		case 5:
			categoria = CategoriaSegnalazione.SICUREZZA;
			break;
		default:
			System.out.println("Scelta non valida. Riprova");
			return null;
		}
		for (Segnalazione segnalazione : segnalazioni) {
			if (segnalazione.getCategoria() == categoria) {
				segnalazioniCategoria.add(segnalazione);
			}
		}
		if (segnalazioniCategoria.isEmpty()) {
			System.out.println("Nessuna segnalazione trovata per la categoria: " + categoria);
		}
		return segnalazioniCategoria;
	}
	
	public ArrayList<Segnalazione> filtraSegnalazioniPerZona() {
		Scanner in = new Scanner(System.in);
		ArrayList<Segnalazione> segnalazioniZona = new ArrayList<>();
		System.out.println("Inserisci la zona da applicare come filtro per la ricerca: ");
		String zona = in.nextLine();
		while (zona.trim().isEmpty()) {
			System.out.println("La zona non può essere vuota. Riprovare o digitare 'annulla' per uscire: ");
			zona = in.nextLine();
			if (zona.equalsIgnoreCase("annulla")) {
				return null;
			}
		}
		for (Segnalazione segnalazione : segnalazioni) {
			if (segnalazione.getZona().toLowerCase().contains(zona.toLowerCase())) {
				segnalazioniZona.add(segnalazione);
			}
		}
		if (segnalazioniZona.isEmpty()) {
			System.out.println("Nessuna segnalazione trovata per la zona: " + zona);
		}
		return segnalazioniZona;
	}
	
	public ArrayList<Segnalazione> filtraSegnalazioniPerStato(){
		Scanner in = new Scanner(System.in);
		ArrayList<Segnalazione> segnalazioniStato = new ArrayList<>();
		System.out.println("Inserisci lo stato da applicare come filtro per la ricerca: "
				+ "\n1 - APERTA\n2 - IN_LAVORAZIONE\n3 - RISOLTA\n4 - CHIUSA\nLa tua scelta: ");
		int scelta = in.nextInt();
		while (scelta < 1 || scelta > 4) {
			System.out.println("Scelta non valida. Riprova");
			scelta = in.nextInt();
		}
		StatoSegnalazione stato = null;
		switch (scelta) {
			case 1:
				stato = StatoSegnalazione.APERTA;
				break;
			case 2:
				stato = StatoSegnalazione.IN_LAVORAZIONE;
				break;
			case 3:
				stato = StatoSegnalazione.RISOLTA;
				break;
			case 4:
				stato = StatoSegnalazione.CHIUSA;
				break;
			default:
				System.out.println("Scelta non valida. Riprova");
				return null;
		}
		for (Segnalazione segnalazione : segnalazioni) {
			if (segnalazione.getStato() == stato) {
				segnalazioniStato.add(segnalazione);
			}
		}
		if (segnalazioniStato.isEmpty()) {
			System.out.println("Nessuna segnalazione trovata per lo stato: " + stato);
		}
		return segnalazioniStato;
	}
	
	public int totaleGiorniAperti() {
		int totaleGiorni = 0;
		for (Segnalazione segnalazione : segnalazioni) {
			if (segnalazione.getStato() == StatoSegnalazione.APERTA || segnalazione.getStato() == StatoSegnalazione.IN_LAVORAZIONE) {
				totaleGiorni += segnalazione.getGiorniAperti();
			}
		}
		return totaleGiorni;
	}
	
	public int contaSegnalazioniAperte() {
		int count = 0;
		for (Segnalazione segnalazione : segnalazioni) {
			if (segnalazione.getStato() == StatoSegnalazione.APERTA || segnalazione.getStato() == StatoSegnalazione.IN_LAVORAZIONE) {
				count++;
			}
		}
		return count;
	}
	
	public void cambiaStatoSegnalazione() {
		Boolean trovata = false;
		Scanner in = new Scanner(System.in);
		System.out.println("Inserisci l'ID della segnalazione di cui vuoi cambiare lo stato: ");
		String id = in.nextLine();
		while (id.trim().isEmpty()) {
			System.out.println("L'ID non può essere vuoto. Riprovare o digitare 'annulla' per uscire: ");
			id = in.nextLine();
			if (id.equalsIgnoreCase("annulla")) {
				return;
			}
		}
		for (Segnalazione segnalazione : segnalazioni) {
			if (segnalazione.getId().equals(id)) {
				trovata = true;
				System.out.println("La segnalazione è attualmente nello stato: " + segnalazione.getStato());
				System.out.println("Inserisci il numero corrispondente al nuovo stato: "
						+ "\n1 - APERTA\n2 - IN_LAVORAZIONE\n3 - RISOLTA\n4 - CHIUSA\nLa tua scelta: ");
				int scelta = in.nextInt();
				while (scelta < 1 || scelta > 4) {
					System.out.println("Scelta non valida. Riprova");
					scelta = in.nextInt();
				}
				StatoSegnalazione nuovoStato = null;
				switch (scelta) {
					case 1:
						nuovoStato = StatoSegnalazione.APERTA;
						break;
					case 2:
						nuovoStato = StatoSegnalazione.IN_LAVORAZIONE;
						break;
					case 3:
						nuovoStato = StatoSegnalazione.RISOLTA;
						break;
					case 4:
						nuovoStato = StatoSegnalazione.CHIUSA;
						break;
					default:
						System.out.println("Scelta non valida. Riprova");
						return;
				}
				segnalazione.setStato(nuovoStato);
				List<String> linee = new ArrayList<>();
				try {
					linee = Files.readAllLines(Paths.get(path));
				} catch (IOException e) {
					System.out.println("Errore nella lettura del file.");
					e.printStackTrace();
				}
				try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
					for (String linea : linee) {
						String[] fields = linea.split(",");
						if (fields[0].equals(id)) {
							fields[7] = nuovoStato.toString();
							linea = String.join(",", fields);
						}
						bw.write(linea);
						bw.newLine();
					}
				} catch (IOException e) {
					System.out.println("Errore nella scrittura del file.");
					e.printStackTrace();
				}
				System.out.println("Stato della segnalazione con ID " + id + " aggiornato a: " + nuovoStato);
				return;
			}
		}
		if (!trovata) {
			System.out.println("Nessuna segnalazione trovata con ID: " + id);
		}
	}
	
	public void aggiungiSegnalazione() {
		Scanner in = new Scanner(System.in);
		System.out.println("Inserisci i dati della segnalazione:");
		String line = "";
		int count = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			br.readLine();
			while ((line = br.readLine()) != null) {
            	if (line.trim().isEmpty()) {
                    continue; 
                }
				count++;
			}
			System.out.println("Segnalazioni caricate con successo!");
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file.");
			e.printStackTrace();
		}
		String id = (String.valueOf(count + 1));
		String codice = "SEG-";
		System.out.println("Cittadino: ");
		String cittadino = in.nextLine();
		System.out.println("Zona: ");
		String zona = in.nextLine();
		System.out.println("Descrizione: ");
		String descrizione = in.nextLine();
		System.out.println("Inserisci il numero corrispondente alla categoria: "
				+ "\n1 - STRADE\n2 - ILLUMINAZIONE\n3 - RIFIUTI \n4 - VERDE_PUBBLICO"
				+ "\n5 - SICUREZZA\nLa tua scelta: ");
		int scelta = in.nextInt();
		while (scelta < 1 || scelta > 5) {
			System.out.println("Scelta non valida. Riprova");
			scelta = in.nextInt();
		}
		CategoriaSegnalazione categoria = null;
		switch(scelta) {
		case 1:
			categoria = CategoriaSegnalazione.STRADE;
			codice += "STR-";
			break;
		case 2:
			categoria = CategoriaSegnalazione.ILLUMINAZIONE;
			codice += "ILL-";
			break;
		case 3:
			categoria = CategoriaSegnalazione.RIFIUTI;
			codice += "RIF-";
			break;
		case 4:
			categoria = CategoriaSegnalazione.VERDE_PUBBLICO;
			codice += "VER-";
			break;
		case 5:
			categoria = CategoriaSegnalazione.SICUREZZA;
			codice += "SIC-";
			break;
		default:
			System.out.println("Scelta non valida. Riprova");
			return;
		}
		codice += "0"+id;
		System.out.println("Inserisci il numero corrispondente alla priorità: "
				+ "\n1 - BASSA\n2 - MEDIA\n3 - ALTA\n4 - URGENTE\nLa tua scelta: ");
		scelta = in.nextInt();
		while (scelta < 1 || scelta > 4) {
			System.out.println("Scelta non valida. Riprova");
			scelta = in.nextInt();
		}
		Priorita priorita = null;
		switch (scelta) {
			case 1:
				priorita = Priorita.BASSA;
				break;
			case 2:
				priorita = Priorita.MEDIA;
				break;
			case 3:
				priorita = Priorita.ALTA;
				break;
			case 4:
				priorita = Priorita.URGENTE;
				break;
			default:
				System.out.println("Scelta non valida. Riprova");
				return;
		}
		System.out.println("Inserisci il numero corrispondente allo stato: "
				+ "\n1 - APERTA\n2 - IN_LAVORAZIONE\n3 - RISOLTA\n4 - CHIUSA\nLa tua scelta: ");
		scelta = in.nextInt();
		while (scelta < 1 || scelta > 4) {
			System.out.println("Scelta non valida. Riprova");
			scelta = in.nextInt();
		}
		StatoSegnalazione stato = null;
		switch (scelta) {
			case 1:
				stato = StatoSegnalazione.APERTA;
				break;
			case 2:
				stato = StatoSegnalazione.IN_LAVORAZIONE;
				break;
			case 3:
				stato = StatoSegnalazione.RISOLTA;
				break;
			case 4:
				stato = StatoSegnalazione.CHIUSA;
				break;
			default:
				System.out.println("Scelta non valida. Riprova");
				return;
		}
		System.out.println("Giorni aperti: ");
		int giorniAperti = in.nextInt();
		System.out.println("La segnalazione è urgente?\n1 - SI\n2 - NO\nLa tua scelta: ");
		scelta = in.nextInt();
		while (scelta < 1 || scelta > 2) {
			System.out.println("Scelta non valida. Riprova");
			scelta = in.nextInt();
		}
		Boolean urgente = null;
		if (scelta == 1) {
			urgente = true;
		} else {
			urgente = false;
		}
		Segnalazione segnalazione = new Segnalazione(id, codice, cittadino, zona, descrizione, categoria, priorita, stato, giorniAperti, urgente);
		segnalazioni.add(segnalazione);
		 try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
	            StringBuilder sb = new StringBuilder();
	            sb.append(id).append(",")
	              .append(codice).append(",")
	              .append(cittadino).append(",")
	              .append(zona).append(",")
	              .append(descrizione).append(",")
	              .append(categoria).append(",")
	              .append(priorita).append(",")
	              .append(stato).append(",")
	              .append(giorniAperti).append(",")
	              .append(urgente);
	            bw.write(sb.toString());
	            bw.newLine();
	        } catch (IOException e) {
	            System.out.println("Errore nella scrittura del file.");
	            e.printStackTrace();
	        }
		System.out.println("Segnalazione aggiunta con successo!");
	}
}