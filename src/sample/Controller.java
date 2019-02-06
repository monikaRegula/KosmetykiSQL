package sample;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Class is the first window application.
 * User logg in with nick and password from SQLServer
 * @author Monika Regula
 * @version 1.0
 */
public class Controller implements Initializable {
    /**
     * Represents BorderPane.
     */
    @FXML
    private BorderPane borderPane;
    /**
     * Represents Tab.
     */
    @FXML
    private Tab produkty;
    /**
     * Represents TableView of products.
     */
    @FXML
    private TableView<Product> tableviewProdukty;
    /**
     * Represents TableView column of products.
     */
    @FXML
    private TableColumn<String,Product> columnProdukt;
    /**
     * Represents TableView column of producents.
     */
    @FXML
    private TableColumn<String,Product> columnProducent;
    /**
     * Represents TableView column of prices.
     */
    @FXML
    private TableColumn<String,Product> columnCena;
    /**
     * Represents Combobox to choose cosmetics category name.
     */
    @FXML
    private ComboBox<String> comboBox;
    /**
     * Represents combobox to choose bank.
     */
    @FXML
    private ComboBox<String> comboBoxBank;
    /**
     * Represents Observable list of comboboxBank.
     */
    private ObservableList<String> banks =
            FXCollections.observableArrayList(
                    "ING","PKO","SANTANDER"
            );

    /**
     * Represents Tab of faktury.
     */
    @FXML
    private Tab faktury;
    /**
     * Represents Tableview of faktury.
     */
    @FXML
    private TableView<Faktury> tableviewFaktury;
    /**
     * Represents Tableview column of imie.
     */
    @FXML
    private TableColumn<String,Faktury> columnImie;
    /**
     * Represents Tableview column of nazwisko.
     */
    @FXML
    private TableColumn<String,Faktury> columnNazwisko;
    /**
     * Represents Tableview column of nr faktury.
     */
    @FXML
    private TableColumn<String,Faktury> columnNrFaktury;
    /**
     * Represents Tableview column of data sprzedazy.
     */
    @FXML
    private TableColumn<String,Faktury> columnDataSprzedazy;
    /**
     * Represents Tableview column of bank.
     */
    @FXML
    private TableColumn<String,Faktury> columnBank;
    /**
     * Represents Tab Klienci.
     */
    @FXML
    private Tab klienci;
    /**
     * Represents Tableview Klienci.
     */
    @FXML
    private TableView<Klienci> tableviewKlienci;
    /**
     * Represents Tableview column imie .
     */
    @FXML
    private TableColumn<String,Klienci> column3Imie;
    /**
     * Represents Tableview column nazwisko .
     */
    @FXML
    private TableColumn<String,Klienci> column3Nazwisko;
    /**
     * Represents Tableview column telefon .
     */
    @FXML
    private TableColumn<String,Klienci> columnTelefon;
    /**
     * Represents Tableview column email .
     */
    @FXML
    private TableColumn<String,Klienci> columnEmail;
    /**
     * Represents Tableview column kod pocztowy .
     */
    @FXML
    private TableColumn<String,Klienci> columnKodPocztowy;
    /**
     * Represents Textfield with Surname to check faktura.
     */
    @FXML
    private TextField txtFinSurname;
    /**
     * Represents Textfield with Name to check faktura.
     */
    @FXML
    private TextField txtFindName;
    /**
     * Represents Textfield with product name to add.
     */
    @FXML
    private TextField txtProdukt;
    /**
     * Represents Textfield with producent name to add.
     */
    @FXML
    private TextField txtProducent;
    /**
     * Represents Textfield with price  to add.
     */
    @FXML
    private TextField txtCena;
    /**
     * Represents combobox to choose category product to add.
     */
    @FXML
    private ComboBox<String> comboBoxAdd;
    /**
     * Represents button DODAJ.
     */
    @FXML
    private Button btnDodaj;

    @FXML
    private Button btn;

    /**
     * Represents Prepared Statement
     */
    private PreparedStatement pst= null;
    /**
     * Represents Resultset
     */
    private ResultSet rs= null;
    /**
     * Represents Connection
     */
    private Connection con = null;

    /**
     * Represents Observable list of products (tableview)
     */
    private ObservableList<Product> productData;
    /**
     * Represents Observable list of faktury (tableview)
     */
    private ObservableList<Faktury> fakturyData;
    /**
     * Represents Observable list of klienci (tableview)
     */
    private ObservableList<Klienci> klienciData;
    /**
     * Represents Observable list of single client(tableview)
     */
    private ObservableList<Faktury> singleClient;
    /**
     * Represents Observable list of options that will be shown in combobox.
     */
    final ObservableList options = FXCollections.observableArrayList();
    /**
     * Represents Observable list of choices that will be shown in comboboxAdd.
     */
    final ObservableList choices = FXCollections.observableArrayList();


    /**
     * This method loads data to Table Klienci.
     * It uses Connection to connect with SQLServer.
     */
    private void loadDataToKlienci(){

    try  {
        //procedura SELECT wybieram konkretne dane z tablic
        pst = con.prepareStatement("SELECT        k.imie, k.nazwisko, kk.nr_tel, kk.email, a.kod_pocztowy\n" +
                        "FROM            dbo.Klienci AS k LEFT OUTER JOIN\n" +
                        "                         dbo.Adresy AS a ON k.id_adresu = a.id_adresu INNER JOIN\n" +
                        "                         dbo.Kontakty AS kk ON k.id_kontaktu = kk.id_kontaktu");

        //uruchamiana jest kwerenda
        rs = pst.executeQuery();

        while (rs.next()) {
            // do listy obserwatorów dodawany jest nowy obiekt klasy Klienci
            klienciData.add(new Klienci(rs.getString("imie"),rs.getString("nazwisko"),
                    rs.getString("nr_tel"),rs.getString("email"),rs.getString("kod_pocztowy")));
        }
        //zamykam procedure
        pst.close();
        //zamykam zestaw wyników
        rs.close();
    }

    catch (SQLException e) {
        e.printStackTrace();
    }
//dodaje do tablicy dane z listy obsrwatorów
    tableviewKlienci.setItems(klienciData);

}

    /**
     * This method loads data to table Produkty from SQLServer.
     */
    private void loadDataToProdukty(){

    try  {
        //przygotowanie kwerendy
        pst = con.prepareStatement("SELECT   nazwa_produktu, cena_brutto, nazwa_producenta\n" +
                        "FROM Produkty3 ");
        //wywołanie kwerendy
        rs = pst.executeQuery();

        while (rs.next()) {
            productData.add(new Product(rs.getString("nazwa_produktu"),
                    rs.getString("nazwa_producenta"),rs.getString("cena_brutto")));
        }
        pst.close();
        rs.close();
    }

    catch (SQLException e) {
        e.printStackTrace();
    }

    tableviewProdukty.setItems(productData);

}

    /**
     * This method loads data to Table Faktury form SQLServer
     */
    private void loadDataToFaktury(){

        try  {
            pst = con.prepareStatement("SELECT        zp.ilosc_produktu, z.data_zlozenia_zamowienia, z.czy_zaplacono, f.nr_faktury, f.nazwa_banku, f.brutto, f.dataSprzedazy,k.id_klienta, k.imie, k.nazwisko, a.ulica, a.kod_pocztowy, a.nr_domu, a.miasto, kk.email, kk.nr_tel, p.nazwa_produktu, \n" +
                    "                         p.cena_brutto, pp.nazwa_producenta, kat.nazwa_kategorii\n" +
                    "FROM            dbo.Zamowienia AS z INNER JOIN\n" +
                    "                         dbo.Adresy AS a INNER JOIN\n" +
                    "                         dbo.Klienci AS k ON a.id_adresu = k.id_adresu INNER JOIN\n" +
                    "                         dbo.Faktury AS f ON k.id_klienta = f.id_klienta INNER JOIN\n" +
                    "                         dbo.Kontakty AS kk ON k.id_kontaktu = kk.id_kontaktu ON z.id_klienta = k.id_klienta INNER JOIN\n" +
                    "                         dbo.Zamowienie_produktu AS zp ON z.id_zamowienia = zp.id_zamowienia INNER JOIN\n" +
                    "                         dbo.Producenci AS pp INNER JOIN\n" +
                    "                         dbo.Kategorie AS kat ON pp.id_kategorii = kat.id_kategorii INNER JOIN\n" +
                    "                         dbo.Produkty AS p ON pp.id_producenta = p.id_producenta ON zp.id_produktu = p.id_produktu");

            rs = pst.executeQuery();

            while (rs.next()) {

                fakturyData.add(new Faktury(rs.getString("imie"),rs.getString("nazwisko"),
                        rs.getString("nr_faktury"), rs.getString("dataSprzedazy"),rs.getString("nazwa_banku")));

            }
            pst.close();
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        tableviewFaktury.setItems(fakturyData);

    }


    /**
     * This method sets Tables' cells
     */
    private void setCellTable(){
        //ustawiam w tabelach wartości kolumn
        columnProdukt.setCellValueFactory(new PropertyValueFactory<>("nazwa_produktu"));
        columnProducent.setCellValueFactory(new PropertyValueFactory<>("nazwa_producenta"));
        columnCena.setCellValueFactory(new PropertyValueFactory<>("cena_brutto"));

        columnImie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        columnNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnNrFaktury.setCellValueFactory(new PropertyValueFactory<>("nr_faktury"));
        columnDataSprzedazy.setCellValueFactory(new PropertyValueFactory<>("dataSprzedazy"));
        columnBank.setCellValueFactory(new PropertyValueFactory<>("nazwa_banku"));

        column3Imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        column3Nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        columnTelefon.setCellValueFactory(new PropertyValueFactory<>("nr_tel"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnKodPocztowy.setCellValueFactory(new PropertyValueFactory<>("kod_pocztowy"));
    }


    /**
     * This method initializes comboboxes, data as obesrablelists and implements Connection.
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //tworze połączenie z bazą danych
        //kreując jako COnnection obiekt klasy DBConnection
    con  =DBConnection.kosmetykiConnection();

    //ustawiam początkowy komunikat comboboxu
    comboBoxBank.setPromptText("Wybierz faktury z banku:");
    //wywołuję metodę do wypełnienia comboboxu wartościami
    fillComboBoxBank();
    //dodaje wartości do comboboxu
    comboBoxBank.getItems().addAll(options);
    //ustawiam początkowy komunikat comboboxu
    comboBox.setPromptText("Wybierz kategorię produktów");
    //wywołuję metodę do wypełnienia comboboxu wartościami
    fillComboBox();
    //dodaje wartości do comboboxu
    comboBox.getItems().addAll(choices);
    //ustawiam początkowy komunikat comboboxu
    comboBoxAdd.setPromptText("Kategoria");
    //dodaje wartości do comboboxu
    comboBoxAdd.getItems().addAll(choices);

    //ustawiam listę obserwatorów klasy Produkt
      productData = FXCollections.observableArrayList();
        //ustawiam listę obserwatorów klasy Faktury
      fakturyData = FXCollections.observableArrayList();
        //ustawiam listę obserwatorów klasy Klienci
      klienciData = FXCollections.observableArrayList();
        //ustawiam listę obserwatorów klasy Klienci
      singleClient = FXCollections.observableArrayList();

      //ładuję dane do tablic
        loadDataToFaktury();
        loadDataToKlienci();
        loadDataToProdukty();

        //ustawiam wartości kolumn tabel
        setCellTable();

    }

    /**
     * This method is activated when combobox is changed.
     * @param event comboBankChanged
     */
    @FXML
    void comboBankChanged(ActionEvent event) {

        String choice = comboBoxBank.getValue();
        System.out.println("Wybrany bank: "+choice);
        tableviewFaktury.getItems().clear();

        String query ="select * from dbo.IleFaktorBanku('"+choice+"')";

        try  {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                fakturyData.add(new Faktury(rs.getString("imie"),rs.getString("nazwisko"),
                rs.getString("nr_faktury"), rs.getString("dataSprzedazy"),rs.getString("nazwa_banku")));
            }
            pst.close();
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }


        tableviewFaktury.setItems(fakturyData);

    }

    /**
     * This method is activated when combobox is changed.
     * @param event comboChanged
     */
    @FXML
    void comboChanged(ActionEvent event) {

        String choice = comboBox.getValue();
        System.out.println("Wybrana kategoria: "+choice);
        //czyszcze tabele Produkty
        tableviewProdukty.getItems().clear();
        //wyswietlam tylko dane z wybranej kategorii kosmetyków
        kategoriaWyswietl(choice);

    }

    /**
     * This method shows in table Produkty data only by choosen cosmetic category name.
     * @param choice choice (value from combobox)
     */
    private void kategoriaWyswietl(String choice){

        String query ="select  nazwa_produktu,nazwa_producenta,cena_brutto\n" +
                "from Produkty3 where  nazwa_kategorii = '"+choice+"'";

        try  {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                productData.add(new Product(rs.getString("nazwa_produktu"),
                        rs.getString("nazwa_producenta"),rs.getString("cena_brutto")));
            }
            pst.close();
            rs.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }

        tableviewProdukty.setItems(productData);

    }

    /**
     * This method only prints value from combobox to console.
     * @param event comboAddChanged
     */
    @FXML
    void comboAddChanged(ActionEvent event) {
        String choice = (String) comboBoxAdd.getValue();
        System.out.println("Wybrana kategoria do dodania: "+choice);
    }


    /**
     * This method activates when button DODAJ is clicked.
     * Then single clients' facture is shown in table Faktury.
     * @param event FindSurname
     */
    @FXML
    void btnFindSurnamePressed(ActionEvent event) {
        //czyszcze tabele
    tableviewFaktury.getItems().clear();
    //lista obserwatorów pod pojedynczego klienta
    singleClient = tableviewFaktury.getSelectionModel().getSelectedItems();
    singleClient.forEach(fakturyData::remove);
    //wprowadzone dane do textfieldow:
    String surname = String.valueOf(txtFinSurname.getText());
    String name = String.valueOf(txtFindName.getText());

        try  {

            pst = con.prepareStatement("select* from dbo.IleFaktorOsobyFX('"+name+"','"+surname+"')");

            rs = pst.executeQuery();

            while (rs.next()) {
                fakturyData.add(new Faktury(rs.getString("imie"),rs.getString("nazwisko"),
                        rs.getString("nr_faktury"), rs.getString("dataSprzedazy"),rs.getString("nazwa_banku")));
                singleClient = fakturyData;
            }
            pst.close();

            rs.close();
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }

        tableviewFaktury.setItems(singleClient);

    }

    /**
     * This method fill comboboxBank with distinct values from
     * column 'bank name'.
     */
    private void fillComboBoxBank(){

    String query = "Select distinct nazwa_banku FROM Faktury";

        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()){
        options.add(rs.getString("nazwa_banku"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method fill comboboxBank with distinct values from
     * column 'cosmetics category name'.
     */
    private void fillComboBox(){

        String query = "Select distinct nazwa_kategorii FROM Produkty3";


        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()){
                choices.add(rs.getString("nazwa_kategorii"));
            }
            pst.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * This method adds new product do database in SQLServer.
     * Then shows it in table in JAVAFX
     * @param event  btnDodajPressed
     */
    @FXML
    void btnDodajPressed(ActionEvent event) {
        //pobieram dane wprowadzone przy dodawaniu produktu:
    String product = txtProdukt.getText().toString();
    String producent = txtProducent.getText().toString();
    String cena = txtCena.getText();
    String choice = (String) comboBoxAdd.getValue();

    //czyszcze tabele produkty
    tableviewProdukty.getItems().clear();
    //wywołuję metodę dodającą produkt do bazy danych
    addToDatabase(product,producent,cena,choice);
    //wywołuję metodę wyświetlająca dla wybranej kategorii produkty
    kategoriaWyswietl(choice);
    }


    /**
     * This method adds product to SQLServer.
     * @param product product
     * @param producent producent
     * @param cena cena
     * @param choice choice
     */
    private void addToDatabase(String product,String producent,String cena,String choice){
        String query ="INSERT INTO dbo.Produkty3 (nazwa_produktu,nazwa_producenta,cena_brutto,nazwa_kategorii) VALUES" +
                "('"+product + "','"+ producent + "',"+cena+",'"+ choice+ "')";

        try  {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

                while(rs.next()){
                    System.out.println(rs.toString());
                }

            pst.close();
            rs.close();
        } catch(SQLServerException ex){
            //potrzebna jest obsługa tego wyjątku bo nie używam ResultSet
            //ponieważ nie wyciągam danych z bazy tylko do niej dodaję
            System.out.println("Nie był zdefiniowany ResultSet do bazy danych");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
