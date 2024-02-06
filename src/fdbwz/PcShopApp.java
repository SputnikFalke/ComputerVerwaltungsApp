package fdbwz;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import fdbwz.Menu.Menu;

import java.util.Scanner;

public class PcShopApp {

    private String collectionname;
    MongoClient mongoClient = new MongoClient("localhost", 27017);

    public void createCollection(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Geben sie der Collection einen Namen");
        collectionname = scanner.next();

        MongoDatabase database = mongoClient.getDatabase("PcShopDb");
        database.createCollection(collectionname);

        System.out.println("Collection hinzugefügt!");
    }


    public void addDocument(){

    }

    public void run(){
        Menu menu = new Menu();
        menu.DisplayHauptmenu();
    }
}
