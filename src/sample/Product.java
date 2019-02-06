package sample;

/**
 * Class Product represnts Table Produkty in SQLServer.
 * @author Monika Regula
 * @version 1.0
 */

public class Product {


    /**
     * Represents product.
     */
    private String nazwa_produktu;
    /**
     * Represents producent.
     */
    private String nazwa_producenta;
    /**
     * Represents price.
     */
    private String cena_brutto;


    /**
     * This constructor creates an Object represnting Product.
     * @param nazwa_produktu nazwa_produktu
     * @param nazwa_producenta nazwa_producenta
     * @param cena_brutto cena_brutto
     */
    public Product(String nazwa_produktu, String nazwa_producenta, String cena_brutto) {
        this.nazwa_produktu = nazwa_produktu;
        this.nazwa_producenta = nazwa_producenta;
        this.cena_brutto = cena_brutto;
    }

    /**
     * This method gets product name.
     * @return nazwa_produktu
     */
    public String getNazwa_produktu() { return nazwa_produktu; }

    /**
     * This method gets producent name.
     * @return nazwa_producenta
     */
    public String getNazwa_producenta() { return nazwa_producenta; }

    /**
     * This method gets price.
     * @return cena_brutto
     */
    public String getCena_brutto() { return cena_brutto; }
}
