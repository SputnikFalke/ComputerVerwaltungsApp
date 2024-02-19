package Model;

public class Schnittstellen {

    private int anzUsbSchnittstellen;
    private int anzUsbCSchnittstellen;
    private int anzHdmiSchnittstellen;
    private int anzLanSchnittstellen;

    public Schnittstellen(int anzUsbSchnittstellen, int anzUsbCSchnittstellen, int anzHdmiSchnittstellen, int anzLanSchnittstellen) {
        this.anzUsbSchnittstellen = anzUsbSchnittstellen;
        this.anzUsbCSchnittstellen = anzUsbCSchnittstellen;
        this.anzHdmiSchnittstellen = anzHdmiSchnittstellen;
        this.anzLanSchnittstellen = anzLanSchnittstellen;
    }

    public int getAnzUsbSchnittstellen() {
        return anzUsbSchnittstellen;
    }

    public void setAnzUsbSchnittstellen(int anzUsbSchnittstellen) {
        this.anzUsbSchnittstellen = anzUsbSchnittstellen;
    }

    public int getAnzUsbCSchnittstellen() {
        return anzUsbCSchnittstellen;
    }

    public void setAnzUsbCSchnittstellen(int anzUsbCSchnittstellen) {
        this.anzUsbCSchnittstellen = anzUsbCSchnittstellen;
    }

    public int getAnzHdmiSchnittstellen() {
        return anzHdmiSchnittstellen;
    }

    public void setAnzHdmiSchnittstellen(int anzHdmiSchnittstellen) {
        this.anzHdmiSchnittstellen = anzHdmiSchnittstellen;
    }

    public int getAnzLanSchnittstellen() {
        return anzLanSchnittstellen;
    }

    public void setAnzLanSchnittstellen(int anzLanSchnittstellen) {
        this.anzLanSchnittstellen = anzLanSchnittstellen;
    }
}
