package ba.unsa.etf.rpr.t7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        KorisniciModel model = new KorisniciModel();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/forma.fxml"));
        KorisnikController ctrl = new KorisnikController(model);
        loader.setController(ctrl);
        Parent root = loader.load();
        primaryStage.setTitle("Forma");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(480);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
