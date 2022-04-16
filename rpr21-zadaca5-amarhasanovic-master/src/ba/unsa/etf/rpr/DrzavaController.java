package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class DrzavaController {
    public TextField fieldNaziv;
    public ChoiceBox choiceGrad;
    private GeografijaDAO dao;
    private ArrayList<Grad> gradovi;
    private Drzava novaDrzava = null;
    private boolean validacija = false;
    private Grad odabraniGrad = null;

    @FXML
    public void initialize(){
        choiceGrad.getItems().addAll(gradovi);
        choiceGrad.getSelectionModel().selectedItemProperty().addListener((obs, oldGrad, newGrad) -> {
            if(newGrad != null)
                odabraniGrad = (Grad) newGrad;
        });
    }
    public DrzavaController(){

    }
    public DrzavaController(GeografijaDAO dao, ArrayList<Grad> gradovi) {
        this.dao = dao;
        this.gradovi = gradovi;
    }

    public void setGrad(ArrayList<Grad> gradovi){
        this.gradovi = gradovi;
    }

    public Drzava getDrzava(){
        return novaDrzava;
    }

    public void okAction (ActionEvent actionEvent){
        if (!fieldNaziv.getText().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
            validacija = true;
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            validacija = false;
        }
        if(validacija){
            novaDrzava = new Drzava(0, fieldNaziv.getText());
            novaDrzava.setGlavniGrad(odabraniGrad);
            fieldNaziv.getScene().getWindow().hide();
        }
    }

    public void cancelAction (ActionEvent actionEvent){
        novaDrzava = null;
        fieldNaziv.getScene().getWindow().hide();
    }
}
