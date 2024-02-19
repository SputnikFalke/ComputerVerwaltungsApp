package DbAccess;

import Controller.Controller;

import Model.Bestellung;
import Model.Computer;
import Model.Kunde;
import Repositorys.ComputerReopsitory;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.ArrayList;

public class DbAccess {

    private Controller controller;
    private String collectionname = "person";
    private MongoCollection<Document> kundeCollection;
    private MongoCollection<Document> computerCollection;
    private MongoCollection<Document> bestellungsCollection;
    private final MongoClient mongoClient = new MongoClient("localhost", 27017);
    MongoDatabase db;
    private final String dbName = "PcShopDb";
    ArrayList<Kunde> kunden;
    ArrayList<Computer> newComputers;
    ArrayList<Computer> computers;

    public DbAccess(Controller controller){
        this.controller =  controller;
    }

    public void connectToDb() {
        MongoCredential credential;
        //mongodb://localhost:27017
        credential = MongoCredential.createCredential("localhost", dbName, "password".toCharArray());
        db = mongoClient.getDatabase(dbName);
        getAllCollections();
    }

    public void getAllCollections(){
        kundeCollection = db.getCollection(collectionname);  
        collectionname = "computer";
        computerCollection = db.getCollection(collectionname);
        collectionname = "bestellungen";
        bestellungsCollection = db.getCollection(collectionname);
    }

    //Bestellungs-Verwaltung-----------------------------------------------------------------------------------------//

    public void SaveComputer(Document document){
        connectToDb();
        computerCollection.insertOne(document);
        System.out.println("Computer erfolgreich gespeichert!");
    }

    public MongoCursor<Document> getComputerbyId(){
        connectToDb();
        MongoCursor<Document> cursor = computerCollection.find().iterator();
        return cursor;
    }

    public void DeleteComputer(ObjectId id){
        connectToDb();
        computerCollection.deleteOne(Filters.eq("_id", id));
    }


    public void updateComputer(Document doc, ObjectId id){
        connectToDb();
        computerCollection.updateOne(Filters.eq("_id", id), new Document("$set", doc));
    }

    public ArrayList<Document> getAllComputers(){
        connectToDb();
        FindIterable<Document> comps = computerCollection.find();
        ArrayList<Document> docs = new ArrayList<>();
        for (Document doc: comps
             ) {
            docs.add(doc);
        }
       return docs;
    }

    //Kunde-Verwaltung-----------------------------------------------------------------------------------------//

    public void SaveKunde(Document document){
        connectToDb();
        kundeCollection.insertOne(document);
        System.out.println("Computer erfolgreich gespeichert!");
    }

    public MongoCursor<Document> getKundeById(){
        connectToDb();
        MongoCursor<Document> cursor = kundeCollection.find().iterator();
        return cursor;
    }

    public void DeleteKunde(ObjectId id){
        connectToDb();
        kundeCollection.deleteOne(Filters.eq("_id", id));
    }


    public void updateKunde(Document doc, ObjectId id){
        connectToDb();
        kundeCollection.updateOne(Filters.eq("_id", id), new Document("$set", doc));
    }

    public ArrayList<Document> getAllKunden(){
        connectToDb();
        FindIterable<Document> kunden = kundeCollection.find();
        ArrayList<Document> docs = new ArrayList<>();
        for (Document doc: kunden
        ) {
            docs.add(doc);
        }
        return docs;
    }
    //Bestellungs-Verwaltung-----------------------------------------------------------------------------------------//

    public void SaveBestellung(Document bestellungDoc){
        connectToDb();
        bestellungsCollection.insertOne(bestellungDoc);
        System.out.println("Bestellung erfolgreich erfasst");
    }

    public MongoCursor<Document> getBestellungById(){
        connectToDb();
        MongoCursor<Document> cursor = bestellungsCollection.find().iterator();
        return cursor;
    }

    public void DeleteBestellung(ObjectId id){
        connectToDb();
        bestellungsCollection.deleteOne(Filters.eq("_id", id));
    }

    public void updateBestellung(Document doc, ObjectId id){
        connectToDb();
        bestellungsCollection.updateOne(Filters.eq("_id", id), new Document("$set", doc));
    }

    public ArrayList<Document> getAllBestellungen(){
        connectToDb();
        FindIterable<Document> bestellungen = bestellungsCollection.find();
        ArrayList<Document> docs = new ArrayList<>();
        for (Document doc : bestellungen
        ) {
            docs.add(doc);
        }
        return docs;
    }

}
