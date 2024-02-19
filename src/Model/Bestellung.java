package Model;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

public class Bestellung {

    private ObjectId BestellungsId;
    private String Bestellnummer;
    private Date Bestelldatum;
    private Kunde kunde;
    private double Total;
    private ArrayList<Bestellposition> bestellpositionen;

    public Bestellung() {
    }

    public Bestellung(ObjectId bestellungsId, String bestellnummer, Date bestelldatum, Kunde kunde, double total, ArrayList<Bestellposition> bestellpositionen) {
        BestellungsId = bestellungsId;
        Bestellnummer = bestellnummer;
        Bestelldatum = bestelldatum;
        this.kunde = kunde;
        Total = total;
        this.bestellpositionen = bestellpositionen;
    }

    public Bestellung(ObjectId id, Date bestelldatum, double total, ArrayList<Bestellposition> bestellpositionen, Kunde kunde) {
    }

    public ObjectId getBestellungsId() {
        return BestellungsId;
    }

    public void setBestellungsId(ObjectId bestellungsId) {
        BestellungsId = bestellungsId;
    }

    public String getBestellnummer() {
        return Bestellnummer;
    }

    public void setBestellnummer(String bestellnummer) {
        Bestellnummer = bestellnummer;
    }

    public Date getBestelldatum() {
        return Bestelldatum;
    }

    public void setBestelldatum(Date bestelldatum) {
        Bestelldatum = bestelldatum;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public ArrayList<Bestellposition> getBestellpositionen() {
        return bestellpositionen;
    }

    public void setBestellpositionen(ArrayList<Bestellposition> bestellpositionen) {
        this.bestellpositionen = bestellpositionen;
    }

    @Override
    public String toString() {

        Computer computer = new Computer();
        double preis;
        int stk;


        for (Bestellposition bestellposition: bestellpositionen ){
            computer = bestellposition.getComputer();
            preis = bestellposition.getPreis();
            stk = bestellposition.getStueckZahl();

            double totalbestellposition = stk * computer.getEinzelPreis();
            Total = Total + totalbestellposition;
        }


        return  "\n ---------------------------------" +
                "\n Bestellungs Uebersicht ----------" +
                "\n Id" + BestellungsId +
                "\n Bestellnummer: " + Bestellnummer +
                "\n Bestelldatum: " + Bestelldatum +
                "\n Kunde  --------------------------" +
                "\n Kunden Id " + kunde.getId() +
                "\n Name: " + kunde.getNachname() +
                "\n Vorname: " + kunde.getVorname() +
                "\n Computer ------------------------" +
                "\n Computer Id: " + computer.getId() +
                "\n Hersteller: " + computer.getHersteller() +
                "\n Modell: " + computer.getModell() +
                "\n TOTAL ---------------------------" +
                "\n Total Preis: " + Total +
                "\n ---------------------------------";
    }

}
