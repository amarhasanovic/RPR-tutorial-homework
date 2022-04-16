package ba.unsa.etf.rpr.t7;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class KorisnikController {
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldUsername;
    public ListView<Korisnik> listKorisnici;
    public PasswordField fldPassword;
    public ImageView btnImage;
    private KorisniciModel model;

    public KorisnikController(KorisniciModel model) {
        this.model = model;
    }

    @FXML
    public void initialize() {
        btnImage.setImage(new Image((new File("resources/pictures/face-smile.png")).toURI().toString()));
        listKorisnici.setItems(model.getKorisnici());

        listKorisnici.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            model.setTrenutniKorisnik(newKorisnik);

            String imageUrl = model.getTrenutniKorisnik().getSlika(); //preuzimamo sliku
            if(imageUrl.equals("resources/pictures/face-smile.png")) {
                File file = new File(imageUrl);
                Image image = new Image(file.toURI().toString());
                btnImage.setImage(image);
            }
            else {
                Image image = new Image(imageUrl);
                btnImage.setImage(image);
            }
            btnImage.maxWidth(128);
            btnImage.minHeight(128);
            btnImage.setFitHeight(128);
            btnImage.setFitWidth(128);

            listKorisnici.refresh();
         });

        model.trenutniKorisnikProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if (oldKorisnik != null) {
                fldIme.textProperty().unbindBidirectional(oldKorisnik.imeProperty() );
                fldPrezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty() );
                fldEmail.textProperty().unbindBidirectional(oldKorisnik.emailProperty() );
                fldUsername.textProperty().unbindBidirectional(oldKorisnik.usernameProperty() );
                fldPassword.textProperty().unbindBidirectional(oldKorisnik.passwordProperty() );
            }
            if (newKorisnik == null) {
                fldIme.setText("");
                fldPrezime.setText("");
                fldEmail.setText("");
                fldUsername.setText("");
                fldPassword.setText("");
            }
            else {
                fldIme.textProperty().bindBidirectional( newKorisnik.imeProperty() );
                fldPrezime.textProperty().bindBidirectional( newKorisnik.prezimeProperty() );
                fldEmail.textProperty().bindBidirectional( newKorisnik.emailProperty() );
                fldUsername.textProperty().bindBidirectional( newKorisnik.usernameProperty() );
                fldPassword.textProperty().bindBidirectional( newKorisnik.passwordProperty() );
            }
        });

        fldIme.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldIme.getStyleClass().removeAll("poljeNijeIspravno");
                fldIme.getStyleClass().add("poljeIspravno");
            } else {
                fldIme.getStyleClass().removeAll("poljeIspravno");
                fldIme.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPrezime.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
                fldPrezime.getStyleClass().add("poljeIspravno");
            } else {
                fldPrezime.getStyleClass().removeAll("poljeIspravno");
                fldPrezime.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldEmail.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldEmail.getStyleClass().removeAll("poljeNijeIspravno");
                fldEmail.getStyleClass().add("poljeIspravno");
            } else {
                fldEmail.getStyleClass().removeAll("poljeIspravno");
                fldEmail.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldUsername.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                fldUsername.getStyleClass().add("poljeIspravno");
            } else {
                fldUsername.getStyleClass().removeAll("poljeIspravno");
                fldUsername.getStyleClass().add("poljeNijeIspravno");
            }
        });

        fldPassword.textProperty().addListener((obs, oldIme, newIme) -> {
            if (!newIme.isEmpty()) {
                fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
                fldPassword.getStyleClass().add("poljeIspravno");
            } else {
                fldPassword.getStyleClass().removeAll("poljeIspravno");
                fldPassword.getStyleClass().add("poljeNijeIspravno");
            }
        });
    }

    public void dodajAction(ActionEvent actionEvent) {
        model.dodajKorisnika();
        Korisnik korisnik = new Korisnik("", "", "", "", "");
        korisnik.setId(model.dajId());
        model.getKorisnici().add(korisnik);
        listKorisnici.getSelectionModel().selectLast();
    }

    public void krajAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void obrisiAction(ActionEvent actionEvent){
        model.obrisiKorisnika();
        model.getKorisnici().remove(model.getTrenutniKorisnik());
    }

    public void exitAction(ActionEvent actionEvent){
        System.exit(0);
    }

    public void aboutAction(ActionEvent actionEvent){
        Parent root;
        try{
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            root = FXMLLoader.load(getClass().getResource("/fxml/about.fxml"), bundle);
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle(bundle.getString("_about"));
            secondaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            secondaryStage.setResizable(false);
            secondaryStage.getIcons().add(new Image("/pictures/question.png"));
            secondaryStage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveAction(ActionEvent actionEvent){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Unesite naziv datoteke");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("TXT file", "*.txt")
        );
        File file = fileChooser.showOpenDialog(fldIme.getScene().getWindow());
        if(file != null){
            model.zapisiDatoteku(file);
        }
    }

    public void printReportAction (ActionEvent actionEvent) {
        try {
            new PrintReport().showReport(model.getConn());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void promijeniBsAction (ActionEvent actionEvent) {
        Locale.setDefault(new Locale("bs", "BA"));
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        reloadScene();
    }

    public void promijeniEnAction (ActionEvent actionEvent) {
        Locale.setDefault(new Locale("en_US", "US"));
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        reloadScene();
    }

    public void imgKorisnikAction (ActionEvent actionEvent){
            Parent root;
            try{
                ResourceBundle bundle = ResourceBundle.getBundle("Translation");
                GiphyController ctrl = new GiphyController(model);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/giphy.fxml"), bundle);
                loader.setController(ctrl);
                root = loader.load();
                Stage secondaryStage = new Stage();
                secondaryStage.setTitle(bundle.getString("pretraga"));
                secondaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                //secondaryStage.setMinHeight(250);
                //secondaryStage.setMinWidth(640);
                secondaryStage.show();
                if(model.getTrenutniKorisnik() != null) {
                    secondaryStage.setOnHiding(event -> {
                        String imageUrl = model.getTrenutniKorisnik().getSlika(); //razlika u putanji
                        if (imageUrl.equals("resources/pictures/face-smile.png")) {
                            File file = new File(imageUrl);
                            Image image = new Image(file.toURI().toString());
                            btnImage.setImage(image);
                        } else {
                            Image image = new Image(imageUrl);
                            btnImage.setImage(image);
                        }
                    });
                }
            }catch(IOException e){
                e.printStackTrace();
            }
    }

    private void reloadScene() {
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Scene scene = listKorisnici.getScene();
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/korisnici.fxml" ), bundle);
        loader.setController(this);
        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
