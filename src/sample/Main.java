package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Class Main creates Stage with Scene nd launches apllication window
 * @author Monika Regula
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Method sets Scene sizes and shows it
     * @param primaryStage Stage
     * @throws Exception Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("LOGOWANIE");
        primaryStage.setScene(new Scene(root,350 , 340));

        primaryStage.show();

    }

    /**
     * Method launches the app
     * @param args initial arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
