package Model;

import Controller.Controller;

import java.util.Scanner;

public class Menu {

    Controller controller;
    Scanner scanner;
    int input;

    public Menu(Controller controller){
        this.controller = controller;
    }
    public void DisplayHauptmenu(){
        System.out.println("Was möchten sie machen?");
        System.out.println("1. Computer verwalten");
        System.out.println("2. Kunden verwalten");
        System.out.println("3. Bestellungen verwalten");
        System.out.println("4. Beenden");
        VerarbeiteEingabeHauptmenu();
    }

    public void VerarbeiteEingabeHauptmenu(){
        scanner = new Scanner(System.in);
        input = scanner.nextInt();

        switch (input){

            case 1: DisplayComputerVerwaltung();
                break;
            case 2: DiplayKundenVerwaltung();
                break;
            case 3: DisplayBestellungsVerwaltung();
                break;
            case 4: System.exit(0);
                break;

        }

    }

    public  void DisplayComputerVerwaltung(){

        System.out.println("Was möchten sie machen?");
        System.out.println("1. Computer anschauen");
        System.out.println("2. Computer suchen");
        System.out.println("3. Computer hinzufügen");
        System.out.println("4. Computer bearbeiten");
        System.out.println("5. Computer löschen");
        System.out.println("6. Zum Hauptmenu");
        VerarbeiteComputerVerwaltungsInput();
    }

    public void VerarbeiteComputerVerwaltungsInput(){
        scanner = new Scanner(System.in);
        input = scanner.nextInt();

        switch (input) {
            case 1 -> controller.getAllComputers();
            case 2 -> controller.getComputerById();
            case 3 -> controller.addComputer();
            case 4 -> controller.UpdateComputer();
            case 5 -> controller.deleteComputer();
            case 6 -> DisplayHauptmenu();
        }

    }
    public void DiplayKundenVerwaltung(){

        System.out.println("Was möchten sie machen?");
        System.out.println("1. Kunden anschauen");
        System.out.println("2. Kunden suchen");
        System.out.println("3. Kunden hinzufügen");
        System.out.println("4. Kunden bearbeiten");
        System.out.println("5. Kunden löschen");
        System.out.println("6. Zum Hauptmenu");
        VerarbeiteKundeVerwaltungsInput();

    }

    public void VerarbeiteKundeVerwaltungsInput(){
        scanner = new Scanner(System.in);
        input = scanner.nextInt();

        switch (input){
            case 1: controller.getAllKunden();
                break;
            case 2: controller.getKundeById();
                break;
            case 3: controller.addKunde();
                break;
            case 4: controller.UpdateKunde();
                break;
            case 5: controller.deleteKunde();
                break;
            case 6: DisplayHauptmenu();
                break;

        }

    }

    public void DisplayBestellungsVerwaltung(){

        System.out.println("Was möchten sie machen?");
        System.out.println("1. Bestellungen anschauen");
        System.out.println("2. Bestellung suchen");
        System.out.println("3. Bestellung hinzufügen");
        System.out.println("4. Bestellung bearbeiten");
        System.out.println("5. Bestellung löschen");
        System.out.println("6. Zum Hauptmenu");
        VerarbeiteBestellungsverawltungInput();
    }

    public void VerarbeiteBestellungsverawltungInput(){
        scanner = new Scanner(System.in);
        input = scanner.nextInt();

        switch (input){
            case 1: controller.getAllBestellungen();
                break;
            case 2: controller.getBestellungById();
                break;
            case 3: controller.addBestellung();
                break;
            case 4:
                break;
            case 5: controller.deleteBestellung();
                break;
            case 6: DisplayHauptmenu();
                break;

        }

    }

}
