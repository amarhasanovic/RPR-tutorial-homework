package ba.unsa.etf.rpr.t7;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class KorisnikController {
    public Button krajBtn;
    public TextField ime, prezime, email, korisnickoIme;
    public PasswordField lozinka;
    public ListView<Korisnik> listView;
    public KorisniciModel model;

    public KorisnikController(KorisniciModel model) {
        this.model = model;
        model.napuni();
    }

    @FXML
    public void initialize(){
        listView.setItems(model.getKorisnici());
        ime.textProperty().addListener(
                ((observableValue, s, t1) -> listView.refresh())
        );
        prezime.textProperty().addListener(((observableValue, s, t1) -> listView.refresh()));
        model.trenutniKorisnikProperty().addListener(
                (obs, oldKorisnik, newKorisnik) -> {
                    if(oldKorisnik != null) {
                        ime.textProperty().unbindBidirectional(oldKorisnik.imeProperty());
                        prezime.textProperty().unbindBidirectional(oldKorisnik.prezimeProperty());
                        email.textProperty().unbindBidirectional(oldKorisnik.emailProperty());
                        korisnickoIme.textProperty().unbindBidirectional(oldKorisnik.korisnickoImeProperty());
                        lozinka.textProperty().unbindBidirectional(oldKorisnik.lozinkaProperty());
                    }
                    if(newKorisnik == null){
                        ime.setText("");
                        prezime.setText("");
                        email.setText("");
                        korisnickoIme.setText("");
                        lozinka.setText("");
                    }else{
                        ime.textProperty().bindBidirectional(newKorisnik.imeProperty());
                        prezime.textProperty().bindBidirectional(newKorisnik.prezimeProperty());
                        email.textProperty().bindBidirectional(newKorisnik.emailProperty());
                        korisnickoIme.textProperty().bindBidirectional(newKorisnik.korisnickoImeProperty());
                        lozinka.textProperty().bindBidirectional(newKorisnik.lozinkaProperty());
                    }
                }

        );
    }
    public void closeBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) krajBtn.getScene().getWindow();
        stage.close();
    }

    public void promjenaKorisnika(MouseEvent mouseEvent) {
        model.setTrenutniKorisnik(listView.getSelectionModel().getSelectedItem());
    }

    public void dodajAction(ActionEvent actionEvent) {
        Korisnik korisnik = new Korisnik();
        model.addKorisnika(korisnik);
        model.setTrenutniKorisnik(korisnik);
    }
}
