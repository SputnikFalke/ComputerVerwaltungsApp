package Controller;

import DbAccess.DbAccess;
import Model.*;
import Repositorys.BestellungReopsitory;
import Repositorys.ComputerReopsitory;
import Repositorys.KundeRepository;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Controller {

    private Scanner scanner;
    private DbAccess dbAccess;
    private ComputerReopsitory computerReopsitory;
    private KundeRepository kundeRepository;
    private BestellungReopsitory bestellungReopsitory;
    private Menu menu;
    private ArrayList<Computer> computers;

    public Controller(){
        dbAccess = new DbAccess(this);
        computerReopsitory =  new ComputerReopsitory(this);
        kundeRepository = new KundeRepository(this);
        bestellungReopsitory = new BestellungReopsitory(this);
        menu = new Menu(this);
    }

    public void Run(){
        while (true){
            menu.DisplayHauptmenu();
        }
    }
    //Computer-Verwaltung-----------------------------------------------------------------------------------------//
    public void getAllComputers(){
        computers = new ArrayList<>();
        ArrayList<Document> docs = dbAccess.getAllComputers();
        for (Document doc: docs
             ) {
            computerReopsitory.getAll(doc, computers);
        }
        computerReopsitory.PrintComputers();
    }

    public Computer getComputerById(){
        Computer computer;

        System.out.println("Index des Computer eingeben: ");
        scanner =new Scanner(System.in);
        int index = scanner.nextInt();

        MongoCursor<Document> cursor = dbAccess.getComputerbyId();
        computer = computerReopsitory.getById(index, cursor);
        computerReopsitory.PrintComputers();

        return computer;
    }

    public void addComputer(){
        dbAccess.SaveComputer(computerReopsitory.save(computerReopsitory.getComputerDatafromUser()));
    }

    public void deleteComputer(){
        computers = new ArrayList<>();
        MongoCursor<Document> cursor = dbAccess.getComputerbyId();
        ArrayList<Document> docs = dbAccess.getAllComputers();
        for (Document doc: docs
             ) {
            computerReopsitory.getAll(doc, computers);
        }
        computerReopsitory.PrintComputers();
        ObjectId id = computerReopsitory.delete(cursor);
        dbAccess.DeleteComputer(id);
    }

    public void UpdateComputer(){
        MongoCursor<Document> cursor = dbAccess.getComputerbyId();
        Computer computerToUpdate = new Computer();
        System.out.println("Geben Sie den Index zum Computer ein, den Sie updaten wollen");
        scanner = new Scanner(System.in);
        int computerId = scanner.nextInt();

        computerReopsitory.getById(computerId, cursor);
        computerToUpdate = computerReopsitory.getComputer(0);

        if (computerToUpdate != null){

            System.out.println("Computer gefunden. Geben Sie die neuen Daten ein:");
            System.out.println("id " + computerToUpdate.getId());

            System.out.print("Geben Sie den neuen Hersteller ein (leer lassen, um ihn nicht zu ändern): ");
            String hersteller = scanner.nextLine();
            if (!hersteller.isEmpty()) {
                computerToUpdate.setHersteller(hersteller);
            }

            System.out.print("Geben Sie das neue Modell ein (leer lassen, um es nicht zu ändern): ");
            String modell = scanner.nextLine();
            if (!modell.isEmpty()) {
                computerToUpdate.setModell(modell);
            }

            System.out.print("Geben Sie den neuen Arbeitsspeicher ein (leer lassen, um ihn nicht zu ändern): ");
            String arbeitsspeicherStr = scanner.nextLine();
            if (!arbeitsspeicherStr.isEmpty()) {
                double arbeitsspeicher = Double.parseDouble(arbeitsspeicherStr);
                computerToUpdate.setMassenspeicher(arbeitsspeicher);
            }

            System.out.print("Geben Sie die neue CPU ein (leer lassen, um sie nicht zu ändern): ");
            String cpu = scanner.nextLine();
            if (!cpu.isEmpty()) {
                computerToUpdate.setCpu(cpu);
            }

            System.out.print("Geben Sie den neuen Massenspeicher ein (leer lassen, um ihn nicht zu ändern): ");
            String massenspeicherStr = scanner.nextLine();

            if (!massenspeicherStr.isEmpty()) {
                double massenspeicher = Double.parseDouble(massenspeicherStr);
                computerToUpdate.setMassenspeicher(massenspeicher);
            }

            System.out.print("Geben Sie den neuen Typ ein (leer lassen, um ihn nicht zu ändern): ");
            String typ = scanner.nextLine();
            if (!typ.isEmpty()) {
                computerToUpdate.setTyp(typ);
            }

            System.out.print("Geben Sie den neuen Einzelpreis ein (leer lassen, um ihn nicht zu ändern): ");
            String einzelpreisStr = scanner.nextLine();
            if (!einzelpreisStr.isEmpty()) {
                double einzelpreis = Double.parseDouble(einzelpreisStr);
                computerToUpdate.setEinzelPreis(einzelpreis);
            }

            System.out.println("Schnittstellen aktualisieren (leer lassen, um sie nicht zu ändern):");
            System.out.print("Geben Sie die Anzahl der neuen USB-Ports ein: ");
            String anzahlUsbPortsStr = scanner.nextLine();
            if (!anzahlUsbPortsStr.isEmpty()) {
                int anzahlUsbPorts = Integer.parseInt(anzahlUsbPortsStr);
                computerToUpdate.getSchnittstelle().setAnzUsbSchnittstellen(anzahlUsbPorts);
            }

            System.out.print("Geben Sie die Anzahl der neuen USB-C-Ports ein: ");
            String anzahlUsbcPortsStr = scanner.nextLine();
            if (!anzahlUsbcPortsStr.isEmpty()) {
                int anzahlUsbcPorts = Integer.parseInt(anzahlUsbcPortsStr);
                computerToUpdate.getSchnittstelle().setAnzUsbCSchnittstellen(anzahlUsbcPorts);
            }

            System.out.print("Geben Sie die Anzahl der neuen HDMI-Ports ein: ");
            String anzahlHdmiPortsStr = scanner.nextLine();
            if (!anzahlHdmiPortsStr.isEmpty()) {
                int anzahlHdmiPorts = Integer.parseInt(anzahlHdmiPortsStr);
                computerToUpdate.getSchnittstelle().setAnzHdmiSchnittstellen(anzahlHdmiPorts);
            }

            System.out.print("Geben Sie die Anzahl der neuen HDMI-Ports ein: ");
            String anzahlLanPortsStr = scanner.nextLine();
            if (!anzahlLanPortsStr.isEmpty()) {
                int anzahlLanPorts = Integer.parseInt(anzahlLanPortsStr);
                computerToUpdate.getSchnittstelle().setAnzHdmiSchnittstellen(anzahlLanPorts);
            }

            computerReopsitory.update(computerToUpdate);

            System.out.println("Computer aktualisiert.");


        }else {
            System.out.println("Keinen Computer mit dieser Id gefunden!");
        }


    }

    //Kunden-Verwaltung-----------------------------------------------------------------------------------------//

    public void getAllKunden(){
        ArrayList<Kunde> kunden = new ArrayList<>();
        ArrayList<Document> docs = dbAccess.getAllKunden();
        for (Document doc: docs
        ) {
            kundeRepository.getAll(doc, kunden);
        }
        kundeRepository.PrintKunden();
    }

    public Kunde getKundeById(){
        Kunde kunde;
        System.out.println("Index des Kunden eingeben: ");
        scanner =new Scanner(System.in);
        int index = scanner.nextInt();

        MongoCursor<Document> cursor = dbAccess.getKundeById();
        kunde = kundeRepository.getById(index, cursor);
        kundeRepository.PrintKunden();

        return kunde;
    }

    public void addKunde(){
        dbAccess.SaveComputer(kundeRepository.save(kundeRepository.getKundeDatafromUser()));
    }

    public void deleteKunde(){
        ArrayList<Kunde> kunden = new ArrayList<>();
        MongoCursor<Document> cursor = dbAccess.getKundeById();
        ArrayList<Document> docs = dbAccess.getAllKunden();
        for (Document doc: docs
        ) {
            kundeRepository.getAll(doc, kunden);
        }
        kundeRepository.PrintKunden();
        ObjectId id = kundeRepository.delete(cursor);
        dbAccess.DeleteKunde(id);
    }

    public void UpdateKunde(){
        MongoCursor<Document> cursor = dbAccess.getKundeById();
        Kunde KundeToUpdate = new Kunde();
        System.out.println("Geben Sie den Index zum Kunden ein, den Sie updaten wollen");
        scanner = new Scanner(System.in);
        int computerId = scanner.nextInt();

        kundeRepository.getById(computerId, cursor);
        KundeToUpdate = kundeRepository.getKunde(0);

        if (KundeToUpdate != null){

            System.out.println("Kunde gefunden. Geben Sie die neuen Daten ein:");
            System.out.println("id " + KundeToUpdate.getId());

            System.out.print("Geben Sie den neuen Vorname ein (leer lassen, um ihn nicht zu ändern): ");
            String vorname = scanner.nextLine();
            if (!vorname.isEmpty()) {
                KundeToUpdate.setVorname(vorname);
            }

            System.out.print("Geben Sie den neuen Nachnamen ein (leer lassen, um es nicht zu ändern): ");
            String nachname = scanner.nextLine();
            if (!nachname.isEmpty()) {
                KundeToUpdate.setNachname(nachname);
            }

            System.out.print("Geben Sie das neue Geschlecht ein (leer lassen, um es nicht zu ändern): ");
            String geschlecht = scanner.nextLine();
            if (!geschlecht.isEmpty()) {
                KundeToUpdate.setGeschlecht(geschlecht);
            }

            System.out.print("Geben Sie die neue Telefonnummer ein (leer lassen, um sie nicht zu ändern): ");
            String telefon = scanner.nextLine();
            if (!telefon.isEmpty()) {
                KundeToUpdate.setTelefon(telefon);
            }

            System.out.print("Geben Sie die neue E-Mail ein (leer lassen, um sie nicht zu ändern): ");
            String email = scanner.nextLine();
            if (!email.isEmpty()) {
                KundeToUpdate.setEmail(email);
            }

            System.out.print("Geben Sie die neue Sprache ein (leer lassen, um sie nicht zu ändern): ");
            String sprache = scanner.nextLine();
            if (!sprache.isEmpty()) {
                KundeToUpdate.setSprache(sprache);
            }

            System.out.print("Geben Sie das neue Geburtsdatum ein ((Format: yyyy-MM-dd)leer lassen, um sie nicht zu ändern): ");
            String geburtsdatumStr = scanner.nextLine();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date geburtsdatum = dateFormat.parse(geburtsdatumStr);
                KundeToUpdate.setGeburtsdatum(geburtsdatum);
            } catch (ParseException e) {
                System.out.println("Ungültiges Datumsformat!");
                e.printStackTrace();
            }

            //Adresse!!
            System.out.print("Geben Sie die neue Strasse ein (leer lassen, um sie nicht zu ändern): ");
            String strasse = scanner.nextLine();
            if (!strasse.isEmpty()) {
                KundeToUpdate.getAdresse().setStrasse(strasse);
            }

            System.out.print("Geben Sie die neue PLZ ein (leer lassen, um sie nicht zu ändern): ");
            String plz = scanner.nextLine();
            if (!strasse.isEmpty()) {
                KundeToUpdate.getAdresse().setStrasse(plz);
            }

            System.out.print("Geben Sie die neue Ort ein (leer lassen, um sie nicht zu ändern): ");
            String ort = scanner.nextLine();
            if (!ort.isEmpty()) {
                KundeToUpdate.getAdresse().setStrasse(ort);
            }

            kundeRepository.update(KundeToUpdate);

            System.out.println("Kunde aktualisiert.");

        }else {
            System.out.println("Keinen Kunden mit dieser Id gefunden!");
        }


    }
    //Bestellungs-Verwaltung-----------------------------------------------------------------------------------------//

    public void getAllBestellungen(){
        ArrayList<Bestellung> bestellungen = new ArrayList<>();
        ArrayList<Document> docs = dbAccess.getAllBestellungen();
        for (Document doc: docs
        ) {
            bestellungReopsitory.getAll(doc, bestellungen);
        }
        bestellungReopsitory.PrintBestellung();
    }

    public Bestellung getBestellungById(){
        Bestellung bestellung;
        System.out.println("Index des Kunden eingeben: ");
        scanner = new Scanner(System.in);
        int index = scanner.nextInt();

        MongoCursor<Document> cursor = dbAccess.getBestellungById();
        bestellung = bestellungReopsitory.getById(index, cursor);
        bestellungReopsitory.PrintBestellung();

        return bestellung;
    }

    public void addBestellung(){
        dbAccess.SaveBestellung(bestellungReopsitory.save(bestellungReopsitory.getBestellungsDatafromUser()));
    }

    public void deleteBestellung(){
        ArrayList<Bestellung> bestellungen = new ArrayList<>();
        MongoCursor<Document> cursor = dbAccess.getBestellungById();
        ArrayList<Document> docs = dbAccess.getAllBestellungen();
        for (Document doc: docs
        ) {
            bestellungReopsitory.getAll(doc, bestellungen);
        }
        bestellungReopsitory.PrintBestellung();
        ObjectId id = bestellungReopsitory.delete(cursor);
        dbAccess.DeleteBestellung(id);
    }

    public void UpdateBestellung(){
        MongoCursor<Document> cursor = dbAccess.getBestellungById();
        Bestellung BestellungToUpdate = new Bestellung();
        System.out.println("Geben Sie den Index zur Bestellung ein, die Sie updaten wollen");
        scanner = new Scanner(System.in);
        int BestellungsId = scanner.nextInt();

        bestellungReopsitory.getById(BestellungsId, cursor);
        BestellungToUpdate = bestellungReopsitory.getBestellung(0);

        if (BestellungToUpdate != null){

            System.out.println("Kunde gefunden. Geben Sie die neuen Daten ein:");
            System.out.println("id " + BestellungToUpdate.getBestellungsId());

            System.out.print("Geben Sie eine neue Bestellnummer ein (leer lassen, um ihn nicht zu ändern): ");
            String bestellnummer = scanner.nextLine().toString();
            if (!bestellnummer.isEmpty()) {
                BestellungToUpdate.setBestellnummer(bestellnummer);
            }

            System.out.print("Geben Sie das neue Geburtsdatum ein ((Format: yyyy-MM-dd)leer lassen, um sie nicht zu ändern): ");
            String bestelldatumStr = scanner.nextLine();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date bestelldatum = dateFormat.parse(bestelldatumStr);
                BestellungToUpdate.setBestelldatum(bestelldatum);
            } catch (ParseException e) {
                System.out.println("Ungültiges Datumsformat!");
                e.printStackTrace();
            }

            System.out.println("Möchten sie den Kunden wechseln? (y/n)");
            String kundeWechseln = scanner.next();

            switch (kundeWechseln){
                case "y": getAllKunden();
                    Kunde kunde = getKundeById();

                    ObjectId kundenId = kunde.getId();
                    String nachname = kunde.getNachname();
                    String vorname = kunde.getVorname();

                    Kunde kunde1 = new Kunde(kundenId, nachname, vorname);
                    BestellungToUpdate.setKunde(kunde1);

                    break;
                case "n":
                    System.out.println("Kunde wird nicht geändert.");
                    break;
            }

            ArrayList<Bestellposition> bestellpositionen = BestellungToUpdate.getBestellpositionen();


            System.out.println("Möchten Sie die bestellten Computer bearbeiten? (y/n)");
            String computerBearbeiten = scanner.next();

             switch (computerBearbeiten) {
                 case "y":

                     for (Bestellposition bestellposition: bestellpositionen
                          ) {
                         getAllComputers();
                         Computer computer = getComputerById();

                         System.out.println("Stueckzahl aendern (Leer lassen, um nicht zu aendern): ");
                         int stk = scanner.nextInt();
                         try {
                             bestellposition.setStueckZahl(stk);
                         } catch (Exception e){
                             System.out.println("Ganze Zahl eingeben!");
                         }
                     }
                     break;
                 case "n":
                     System.out.println("Computer werden nicht bearbeitet.");
             }

            bestellungReopsitory.update(BestellungToUpdate);

            System.out.println("Kunde aktualisiert.");

        }else {
            System.out.println("Keinen Kunden mit dieser Id gefunden!");
        }


    }

    public void bestellungComputerBearbeiten(){

    }

    public DbAccess getDbAccess() {
        return dbAccess;
    }

    public void setDbAccess(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }
}
