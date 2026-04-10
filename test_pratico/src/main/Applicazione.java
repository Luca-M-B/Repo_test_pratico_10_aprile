package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import database.*;
import classi.*;
import decorator.UtentePro;
import facade.GestoreApplicazione;
import observator.*;
import strategy.*;

public class Applicazione {

    private static boolean puoUsareSpedizioneRapida(Utente utente) {
        return (utente instanceof UtentePro) || (utente instanceof Admin);
    }

    private static void stampaUtenti(List<Utente> listaUtenti) {

        for (int i = 0; i < listaUtenti.size(); i++) {

            System.out.println((i + 1) + " - " +
                    listaUtenti.get(i).getNomeUtente() +
                    " (" + listaUtenti.get(i).getTipoUtente() + ")");
        }
    }

    public static void main(String[] args) {

        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);

        GestoreApplicazione gestoreApplicazione = new GestoreApplicazione();
        GestoreVendite gestoreVendite = new GestoreVendite();

        gestoreVendite.aggiungiOsservatore(new OsservatoreAdmin("Admin1"));

        UtenteDAO utenteDAO = new UtenteDAO();
        ProdottoDAO prodottoDAO = new ProdottoDAO();
        VenditaDAO venditaDAO = new VenditaDAO();

        // carica utenti dal database
        ArrayList<Utente> listaUtenti = new ArrayList<>(utenteDAO.caricaUtenti());

        boolean continua = true;

        while (continua) {

            System.out.println("\n\tMENU GESTIONALE");
            System.out.println("1. Crea utente");
            System.out.println("2. Inserisci vendita");
            System.out.println("0. Esci\n");
            System.out.print("Scelta: ");

            int scelta = scannerInt.nextInt();

            switch (scelta) {

                case 1:

                    System.out.println("\nSeleziona tipo utente:");
                    System.out.println("1 - BASE");
                    System.out.println("2 - PRO");
                    System.out.println("3 - ADMIN\n");
                    System.out.print("Scelta: ");

                    int sceltaTipo = scannerInt.nextInt();

                    String tipo;

                    switch (sceltaTipo) {
                        case 1:
                            tipo = "BASE";
                            break;
                        case 2:
                            tipo = "PRO";
                            break;
                        case 3:
                            tipo = "ADMIN";
                            break;
                        default:
                            System.out.println("Scelta non valida verra creato utente BASE");
                            tipo = "BASE";
                    }

                    System.out.println("Nome utente: ");
                    String nome = scannerString.nextLine();

                    int livello = 0;

                    if (tipo.equals("ADMIN")) {
                        System.out.println("Livello admin: ");
                        livello = scannerInt.nextInt();
                    }

                    Utente utente = gestoreApplicazione.creaUtente(tipo, nome, livello);
                    listaUtenti.add(utente);
                    utenteDAO.salvaUtente(utente); // salvataggio db

                    System.out.println("Utente creato: " + utente.getNomeUtente() +
                            " (" + utente.getTipoUtente() + ")");

                    break;

                case 2:

                    if (listaUtenti.isEmpty()) {
                        System.out.println("Nessun utente disponibile.");
                        break;
                    }

                    // scelta utente
                    System.out.println("\nSeleziona utente:");
                    stampaUtenti(listaUtenti);

                    int indiceUtente;

                    do {
                        indiceUtente = scannerInt.nextInt();
                    } while (indiceUtente < 1 || indiceUtente > listaUtenti.size());

                    Utente utenteCorrente = listaUtenti.get(indiceUtente - 1);

                    System.out.println("\nTipo spedizione:");
                    System.out.println("1 - STANDARD");
                    System.out.println("2 - RAPIDA\n");

                    int sceltaSpedizione = scannerInt.nextInt();

                    String tipoSpedizione;

                    switch (sceltaSpedizione) {
                        case 1:
                            tipoSpedizione = "STANDARD";
                            break;
                        case 2:
                            tipoSpedizione = "RAPIDA";
                            break;
                        default:
                            System.out.println("Scelta non valida. Impostata STANDARD.");
                            tipoSpedizione = "STANDARD";
                    }

                    Spedizione spedizione;

                    if (tipoSpedizione.equalsIgnoreCase("RAPIDA") &&
                            !puoUsareSpedizioneRapida(utenteCorrente)) {

                        System.out.println("Spedizione rapida non consentita. Uso STANDARD.");
                        spedizione = new Spedizione("STANDARD", 0);

                    } else {
                        spedizione = new Spedizione(tipoSpedizione, 0);
                    }

                    Vendita vendita = new Vendita(spedizione);

                    boolean continuaProdotti = true;

                    while (continuaProdotti) {

                        // carica prodotti dal database
                        ArrayList<Prodotto> listaProdotti = new ArrayList<>(prodottoDAO.caricaProdotti());

                        if (listaProdotti.isEmpty()) {
                            System.out.println("Nessun prodotto disponibile nel database.");
                            break;
                        }

                        System.out.println("\nSeleziona prodotto:");

                        for (int i = 0; i < listaProdotti.size(); i++) {
                            System.out.println((i + 1) + " - " + listaProdotti.get(i).getNomeProdotto());
                        }

                        int sceltaProdotto;

                        do {
                            sceltaProdotto = scannerInt.nextInt();
                        } while (sceltaProdotto < 1 || sceltaProdotto > listaProdotti.size());

                        Prodotto prodotto = listaProdotti.get(sceltaProdotto - 1);

                        int quantita;
                        do {
                            System.out.println("Quantità: ");
                            quantita = scannerInt.nextInt();
                        } while (quantita <= 0);

                        vendita.aggiungiProdotto(prodotto, quantita);

                        scannerString.nextLine();

                        int risposta;

                        do {
                            System.out.println("Aggiungere altro prodotto?");
                            System.out.println("1 - SI");
                            System.out.println("2 - NO");
                            System.out.println("Scelta: ");

                            risposta = scannerInt.nextInt();

                        } while (risposta != 1 && risposta != 2);

                        if (risposta == 2) {
                            continuaProdotti = false;
                        }
                    }

                    // strategy automatica
                    StrategiaCalcoloVendita strategia;

                    if (utenteCorrente instanceof Admin) {

                        double sconto;
                        do {
                            System.out.println("Sconto admin (0 - 1): ");
                            sconto = scannerInt.nextDouble();
                        } while (sconto < 0 || sconto > 1);

                        strategia = new CalcoloVenditaAdmin(sconto);

                    } else if (utenteCorrente instanceof UtentePro) {

                        strategia = new CalcoloVenditaPro();

                    } else {

                        strategia = new CalcoloVenditaBase();
                    }

                    vendita.setStrategiaCalcoloVendita(strategia);
                    vendita.calcolaTotale();

                    vendita.stampaDettagliVendita();
                    venditaDAO.salvaVendita(vendita); // salvataggio vendita db

                    gestoreVendite.registraVendita(vendita);

                    break;

                case 0:
                    continua = false;
                    System.out.println("Uscita dal sistema.");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }
        }

        scannerInt.close();
        scannerString.close();
    }
}