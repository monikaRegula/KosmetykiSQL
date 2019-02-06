package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**
 * Class is responsible for logging to SQLServer
 * @author Monika Regula
 * @version 1.0
 */
public class LoginController implements Initializable {
    /**
     * Represents Connection.
     */
    private Connection con;
    /**
     * Represent Prepared Statement.
     */
    private PreparedStatement pst;
    /**
     * Represents Resukt Set.
     */
    private ResultSet rs;
    /**
     * Represennts Textfield with nick.
     */
    @FXML
    private TextField txtNick;
    /**
     * Represents Textfield with password.
     */
    @FXML
    private PasswordField password;

    /**
     * Represents button
     */
    @FXML
    private Button btnLoguj;
    /**
     * Represents anchor pane.
     */
    @FXML
    private AnchorPane anchorPane;


    /**
     * This method initializes the Connection with Databasse.
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        con = DBConnection.kosmetykiConnection();
    }

    /**
     * This methd is actiavted when button LOGUJ is clicked.
     * @param event btnLogujCLicked
     * @throws IOException IOException
     */
    @FXML
    void btnLogujClicked(ActionEvent event) throws IOException {

        System.out.println("Nick: "+getNick()+" hasło: "+getPassword());
        //jeśli wartości pobrane z Textfieldów są równe wartośviom z bazy danych to zmiana sceny
if ((txtNick.getText().equals(getNick()))  && (password.getText().equals(getPassword())) ){

    Stage primarystage = (Stage) anchorPane.getScene().getWindow();
    primarystage.close();
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    primarystage.setTitle("SKLEP");
    primarystage.setScene(new Scene(root,1000 , 600));
    primarystage.show();

}else{
//jak nie to wyskakuje alert o niepoprawnych wprowadzonych danych
    Alert alert = new Alert(Alert.AlertType.NONE,"Nieprawidłowy nick lub hasło", ButtonType.OK);
    alert.setTitle("Invalid");
    alert.showAndWait();
}
    }


    /**
     * This method gets nick form database
     * @return nick
     */
    private String getNick(){

        String nick="";

        try{
            pst = con.prepareStatement("Select nick from Uzytkownicy where nick=?");
            pst.setString(1,txtNick.getText());
            rs = pst.executeQuery();
            if (rs.next()){
                nick = rs.getString(1);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return nick;
    }


    /**
     * This method gets password form database
     * @return password
     */
    private String getPassword(){

        String haslo="";

        try{
            pst = con.prepareStatement("Select password from Uzytkownicy where nick=?");
            pst.setString(1,txtNick.getText());
            rs = pst.executeQuery();
            if (rs.next()){
                haslo = rs.getString(1);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return haslo;
    }




}
