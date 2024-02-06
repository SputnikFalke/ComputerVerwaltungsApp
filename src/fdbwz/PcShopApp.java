package fdbwz;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fdbwz.Menu.Menu;
import fdbwz.Model.Computer;
import org.bson.BSON;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

public class PcShopApp {

    Scanner scanner = new Scanner(System.in);
    private String collectionname;
    MongoClient mongoClient = new MongoClient("localhost", 27017);

    public void createCollection(){

        System.out.println("Geben sie der Collection einen Namen");
        collectionname = scanner.next();
        scanner.close();

        MongoDatabase db = mongoClient.getDatabase("PcShopDb");
        db.createCollection(collectionname);
        db.getCollection(collectionname);

        System.out.println("Collection hinzugefügt!");
    }


    public void getCollection(){
        ArrayList<Computer> computers = new ArrayList<>();
        collectionname = "Computer";
        MongoDatabase db = mongoClient.getDatabase("PcShopDb");
        MongoCollection<Document> collection = db.getCollection(collectionname);
        System.out.println(collection);

        FindIterable<Document> collections = collection.find();

        for (Document doc : collections
             ) {
            Computer computer = new Computer();

            computers.add(computer);
        }

        for (Computer compi: computers){
            System.out.println(compi);
        }


    }

    public void addDocument(){

    }

    public void run(){
        Menu menu = new Menu();
        menu.DisplayHauptmenu();
    }
}
