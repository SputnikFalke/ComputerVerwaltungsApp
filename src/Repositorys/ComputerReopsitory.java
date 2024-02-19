package Repositorys;

import Controller.Controller;

import DbAccess.DbAccess;
import Interfaces.IComputer;
import Model.Computer;
import Model.Schnittstellen;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;


import java.util.ArrayList;
import java.util.Scanner;

public class ComputerReopsitory implements IComputer {


    private Scanner scanner;
    private Controller controller;
    private Computer computer;
    private DbAccess dbAccess;

    ArrayList<Computer> computers;

    public ComputerReopsitory(Controller controller){
        this.controller = controller;
    }

    @Override
    public ArrayList<Computer> getAll(Document doc, ArrayList<Computer> computers) {
        this.computers = computers;
        computers.add(DocToComputer(doc));
        return computers;
    }

    @Override
    public Computer DocToComputer(Document doc){

        ObjectId id = doc.getObjectId("_id");
        String hersteller = doc.getString("Hersteller");
        String modell = doc.getString("Modell");
        double arbeitsspeicher = doc.getDouble("Arbeitsspeicher");
        String cpu = doc.getString("Cpu");
        double massenspeicher = doc.getDouble("Massenspeicher");
        String typ = doc.getString("Typ");
        double einzelPreis = doc.getDouble("Einzelpreis");

        Document schnittstellenDoc = (Document) doc.get("Schnittstellen");

        int anzUsbSchnittstellen = schnittstellenDoc.getInteger("AnzUsbSchnittstellen");
        int anzUsbCSchnittstellen = schnittstellenDoc.getInteger("AnzUsbCSchnittstellen");
        int anzHdmiSchnittstellen = schnittstellenDoc.getInteger("AnzHdmiSchnittstellen");
        int anzLanSchnittstellen = schnittstellenDoc.getInteger("AnzLANSchnittstellen");

        Schnittstellen schnittstellen = new Schnittstellen(anzUsbSchnittstellen, anzUsbCSchnittstellen, anzHdmiSchnittstellen, anzLanSchnittstellen);

        // Neues Computer-Objekt erstellen
        Computer computer = new Computer(id, hersteller, modell, arbeitsspeicher, cpu, massenspeicher, typ, einzelPreis, schnittstellen);

        return computer;
    }

    @Override
    public Computer getById(int index, MongoCursor<Document> cursor) {
        computers = new ArrayList<>();
        try {
            int currentIndex = 0;
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (currentIndex == index) {
                    computers.add(DocToComputer(doc));
                    return DocToComputer(doc);
                }
                currentIndex++;
            }
        } finally {
            cursor.close();
        }
        return null;
    }
    @Override
    public void update(Computer computer) {
        dbAccess = new DbAccess(controller);
        this. dbAccess = controller.getDbAccess();

        dbAccess.updateComputer(save(computer), computer.getId());
    }

    @Override
    public ObjectId delete(MongoCursor<Document> cursor) {
        computers = new ArrayList<>();
        int index = getIndex();
        try {
            int currentIndex = 0;
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (currentIndex == index) {
                    computers.add(DocToComputer(doc));
                }
                currentIndex++;
            }
        } finally {
            cursor.close();
        }
        computer = computers.get(0);
        ObjectId id = computer.getId();
        return id;
    }

    public Document save(Computer computer) {

        Document bsonComputer = new Document();
        bsonComputer.append("Hersteller", computer.getHersteller())
                .append("Modell", computer.getModell())
                .append("Arbeitsspeicher", computer.getArbeitsspeicher())
                .append("Cpu", computer.getCpu())
                .append("Massenspeicher", computer.getMassenspeicher())
                .append("Typ", computer.getTyp())
                .append("Einzelpreis", computer.getEinzelPreis())
                .append("Schnittstellen", new Document("AnzUsbSchnittstellen", computer.getSchnittstelle().getAnzUsbSchnittstellen())
                        .append("AnzUsbCSchnittstellen", computer.getSchnittstelle().getAnzUsbCSchnittstellen())
                        .append("AnzHdmiSchnittstellen", computer.getSchnittstelle().getAnzHdmiSchnittstellen())
                        .append("AnzLANSchnittstellen", computer.getSchnittstelle().getAnzLanSchnittstellen()));
        return bsonComputer;
    }

    public Integer getIndex(){
        System.out.println("Index des Computer eingeben: ");
        scanner =new Scanner(System.in);
        return scanner.nextInt();
    }

    public Computer getComputerDatafromUser(){
        scanner = new Scanner(System.in);

        System.out.println("Neuen Computer erfassen: ");


        ObjectId objectId = new ObjectId();
        System.out.println("ObjectId: " + objectId);

        System.out.println("Hersteller: ");
        String hersteller = scanner.next();

        System.out.println("Modell: ");
        String Modell = scanner.next();

        System.out.println("Arbeitsspeicher (in GB): ");
        double arbeitsspeicher = scanner.nextDouble();

        System.out.println("CPU: ");
        String cpu = scanner.next();

        System.out.println("Massenspeicher (in GB): ");
        double massenspeicher = scanner.nextDouble();

        System.out.println("Typ: ");
        String typ = scanner.next();

        System.out.println("Einzelpreis (in CHFS): ");
        double einzelpreis = scanner.nextDouble();

        System.out.println("Geben sie die Anzahl USB-Schnittstellen an: ");
        int anzUsbSchnittstellen = scanner.nextInt();

        System.out.println("Anzahl USB-C-Schnittstellen: ");
        int anzUsbCSchnittstellen = scanner.nextInt();

        System.out.println("Anzahl HDMI-Schnittstellen: ");
        int anzHdmiSchnittstellen = scanner.nextInt();

        System.out.println("Anzahl LAN-Schnittstellen: ");
        int anzLanSchnittstellen = scanner.nextInt();

        Schnittstellen schnittstellen = new Schnittstellen(anzUsbSchnittstellen, anzUsbCSchnittstellen, anzHdmiSchnittstellen, anzLanSchnittstellen);

        Computer computer1 = new Computer(objectId, hersteller, Modell, arbeitsspeicher, cpu, massenspeicher, typ, einzelpreis, schnittstellen);
        return computer1;
    }

    public void PrintComputers(){
        for (Computer computer: computers
             ) {
            System.out.println(computer);
        }
    }

    public Computer getComputer(int index){
        return computer = computers.get(index);
    }

}
