# Segnalazioni — Gestione console delle segnalazioni del comune

Applicazione Java a riga di comando per la gestione di segnalazioni comunali. Legge segnalazioni da un file CSV, mantiene i record in memoria, permette ricerche e filtri, consente di modificare lo stato delle segnalazioni e di aggiungerne di nuove salvando sul file.

Il progetto è pensato come esempio didattico per I/O su file, enum, programmazione orientata agli oggetti e interazione tramite console in Java.

## Funzionalità principali
- Caricamento delle segnalazioni da file CSV
- Visualizzazione di tutte le segnalazioni
- Ricerca per cittadino, parola nella descrizione o prefisso del codice
- Visualizzazione delle segnalazioni aperte e urgenti
- Filtri per categoria, zona e stato
- Calcolo del totale dei giorni aperti e conteggio delle segnalazioni aperte
- Cambio di stato di una segnalazione con aggiornamento del file CSV
- Inserimento di nuove segnalazioni (append sul CSV)

## Requisiti
- Java 8 o successivo (JDK installato)
- Facoltativo: IDE come Eclipse

## File CSV atteso
Il programma si aspetta un file CSV con 10 colonne nell'ordine seguente (senza spazi aggiuntivi tra le virgole):

id,codice,cittadino,zona,descrizione,categoria,priorita,stato,giorniAperti,urgente

- id: stringa (identificativo univoco)
- codice: codice della segnalazione (es. SEG-STR-01)
- cittadino: nome del segnalante
- zona: area/indirizzo della segnalazione
- descrizione: testo descrittivo del problema
- categoria: una delle enum: STRADE, ILLUMINAZIONE, RIFIUTI, VERDE_PUBBLICO, SICUREZZA, RUMORE
- priorita: una delle enum: BASSA, MEDIA, ALTA, URGENTE
- stato: una delle enum: APERTA, IN_LAVORAZIONE, RISOLTA, CHIUSA
- giorniAperti: intero (giorni trascorsi da apertura)
- urgente: boolean (`true` o `false`)

Nota: il codice attuale usa `String.split(",")` per parsare il CSV. Se i campi (ad es. la descrizione) contengono virgole o sono quotati, lo split semplice potrebbe rompere le colonne. Per dati reali è consigliabile usare una libreria CSV robusta (OpenCSV o Apache Commons CSV).

## Come compilare ed eseguire da terminale
Dalla cartella radice del progetto (`/home/cloud/git/SegnalazioniComune/Segnalazioni`):

```sh
# creare la cartella bin e compilare
mkdir -p bin
javac -d bin src/segnalazioni/*.java

# eseguire (sostituire path/to/file.csv con il percorso reale del CSV)
java -cp bin segnalazioni.Segnalazioni path/to/file.csv
```

Esempio se il file CSV si trova nella root del progetto e si chiama `segnalazioni.csv`:

```sh
java -cp bin segnalazioni.Segnalazioni segnalazioni.csv
```

Il programma apre un menu interattivo in console. La classe `Segnalazioni` si aspetta come argomento il percorso del file CSV.

## Come eseguire in Eclipse
1. Importa il progetto in Eclipse come "Existing Java Project" o crea un nuovo progetto Java e copia `src`.
2. Crea una Run Configuration per la classe `segnalazioni.Segnalazioni`.
3. Nella scheda Arguments → Program arguments, inserisci il percorso del file CSV (es. `segnalazioni.csv`).
4. Avvia la configurazione.

## Panoramica delle classi
- `Segnalazioni` — classe `main` che avvia il menu
- `Menu` — interfaccia console e ciclo principale
- `ComuneService` — logica di caricamento, ricerca, filtraggio, modifica e salvataggio delle segnalazioni
- `Segnalazione` — modello della segnalazione (estende `Richiesta`)
- `Richiesta` — classe base con campi comuni (id, cittadino, descrizione)
- `CategoriaSegnalazione`, `Priorita`, `StatoSegnalazione` — enum per categorie, priorità e stato

## Dettagli implementativi importanti
- `ComuneService.aggiungiDaCsv()` legge tutte le righe e costruisce oggetti `Segnalazione` solo se la riga contiene esattamente 10 colonne.
- `ComuneService.cambiaStatoSegnalazione()` aggiorna il file leggendo tutte le righe in memoria e riscrivendole sostituendo il campo `stato` per la segnalazione modificata.
- `ComuneService.aggiungiSegnalazione()` aggiunge la nuova segnalazione alla lista in memoria e la appende al file CSV con modalità append (`FileWriter(path, true)`).

## Limitazioni e suggerimenti di miglioramento
- Usa un parser CSV robusto (OpenCSV o Apache Commons CSV) per gestire campi con virgole e campi quotati.
- Aggiungi validazione più rigorosa sui campi in ingresso (es. formati, enum non validi).
- Gestione degli errori: attualmente si stampano stacktrace su console; si potrebbe introdurre logging strutturato (SLF4J + Logback) e messaggi utente più chiari.
- Aggiungere test unitari per `ComuneService` e le operazioni di I/O.
- Considerare una persistenza più solida (database o file JSON) per evitare problemi di concorrenza o corruzione del CSV.

## Esempio di uso del menu (opzioni principali)
- Visualizza tutte le segnalazioni
- Cerca segnalazione per cittadino
- Cerca per parola chiave nella descrizione
- Cerca per prefisso codice
- Visualizza aperte / urgenti
- Filtra per categoria / zona / stato
- Calcola totale giorni aperti
- Cambia stato di una segnalazione (aggiorna il CSV)
- Inserisci nuova segnalazione (salva sul CSV)
