package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class GradController {
    public TextField fieldNaziv;
    public TextField fieldBrojStanovnika;
    public ChoiceBox choiceDrzava;
    private GeografijaDAO dao;
    ArrayList<Drzava> drzave;
    private Grad trenutniGrad = null;
    private Grad dodaniGrad = null;
    private Drzava odabranaDrzava = null;
    private boolean validacija = false;

    @FXML
    public void initialize(){
        choiceDrzava.getItems().addAll(drzave);

        if(trenutniGrad != null){
            fieldNaziv.setText(trenutniGrad.getNaziv());
            fieldBrojStanovnika.setText(String.valueOf(trenutniGrad.getBrojStanovnika()));
            choiceDrzava.setValue(trenutniGrad.getDrzava());
            odabranaDrzava = trenutniGrad.getDrzava();
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
        if(trenutniGrad != null && validacija){
            trenutniGrad.setDrzava(odabranaDrzava);
            trenutniGrad.setNaziv(fieldNaziv.getText());
            trenutniGrad.setBrojStanovnika(Integer.parseInt(fieldBrojStanovnika.getText()));
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
                validacija = true;
            } else {
                fieldNaziv.getStyleClass().removeAll("poljeIspravno");
                fieldNaziv.getStyleClass().add("poljeNijeIspravno");
                validacija = false;
            }
            int brojStanovnika = 0;
            try{
                brojStanovnika = Integer.parseInt(fieldBrojStanovnika.getText());
            } catch (NumberFormatException e){
                fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
                fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
                validacija = false;
            }
            if(!fieldBrojStanovnika.getText().trim().isEmpty() && brojStanovnika > 0 ){
                fieldBrojStanovnika.getStyleClass().removeAll("poljeNijeIspravno");
                fieldBrojStanovnika.getStyleClass().add("poljeIspravno");
                validacija = true;
            }else{
                fieldBrojStanovnika.getStyleClass().removeAll("poljeIspravno");
                fieldBrojStanovnika.getStyleClass().add("poljeNijeIspravno");
                validacija = false;
            }
        if(validacija){
            dodaniGrad = new Grad(0, fieldNaziv.getText(), Integer.parseInt(fieldBrojStanovnika.getText()), odabranaDrzava);
            fieldNaziv.getScene().getWindow().hide();
        }
    }

    public void cancelAction (ActionEvent actionEvent){
        dodaniGrad = null;
        odabranaDrzava = null;
        trenutniGrad = null;
        fieldNaziv.getScene().getWindow().hide();
    }

}
