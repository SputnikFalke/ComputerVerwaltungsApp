package Model;

import org.bson.types.ObjectId;

public class Computer {

    private ObjectId id;
    private String hersteller;
    private String modell;
    private double arbeitsspeicher; //in GB
    private String cpu;
    private double massenspeicher; //in GB
    private String typ;
    private double einzelPreis;

    private Schnittstellen schnittstelle;



    public Computer() {

    }

    public Computer(ObjectId id, String hersteller, String modell, double arbeitsspeicher, String cpu, double massenspeicher, String typ, double einzelPreis, Schnittstellen schnittstelle) {
        this.id = id;
        this.hersteller = hersteller;
        this.modell = modell;
        this.arbeitsspeicher = arbeitsspeicher;
        this.cpu = cpu;
        this.massenspeicher = massenspeicher;
        this.typ = typ;
        this.einzelPreis = einzelPreis;
        this.schnittstelle = schnittstelle;
    }

    public Computer(String hersteller, String modell) {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public double getEinzelPreis() {
        return einzelPreis;
    }

    public void setEinzelPreis(double einzelPreis) {
        this.einzelPreis = einzelPreis;
    }

    public Schnittstellen getSchnittstelle() {
        return schnittstelle;
    }

    public void setSchnittstelle(Schnittstellen schnittstelle) {
        this.schnittstelle = schnittstelle;
    }

    @Override
    public String toString() {
        return  "\n --------------------------------"+
                "\n Computer: " +
                "\n Id" + id +
                "\n Hersteller: " + hersteller +
                "\n Modell: " + modell +
                "\n Arbeitsspeicher=" + arbeitsspeicher +
                "\n CPU: " + cpu +
                "\n Massenspeicher: '" + massenspeicher +
                "\n Einzel Preis: " + einzelPreis+
                "\n --------------------------------";
    }

}
