package Repositorys;

import Controller.Controller;
import DbAccess.DbAccess;
import Interfaces.IKunde;
import Model.Adresse;
import Model.Computer;
import Model.Kunde;
import Model.Schnittstellen;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class KundeRepository implements IKunde {

    private Kunde kunde;
    private ArrayList<Kunde> kunden;
    private Controller controller;
    private DbAccess dbAccess;
    private Scanner scanner;
    public  KundeRepository(Controller controller){
        this.controller = controller;
    }

    public Document save(Kunde kunde) {

        Document bsonPerson = new Document();
        bsonPerson.append("Name", kunde.getNachname())
                .append("Vorname", kunde.getVorname())
                .append("Geschlecht", kunde.getGeschlecht())
                .append("Telefon", kunde.getTelefon())
                .append("E-Mail", kunde.getEmail())
                .append("Sprache", kunde.getSprache())
                .append("Geburtsdatum", kunde.getGeburtsdatum())
                .append("Adresse", new Document("Strasse", kunde.getAdresse().getStrasse())
                        .append("PLZ", kunde.getAdresse().getPlz())
                        .append("Ort", kunde.getAdresse().getOrt()));
        return bsonPerson;
    }

    @Override
    public Kunde DocToKunde(Document doc) {
        ObjectId id = doc.getObjectId("_id");
        String geschlecht = doc.getString("Geschlecht");
        String vorname = doc.getString("Vorname");
        String nachname = doc.getString("Nachname");
        String telefon = doc.getString("Telefon");
        String mail = doc.getString("E-Mail");
        String sprache = doc.getString("Sprache");
        String geburtstatumStr = doc.getString("Geburtsdatum");
        Date geburtsdatum = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            geburtsdatum = dateFormat.parse(geburtstatumStr);
        } catch (ParseException e) {
            System.out.println("Fehler beim Parsen des Datums: " + e.getMessage());
            e.printStackTrace();
        }


        Document adresseDoc = (Document) doc.get("Adresse");

        String strasse = adresseDoc.getString("Strasse");
        String plz = adresseDoc.getString("Strasse");
        String ort = adresseDoc.getString("Strasse");

        Adresse adresse = new Adresse(strasse, plz, ort);
        Kunde kunde = new Kunde(id, geschlecht, vorname, nachname, telefon, mail, sprache, geburtsdatum, adresse);

        return kunde;

    }

    @Override
    public ArrayList<Kunde> getAll(Document doc, ArrayList<Kunde> kunden) {
        this.kunden = kunden;
        kunden.add(DocToKunde(doc));
        return null;
    }

    @Override
    public Kunde getById(int index, MongoCursor<Document> cursor) {
        kunden = new ArrayList<>();
        try {
            int currentIndex = 0;
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (currentIndex == index) {
                    kunden.add(DocToKunde(doc));
                    return DocToKunde(doc);
                }
                currentIndex++;
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    @Override
    public void update(Kunde kunde) {
        dbAccess = new DbAccess(controller);
        this. dbAccess = controller.getDbAccess();
        dbAccess.updateKunde(save(kunde), kunde.getId());
    }

    @Override
    public ObjectId delete(MongoCursor<Document> cursor) {
        kunden = new ArrayList<>();
        int index = getIndex();
        try {
            int currentIndex = 0;
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (currentIndex == index) {
                    kunden.add(DocToKunde(doc));
                }
                currentIndex++;
            }
        } finally {
            cursor.close();
        }
        kunde = kunden.get(0);
        ObjectId id = kunde.getId();
        return id;
    }

    public Integer getIndex(){
        System.out.println("Index des Computer eingeben: ");
        scanner =new Scanner(System.in);
        return scanner.nextInt();
    }

    public Kunde getKundeDatafromUser(){
        scanner = new Scanner(System.in);
        Kunde kunde;

        System.out.println("Neuen Kunden erfassen: ");

        ObjectId objectId = new ObjectId();
        System.out.println("ObjectId: " + objectId);

        System.out.println("Vorname: ");
        String vorname = scanner.next().toString();

        System.out.println("Nachname: ");
        String nachname = scanner.next().toString();

        System.out.println("Geschlecht: ");
        String geschlecht = scanner.next().toString();

        System.out.println("Telefon: ");
        String telefon = scanner.next().toString();

        System.out.println("E-Mail: ");
        String email = scanner.next().toString();

        System.out.println("Sprache: ");
        String sprache = scanner.next().toString();

        System.out.println("Geburtsdatum (Format: yyyy-MM-dd)");
        String geburtsdatumStr = scanner.next();
        Date geburtsdatum = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            geburtsdatum = dateFormat.parse(geburtsdatumStr);
        } catch (ParseException e) {
            System.out.println("Fehler beim Parsen des Datums: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Geben sie Ihre Adresse an: ");
        System.out.println("Strasse: ");
        String strasse = scanner.next().toString();

        System.out.println("PLZ: ");
        String plz = scanner.next().toString();

        System.out.println("Ort: ");
        String ort = scanner.next().toString();

        Adresse adresse = new Adresse(strasse, plz, ort);

        kunde = new Kunde(objectId, geschlecht, nachname, vorname, telefon, email, sprache, geburtsdatum, adresse);
        return kunde;
    }

    public void PrintKunden(){
        for (Kunde kunde: kunden
        ) {
            System.out.println(kunde);
        }
    }

    public Kunde getKunde(int index){
        return kunde = kunden.get(index);
    }


}
