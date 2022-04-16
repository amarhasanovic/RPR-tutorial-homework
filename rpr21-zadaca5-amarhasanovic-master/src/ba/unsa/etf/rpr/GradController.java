package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class GradController {
    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;
    public TextField fldPostanskiBroj;
    public ChoiceBox choiceDrzava;
    public Button promijeniBtn;
    public ImageView slikaIv;
    private GeografijaDAO dao;
    ArrayList<Drzava> drzave;
    private Grad trenutniGrad = null;
    private Grad dodaniGrad = null;
    private Drzava odabranaDrzava = null;

    boolean validacija1 = false;
    boolean validacija2 = false;
    boolean validacija3 = true;

    @FXML
    public void initialize(){
        choiceDrzava.getItems().addAll(drzave);
        if(trenutniGrad != null){
            fieldNaziv.setText(trenutniGrad.getNaziv());
            fieldBrojStanovnika.setText(String.valueOf(trenutniGrad.getBrojStanovnika()));
            choiceDrzava.setValue(trenutniGrad.getDrzava());
            odabranaDrzava = trenutniGrad.getDrzava();
            slikaIv.setImage(new Image(trenutniGrad.getSlika()));
            fldPostanskiBroj.setText(String.valueOf(trenutniGrad.postanskiBroj));

        }else{
            File file = new File("C:/Users/Korisnik/IdeaProjects/rpr21-zadaca5-amarhasanovic/resources/pictures/architecture-and-city.png");
            slikaIv.setImage(new Image(file.toURI().toString()));
        }

        choiceDrzava.setOnAction((event) ->{
            odabranaDrzava = (Drzava) choiceDrzava.getSelectionModel().getSelectedItem();
        });
    }

    public GradController(){

    }
    public GradController(Grad grad, ArrayList<Drzava> drzave) {
        this.trenutniGrad = grad;
        this.drzave = drzave;
    }

    public Grad getGrad() {
        return dodaniGrad;
    }
    public Grad getIzmijenjeniGrad(){
        if(trenutniGrad != null && validacija1 && validacija2){
            trenutniGrad.setDrzava(odabranaDrzava);
            trenutniGrad.setNaziv(fieldNaziv.getText());
            trenutniGrad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText()));
            if(validacija3)
                trenutniGrad.setPostanskiBroj(Integer.parseInt(fldPostanskiBroj.getText()));
        }
        return trenutniGrad;
    }

    public void setDrzave(ArrayList<Drzava> drzave){
        this.drzave = drzave;
    }

    public void okAction (ActionEvent actionEvent){
            if (!fieldNaziv.getText().trim().isEmpty()) {
                fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
                fieldNaziv.getStyleClass().add("poljeIspravno");
                validacija1 = true;
            } else {
                fieldNaziv.getStyleClass().removeAll("poljeIspravno");
                fieldNaziv.getStyleClass().add("poljeNijeIspravno");
                validacija1 = false;
            }
            int brojStanovnika = 0;
            try{
                brojStanovnika = Integer.parseInt(fieldBrojStanovnika.getText());
                if(!fieldBrojStanovnika.getText().trim().isEmpty() && brojStanovnika > 0 ){
                    fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
                    fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
                    validacija2 = true;
                }else{
                    fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
                    fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
                    validacija2 = false;
                }
            } catch (NumberFormatException e){
                fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
                fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
                validacija2 = false;
            }

            //Validacija postanskog broja
        // NAPOMENA: validacija putem servisa, potrebno skloniti komentare
        new Thread(() -> {
            int postanskiBroj = 0;
            try {
                postanskiBroj = Integer.parseInt(fldPostanskiBroj.getText());
                validacija3 = true;
            }catch (NumberFormatException e){
                validacija3 = false;
            }
//            String adr = "http://c9.etf.unsa.ba/proba/postanskiBroj.php?postanskiBroj="  + fldPostanskiBroj.getText();
//            URL url = null;
//            try {
//                url = new URL(adr);
//                BufferedReader ulaz = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
//                String postanskiBrojIs = ulaz.readLine();
//                ulaz.close();
//                if(postanskiBrojIs.equals("OK")) {
//                    validacija3 = true;
//                }else{
//                    validacija3 = false;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            if(validacija3){
                fldPostanskiBroj.getStyleClass().removeAll("poljeNijeIspravno");
                fldPostanskiBroj.getStyleClass().add("poljeIspravno");
            }else{
                fldPostanskiBroj.getStyleClass().removeAll("poljeIspravno");
                fldPostanskiBroj.getStyleClass().add("poljeNijeIspravno");
            }

            if(validacija1 && validacija2 /*&& validacija3 - potrebno otkomentarisati ako zelimo validaciju postanskog broja*/){
                dodaniGrad = new Grad(0, fieldNaziv.getText(), Integer.parseInt(fieldBrojStanovnika.getText()), odabranaDrzava);
                dodaniGrad.setPostanskiBroj(postanskiBroj);
            }
            if(validacija1 && validacija2 /*&& validacija3 - potrebno otkomentarisati ako zelimo validaciju postanskog broja*/){
                Platform.runLater(() -> fieldNaziv.getScene().getWindow().hide());
            }
        }).start();
    }

    public void cancelAction (ActionEvent actionEvent){
        dodaniGrad = null;
        odabranaDrzava = null;
        trenutniGrad = null;
        fieldNaziv.getScene().getWindow().hide();
    }

    public void promijeniAction (ActionEvent actionEvent){
        PretragaController pretragaController = new PretragaController();
        Parent root;
        try{
            Locale defaultLocale = new Locale("bs");
            ResourceBundle bundle = ResourceBundle.getBundle("Translation", defaultLocale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pretraga.fxml"), bundle);
            loader.setController(pretragaController);
            root = loader.load();
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle(bundle.getString("pretraga"));
            secondaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            secondaryStage.setMinWidth(400);
            secondaryStage.setMinHeight(200);
            secondaryStage.show();
            secondaryStage.setOnHiding(windowEvent -> {
                if(pretragaController.getSlikaPath() != null) {
                    if(trenutniGrad != null) {
                        trenutniGrad.setSlika(pretragaController.getSlikaPath());
                    }else{
                        dodaniGrad.setSlika(pretragaController.getSlikaPath());
                    }
                    this.initialize();
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
