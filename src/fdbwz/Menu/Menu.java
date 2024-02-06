package fdbwz.Menu;

import java.util.Scanner;

public class Menu {
    int input;
    boolean running = true;


    public void DisplayHauptmenu(){

        while(running = true){
            System.out.println("---------------------------");
            System.out.println("    Was möchten Sie machen?");
            System.out.println("---------------------------");
            System.out.println(" 1. Bestellungen verwalten");
            System.out.println(" 2. Kunden verwalten");
            System.out.println(" 3. Computer verwalten");
            System.out.println(" 4. Programm beenden");
            System.out.println("---------------------------");
            processUserInputMenu();
        }



    }

    public int getUserAction(){
        Scanner scanner = new Scanner(System.in);

        try {
            input = scanner.nextInt();
        }catch (Exception e){
            System.out.println("Bitte geben sie eine ganze *Zahl* ein");
        }
        return input;
    }

    public void processUserInputMenu(){
        getUserAction();

        switch (input){
            case 1:
                displayBestellungsVerwaltung();
                break;
            case 2:
                displayKundenVerwaltung();
                break;
            case 3:
                displayComputerVerwaltung();
                break;
            case 4:
                System.exit(0);
                break;
            default:
        }

    }

    public void displayBestellungsVerwaltung(){
        System.out.println("----------------------------");
        System.out.println("    Bestellungsverwaltung   ");
        System.out.println("----------------------------");
        System.out.println(" 1. Alle Bestellungen ansehen");
        System.out.println(" 2. Bestellung suchen");
        System.out.println(" 3. Bestellung bearbeiten");
        System.out.println(" 4. Bestellung löschen");
        System.out.println(" 5. Zurück ins Hauptmenü ");
        System.out.println("---------------------------");

        processUserInputBestellungsVerwaltung();
    }

    public void processUserInputBestellungsVerwaltung(){
        getUserAction();

        switch (input){
            case 1:
                scheissFunktion();
                break;
            case 2:
                System.out.println("Function to search for one Order");
                break;
            case 3:
                System.out.println("Function to edit an Order");
                break;
            case 4:
                System.out.println("Function to delete an Order");
                break;
            case 5:
                DisplayHauptmenu();
                break;
        }
    }

    public void displayKundenVerwaltung(){
        System.out.println("----------------------------");
        System.out.println("      Kundenverwaltung      ");
        System.out.println("----------------------------");
        System.out.println(" 1. Alle Kunden ansehen");
        System.out.println(" 2. Kunde suchen");
        System.out.println(" 3. Kundendaten bearbeiten");
        System.out.println(" 4. kunde löschen");
        System.out.println(" 5. Zurück ins Hauptmenü ");
        System.out.println("---------------------------");

        processUserInputKundenVerwaltung();
    }

    public void processUserInputKundenVerwaltung(){
        getUserAction();

        switch (input){
            case 1:
                System.out.println("Function to display all Customers");
                break;
            case 2:
                System.out.println("Function to search for one Customer");
                break;
            case 3:
                System.out.println("Function to edit the data a Customer");
                break;
            case 4:
                System.out.println("Function to delete a Customer");
                break;
            case 5:
                DisplayHauptmenu();
                break;
        }
    }

    public void displayComputerVerwaltung(){
        System.out.println("----------------------------");
        System.out.println("     Computerverwaltung     ");
        System.out.println("----------------------------");
        System.out.println(" 1. Alle Computer ansehen");
        System.out.println(" 2. Computer suchen");
        System.out.println(" 3. Computerdaten bearbeiten");
        System.out.println(" 4. Computer löschen");
        System.out.println(" 5. Zurück ins Hauptmenü ");
        System.out.println("---------------------------");

        processUserInputComputerVerwaltung();
    }

    public void processUserInputComputerVerwaltung(){
        getUserAction();

        switch (input){
            case 1:
                System.out.println("Function to display all Computers");
                break;
            case 2:
                System.out.println("Function to search for one Computer");
                break;
            case 3:
                System.out.println("Function to edit the Data of a Computer");
                break;
            case 4:
                System.out.println("Function to delete a Computer");
                break;
            case 5:
                DisplayHauptmenu();
                break;
        }
    }


    public void scheissFunktion(){
        int i;
        System.out.println("Ich bin eine Scheiss Funktion und kann garnichts");
        i = 1;
        System.out.println(i);
    }

}
