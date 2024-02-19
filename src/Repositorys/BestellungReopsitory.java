package Repositorys;

import Controller.Controller;
import DbAccess.DbAccess;
import Interfaces.IBestellung;
import Model.*;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class BestellungReopsitory implements IBestellung {

    private Bestellung bestellung;
    private Scanner scanner;
    private Controller controller;
    private ArrayList<Bestellung> bestellungen;
    private DbAccess dbAccess;


    public BestellungReopsitory(Controller controller) {
        this.controller = controller;
    }

    public Document save(Bestellung bestellung){

        Document bsonBestellung = new Document();
        bsonBestellung.append("bestellnummer", bestellung.getBestellnummer())
                .append("bestelldatum", bestellung.getBestelldatum())
                .append("total", bestellung.getTotal())
                .append("kundenId", bestellung.getKunde().getId())
                .append("vorname", bestellung.getKunde().getVorname())
                .append("vorname", bestellung.getKunde().getNachname());

        ArrayList<Document> bestellPositionenDocs = new ArrayList<>();
        for (Bestellposition position : bestellung.getBestellpositionen()
             ) {
            Document positionDoc = new Document("computer", position.getComputer())
                    .append("hersteller", position.getComputer().getHersteller())
                    .append("modell", position.getComputer().getModell())
                    .append("preis", position.getComputer().getEinzelPreis())
                    .append("stueckzahl", position.getStueckZahl());
            bestellPositionenDocs.add(positionDoc);
        }

        bsonBestellung.append("bestellposition", bestellPositionenDocs);

        return bsonBestellung;
    }

    public ArrayList<Bestellung> getAll(Document doc, ArrayList<Bestellung> bestellungen){
        this.bestellungen = bestellungen;
        bestellungen.add(DocToBestellung(doc));
        return bestellungen;
    }

    public Bestellung DocToBestellung(Document doc){

        ObjectId id = doc.getObjectId("_id");
        int bestellnummer = doc.getInteger("Bestellnummer");
        String bestelldatumStr = doc.getString("Bestelldatum");
        Date bestelldatum = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            bestelldatum = dateFormat.parse(bestelldatumStr);
        } catch (ParseException e){
            System.out.println("Fehler beim Parsen des Bestelldatums: " + e.getMessage());
            e.printStackTrace();
        }
        double total = doc.getDouble("Total");


        ObjectId kundeId = doc.getObjectId("kunde");
        String vorname = doc.getString("vorname");
        String nachname = doc.getString("nachname");

        Kunde kunde = new Kunde(kundeId, nachname, vorname);

        ArrayList<Bestellposition> bestellpositionen = new ArrayList<>();
        ArrayList<Document> positionDocs = doc.get("bestellpositionen", ArrayList.class);

        if (positionDocs != null){
            for (Document positonDoc: positionDocs
                 ) {
                String hersteller = positonDoc.getString("Hersteller");
                String modell = positonDoc.getString("Modell");
                double preis = positonDoc.getDouble("Preis");
                int stueckZahl = positonDoc.getInteger("Stueckzahl");

                Computer computer = new Computer(hersteller, modell);
                Bestellposition bestellposition = new Bestellposition(computer, preis, stueckZahl);
                bestellpositionen.add(bestellposition);


            }
        }

        return new Bestellung(id, bestelldatum, total, bestellpositionen, kunde);

    }

    public Bestellung getBestellungsDatafromUser(){
        Computer computer = new Computer();
        ArrayList<Bestellposition> bestellpositionen = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Neue Bestellung erfassen: ");

        ObjectId objectId = new ObjectId();
        System.out.println("ObjectId: " + objectId);

        System.out.println("Bestellnummer: ");
        String bestellnummer = scanner.next();

        System.out.println("Bestelldatum (Fromat: yyyy-MM-dd): ");
        String bestelldatumStr = scanner.next();
        Date bestelldatum = null;

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            bestelldatum = dateFormat.parse(bestelldatumStr);
        } catch (ParseException e) {
            System.out.println("Fehler beim Parsen: " + e.getMessage());
        }

        controller.getAllKunden();
        System.out.println("Zu welchem Kunde gehört die Bestellung (Index eingeben): ");
        Kunde kunde = controller.getKundeById();

        ObjectId kundenId = kunde.getId();
        String nachname = kunde.getNachname();
        String vorname = kunde.getVorname();

        Kunde kunde1 = new Kunde(kundenId, nachname, vorname);

        controller.getAllComputers();
        System.out.println("Wie viel Computer sind Bestellt worden?");
        int anzComputer = scanner.nextInt();
        double preis = 0.00;

        for (int i = 0; i <= anzComputer; i++){
            System.out.println("Geben sie den Computer Index ein: ");
            int computerIndex = scanner.nextInt();
            computer = controller.getComputerById();
            String hersteller  = computer.getHersteller();
            String modell  = computer.getModell();
            preis = computer.getEinzelPreis();
            System.out.println("Wie viele Stücke wurden bestellt? ");
            int stueck = scanner.nextInt();

            Computer computer1 = new Computer(hersteller, modell);
            Bestellposition bestellposition = new Bestellposition(computer1, preis, stueck);
            bestellpositionen.add(bestellposition);
        }

        Bestellung bestellung = new Bestellung(objectId, bestellnummer, bestelldatum, kunde1, preis, bestellpositionen);

        return bestellung;
    }

    public void update(Bestellung bestellung) {
        dbAccess = new DbAccess(controller);
        this.dbAccess = controller.getDbAccess();

        dbAccess.updateComputer(save(bestellung), bestellung.getBestellungsId());
    }

    public Bestellung getById(int index, MongoCursor<Document> cursor) {
        bestellungen = new ArrayList<>();
        try {
            int currentIndex = 0;
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (currentIndex == index) {
                    bestellungen.add(DocToBestellung(doc));
                    return DocToBestellung(doc);
                }
                currentIndex++;
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    public ObjectId delete(MongoCursor<Document> cursor) {
        bestellungen = new ArrayList<>();
        int index = getIndex();
        try {
            int currentIndex = 0;
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (currentIndex == index) {
                    bestellungen.add(DocToBestellung(doc));
                }
                currentIndex++;
            }
        } finally {
            cursor.close();
        }
        bestellung = bestellungen.get(0);
        ObjectId id = bestellung.getBestellungsId();
        return id;
    }

    public Integer getIndex(){
        System.out.println("Index des Computer eingeben: ");
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void PrintBestellung(){
        for (Bestellung bestellung : bestellungen
             ) {
            System.out.println(bestellung);
        }
    }

    public Bestellung getBestellung(int index){
        return bestellung = bestellungen.get(index);
    }

}
