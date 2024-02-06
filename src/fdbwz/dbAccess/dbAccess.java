package fdbwz.dbAccess;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dbAccess {

    String collectionname;
    MongoClient mongoClient;
    public void connectToDatabase(){
        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser", "myDb", "password".toCharArray());
    }

    public void createCollection(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben sie der Collection einen Namen");
        collectionname = scanner.next();

        mongoClient = new MongoClient("localhost", 27017);
        MongoDatabase database = mongoClient.getDatabase("myDb");
        database.createCollection(collectionname);

        System.out.println("Collection hinzugefügt!");
    }

}
