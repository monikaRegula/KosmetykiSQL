package sample;

/**
 * Class Faktury represents table Faktury from SQL Database
 * @author Monika Regula
 * @version 1.0
 */
public class Faktury {
    /**
     * Represents name.
     */
    private String imie;
    /**
     * Represents surname.
     */
    private String nazwisko;
    /**
     * Represents nr of invoice.
     */
    private String nr_faktury;
    /**
     * Represents date of selling.
     */
    private String dataSprzedazy;
    /**
     * Represents name of bank.
     */
    private String nazwa_banku;

    /**
     * This contructor makes an Object representing Invoice.
     * @param imie name
     * @param nazwisko surnmae
     * @param nr_faktury invoice nr
     * @param dataSprzedazy date of selling
     * @param nazwa_banku bank name
     */
    public Faktury(String imie, String nazwisko, String nr_faktury, String dataSprzedazy, String nazwa_banku) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_faktury = nr_faktury;
        this.dataSprzedazy = dataSprzedazy;
        this.nazwa_banku = nazwa_banku;
    }

    /**
     * This method gets name.
     * @return imie
     */
    public String getImie() { return imie; }

    /**
     * This method gets surname.
     * @return nazwisko
     */
    public String getNazwisko() { return nazwisko; }

    /**
     * This method gets invoice nr.
     * @return nr_faktury
     */
    public String getNr_faktury() { return nr_faktury; }

    /**
     * THis method gets date of selling.
     * @return dataSprzedazy
     */
    public String getDataSprzedazy() { return dataSprzedazy; }

    /**
     * his method gets bank name.
     * @return nazwa_banku
     */
    public String getNazwa_banku() { return nazwa_banku; }
}
