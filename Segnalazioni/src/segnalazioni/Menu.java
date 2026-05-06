package segnalazioni;

import java.util.Scanner;

public class Menu {
	private ComuneService comuneService;
	
	public Menu(ComuneService comuneService) {
		this.comuneService = comuneService;
		comuneService.aggiungiDaCsv();
	}
	
	public void start() {
		int scelta = -1;
		Scanner in = new Scanner(System.in);
		while (scelta !=0) {
			System.out.println("\nBenvenuto nel sistema di segnalazioni del comune!");
			System.out.println("\nScegli un'opzione:");
			System.out.println("1. Visualizza tutte le segnalazioni");
			System.out.println("2. Cerca segnalazione per cittadino");
			System.out.println("3. Cerca segnalazione per parola in descrizione");
			System.out.println("4. Cerca segnalazione per prefisso codice");
			System.out.println("5. Visualizza segnalazioni aperte");
			System.out.println("6. Visualizza segnalazioni urgenti");
			System.out.println("7. Filtra segnalazioni per categoria");
			System.out.println("8. Filtra segnalazioni per zona");
			System.out.println("9. Filtra segnalazioni per stato");
			System.out.println("10. Calcola totale giorni aperti delle segnalazioni aperte");
			System.out.println("11. Conta segnalazioni aperte");
			System.out.println("12. Cambia stato di una segnalazione");
			System.out.println("13. Inserisci nuova segnalazione");
			System.out.println("0. Esci");
			System.out.print("Scelta: ");
			scelta = in.nextInt();
			in.nextLine();
			switch (scelta) {
			case 1:
				comuneService.visualizzaSegnalazioni();;
				break;
			case 2:
				for (Segnalazione s : comuneService.cercaSegnalazioniPerCittadino()) {
					System.out.println("\n");
					s.stampaDettagli();;
				}
				break;
			case 3:
				for (Segnalazione s : comuneService.cercaSegnalazioniPerParolaChiave()) {
					System.out.println("\n");
					s.stampaDettagli();
				}
				break;
			case 4:
				for (Segnalazione s : comuneService.cercaSegnalazioniPerPrefisso()) {
					System.out.println("\n");
					s.stampaDettagli();
				}
				break;
			case 5:
				for (Segnalazione s : comuneService.visualizzaSegnalazioniAperte()) {
					System.out.println("\n");
					s.stampaDettagli();
				}
				break;
			case 6:
				for (Segnalazione s : comuneService.visualizzaSegnalazioniUrgenti()) {
					System.out.println("\n");
					s.stampaDettagli();
				}
				break;
			case 7:
				for (Segnalazione s : comuneService.filtraSegnalazioniPerCategoria()) {
					System.out.println("\n");
					s.stampaDettagli();
				}
				break;
			case 8:
				for (Segnalazione s : comuneService.filtraSegnalazioniPerZona()) {
					System.out.println("\n");
					s.stampaDettagli();
				}
				break;
			case 9:
				for (Segnalazione s : comuneService.filtraSegnalazioniPerStato()) {
					System.out.println("\n");
					s.stampaDettagli();
				}
				break;
			case 10:
				System.out.println("Totale giorni aperti delle segnalazioni aperte: " + comuneService.totaleGiorniAperti());
				break;
			case 11:
				System.out.println("Totale segnalazioni aperte: " + comuneService.contaSegnalazioniAperte());
				break;
			case 12:
				comuneService.cambiaStatoSegnalazione();
				break;
			case 13:
				comuneService.aggiungiSegnalazione();
				break;
			case 0:
				System.out.println("\nArrivederci!");
				break;
			default:
				System.out.println("\nScelta non valida. Riprova.");
			}
		}
		in.close();
	}
}
