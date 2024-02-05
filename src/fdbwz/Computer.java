package fdbwz;

import java.util.ArrayList;
import java.util.List;

public class Computer {
    private String hersteller;
    private String modell;
    private double arbeitsspeicher; //in GB
    private String cpu;
    private double massenspeicher; //in GB
    private enum typ{
        Desktop,
        Notebook
    }
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


}
