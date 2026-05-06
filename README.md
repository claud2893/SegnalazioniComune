# HelpDesk — Applicazione console per la gestione dei ticket

Una semplice applicazione Java a riga di comando che implementa un sistema di Help Desk / gestione ticket. Legge i ticket da un file CSV, carica i ticket validi in memoria e fornisce un menu interattivo in console per cercare, filtrare, visualizzare, modificare e correggere ticket malformati.

Questo progetto è indicato come esercizio didattico per apprendere I/O su file, enum, progettazione orientata agli oggetti e interazione console in Java.

## Funzionalità
- Caricamento dei ticket da un file CSV
- Visualizzazione di tutti i ticket con i dettagli
- Ricerca ticket per utente, parola nella descrizione o prefisso del codice
- Visualizzazione dei ticket aperti (APERTO o IN_LAVORAZIONE)
- Filtraggio dei ticket per priorità o stato
- Calcolo delle ore stimate totali per i ticket aperti e conteggio dei ticket aperti
- Cambio dello stato di un ticket
- Visualizzazione dei ticket non assegnati
- Rilevamento di righe malformate durante l'import CSV e possibilità di correggerle interattivamente

## Requisiti
- Java 8 o superiore (JDK installato)
- Facoltativo: IDE come Eclipse (il progetto è strutturato per Eclipse)
- L'applicazione richiede un file CSV contenente i dati dei ticket (è incluso un esempio: `ticket.csv`).

## Struttura rilevante del repository
- `src/helpdesk/Start.java` — punto di ingresso del programma
- `src/helpdesk/Menu.java` — interfaccia console e ciclo del menu principale
- `src/helpdesk/TicketService.java` — logica dell'applicazione e caricamento CSV
- `src/helpdesk/Ticket.java` — modello Ticket (estende `Richiesta`)
- `src/helpdesk/Richiesta.java` — modello base della richiesta
- `src/helpdesk/Priorita.java`, `src/helpdesk/StatoTicket.java` — enum per priorità e stato
- `ticket.csv` — dataset di esempio usato per caricare i ticket

## Formato del file CSV
Il CSV deve contenere una riga di intestazione e poi una riga per ogni ticket con le colonne ordinate così:

id,codice,utente,reparto,descrizione,priorita,stato,oreStimate,assegnato

- id: intero (positivo). Righe con id non valido o campi critici invalidi vengono classificate come "errate".
- codice: stringa (codice del ticket)
- utente: nome del richiedente (stringa)
- reparto: reparto di riferimento (stringa)
- descrizione: descrizione del problema (stringa)
- priorita: uno dei valori enum: BASSA, MEDIA, ALTA, URGENTE
- stato: uno dei valori enum: APERTO, IN_LAVORAZIONE, RISOLTO, CHIUSO
- oreStimate: numero (double) che rappresenta le ore stimate
- assegnato: boolean (`true` o `false`)

Attenzione: il parser CSV implementato nel progetto usa `String.split(",")`. Se i campi contengono virgole o sono quotati, lo split semplice potrebbe rompere i campi. Per un uso robusto in produzione si raccomanda una libreria CSV come OpenCSV o Apache Commons CSV.

Nota: il file di esempio incluso (`ticket.csv`) può contenere descrizioni o testo non adatti alla pubblicazione pubblica. Controlla e pulisci i dati prima di condividerli pubblicamente.

## Come compilare ed eseguire (da linea di comando)
Dalla radice del progetto (`/path/to/HelpDesk`):

```sh
# creare la cartella bin e compilare
mkdir -p bin
javac -d bin src/helpdesk/*.java

# eseguire (sostituire il percorso del CSV se necessario)
java -cp bin helpdesk.Start ticket.csv
```

La classe principale `Start` si aspetta esattamente un argomento in riga di comando: il percorso al file CSV.

## Come eseguire in Eclipse
1. Importa il progetto in Eclipse come progetto Java esistente oppure crea un nuovo progetto Java e copia la cartella `src`.
2. Clic destro su `Start.java` → Run As → Run Configurations.
3. Nella scheda Arguments, inserisci il percorso del file CSV in Program Arguments (es. `ticket.csv` se il file è nella root del progetto).
4. Avvia la configurazione.

## Menu interattivo (riassunto)
All'avvio il programma mostra un menu con opzioni come:
- 1: Visualizza tutti i ticket
- 2: Cerca ticket per utente
- 3: Cerca ticket nella descrizione
- 4: Cerca ticket per prefisso codice
- 5: Visualizza ticket aperti
- 6: Filtra ticket per priorità
- 7: Filtra ticket per stato
- 8: Conta totale ore stimate per ticket aperti
- 9: Conta ticket aperti
- 10: Cambia stato di un ticket
- 11: Visualizza ticket non assegnati
- 12: Visualizza ticket errati
- 13: Correggi un ticket errato
- 0: Esci

## Panoramica delle classi
- Start
  - `main(String[] args)`: avvia il programma creando `Menu` con `TicketService`
- Menu
  - gestisce l'interazione con l'utente e il ciclo del menu
- TicketService
  - carica i ticket dal CSV (`aggiungiDaCsv()`), mantiene le liste `tickets` e `errati` e fornisce metodi di ricerca, filtro, modifica e correzione
- Ticket (estende Richiesta)
  - campi: `codice`, `reparto`, `priorita`, `stato`, `oreStimate`, `assegnato` e validazioni nei setter
- Richiesta
  - campi base: `id`, `utente`, `descrizione`
- Priorita
  - enum: `BASSA, MEDIA, ALTA, URGENTE`
- StatoTicket
  - enum: `APERTO, IN_LAVORAZIONE, RISOLTO, CHIUSO`

## Note su gestione errori e limiti
- Il caricamento CSV non gestisce campi quotati o con virgole interne (usare una libreria CSV per casi reali).
- Le righe con campi critici non validi vengono messe nella lista `errati` e possono essere corrette con l'opzione 13 del menu.
- L'applicazione usa `Scanner` per input da console; per uso non interattivo si potrebbe aggiungere una modalità batch o test automatizzati.

## Miglioramenti suggeriti
- Sostituire lo split semplice con un parser CSV robusto (OpenCSV / Apache Commons CSV).
- Salvare le modifiche su file (persistenza) oppure usare un database leggero.
- Aggiungere test unitari per `TicketService` e le sue funzioni.
- Migliorare la generazione degli ID per evitare collisioni quando si correggono ticket errati.
- Aggiungere logging strutturato invece di stampe su stdout.

## Contribuire
Se vuoi contribuire, apri issue o pull request. Suggerimenti per contribuire:
- Includi test per le nuove funzionalità
- Mantieni le modifiche piccole e documentate
- Aggiorna il README se introduci nuove dipendenze o comportamenti
