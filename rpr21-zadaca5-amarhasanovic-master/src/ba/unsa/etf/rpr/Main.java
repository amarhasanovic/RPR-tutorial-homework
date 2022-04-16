package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GlavnaController ctrl = new GlavnaController();
        Locale defaultLocale = new Locale("bs");
        ResourceBundle bundle = ResourceBundle.getBundle("Translation", defaultLocale);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml" ), bundle);
        loader.setController(ctrl);
        Parent root = loader.load();
        primaryStage.setTitle("Glavna");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
        //primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);

    }
}