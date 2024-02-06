package fdbwz.Model;

import fdbwz.interfaces.ComputerInterface;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Computer implements ComputerInterface {
    ArrayList<Computer> computers;

    private ObjectId id;
    private String hersteller;
    private String modell;
    private double arbeitsspeicher; //in GB
    private String cpu;
    private double massenspeicher; //in GB
    private enum typ {
        Desktop,
        Notebook
    }

    private String typ;


    private double einzelPreis;

    private enum Schnittstelle{
        USB,
        HDMI,
        LAN,
        USBc,
        MicroSd

    }

    private Schnittstelle[] schnittstellen;

    Schnittstelle[] schnittstellenArray = {Schnittstelle.USB, Schnittstelle.HDMI, Schnittstelle.LAN};
    //Notebook meinNotebook = new Notebook(schnittstellenArray);

    // Zugriff auf die Schnittstellen des Notebooks
    /*Schnittstelle[] notebookSchnittstellen = meinNotebook.getSchnittstellen();
        for (Schnittstelle schnittstelle : notebookSchnittstellen) {
        System.out.println("Unterstützte Schnittstelle: " + schnittstelle);*/

    public Computer(){

    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public double getArbeitsspeicher() {
        return arbeitsspeicher;
    }

    public void setArbeitsspeicher(double arbeitsspeicher) {
        this.arbeitsspeicher = arbeitsspeicher;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public double getMassenspeicher() {
        return massenspeicher;
    }

    public void setMassenspeicher(double massenspeicher) {
        this.massenspeicher = massenspeicher;
    }

    public double getEinzelPreis() {
        return einzelPreis;
    }

    public void setEinzelPreis(double einzelPreis) {
        this.einzelPreis = einzelPreis;
    }

    public Schnittstelle[] getSchnittstellen() {
        return schnittstellen;
    }

    public void setSchnittstellen(Schnittstelle[] schnittstellen) {
        this.schnittstellen = schnittstellen;
    }

    public Schnittstelle[] getSchnittstellenArray() {
        return schnittstellenArray;
    }

    public void setSchnittstellenArray(Schnittstelle[] schnittstellenArray) {
        this.schnittstellenArray = schnittstellenArray;
    }


    public void printComputer(){
        System.out.println("Hersteller: " + hersteller);
        System.out.println("Modell: " + modell);
        System.out.println("Arbeitspeicher: " + arbeitsspeicher + " GB");
        System.out.println("CPU: " + cpu);
        System.out.println("Massenspeicher: " + massenspeicher + " GB");
        System.out.println("Typ: " + typ);
        System.out.println("Einzelpreis: " + einzelPreis);
        System.out.println(schnittstellen.length + " Schnittstellen für dieses Gerät: ");
        for (Schnittstelle item :schnittstellen
        ) {
            System.out.println("- " + item);
        }
    }

    @Override
     public ArrayList<Computer> getAll(){
        //implementierung zu getAll
        return computers;
    }

    @Override
    public Computer getById(int ComputerId) {
        return null;
    }

    @Override
    public void insert(Computer computer) {

    }

    @Override
    public void update(Computer computer) {

    }

    @Override
    public void delete(int ComputerId) {

    }

    @Override
    public void save() {

    }


}
