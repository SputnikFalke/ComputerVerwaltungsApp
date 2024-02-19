package Model;

public class Bestellposition {

    private Computer computer;
    private double preis;
    private int stueckZahl;

    public Bestellposition() {
    }

    public Bestellposition(Computer computer, double preis, int stueckZahl) {
        this.computer = computer;
        this.preis = preis;
        this.stueckZahl = stueckZahl;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public int getStueckZahl() {
        return stueckZahl;
    }

    public void setStueckZahl(int stueckZahl) {
        this.stueckZahl = stueckZahl;
    }
}
