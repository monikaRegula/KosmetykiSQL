package sample;

/**
 * Class Klienci represents table Klienci from SQL Database.
 * It is used when Connection with query with database is executed.
 * @author Monika Regula
 * @version 1.0
 */
public class Klienci {

    /**
     * Represent name.
     */
    private String imie;
    /**
     * Represents surname.
     */
    private String nazwisko;
    /**
     * Represents phone number.
     */
    private String nr_tel;
    /**
     * Represents email.
     */
    private String email;
    /**
     * Represents zip code.
     */
    private String kod_pocztowy;

    /**
     * Ths constructor makes an Object representing Client.
     * @param imie name
     * @param nazwisko surnmae
     * @param nr_tel phone number
     * @param email email
     * @param kod_pocztowy zip code
     */
    public Klienci(String imie, String nazwisko, String nr_tel, String email, String kod_pocztowy) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nr_tel = nr_tel;
        this.email = email;
        this.kod_pocztowy = kod_pocztowy;
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
     * This method gets phonenumber.
     * @return nr_telefonu
     */
    public String getNr_tel() { return nr_tel; }

    /**
     * This method gets email.
     * @return email
     */
    public String getEmail() { return email; }

    /**
     * This method gets zip code.
     * @return kod_pocztowy
     */
    public String getKod_pocztowy() { return kod_pocztowy; }

}
