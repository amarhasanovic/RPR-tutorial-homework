package ba.unsa.etf.rpr;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class NoviController {
    public Button okBtn;
    public TextField fldIme;
    public ProgressBar progressBar;
    public ListView lvStudenti;

    @FXML
    public void initialize(){
        fldIme.textProperty().addListener(
                ((observableValue, s, t1) -> {
                    progressBar.progressProperty().bind(new SimpleDoubleProperty(fldIme.getLength()/10.));
                    if(fldIme.getLength()<10){
                        progressBar.getStyleClass().removeAll("zeleniProgress");
                        progressBar.getStyleClass().add("crveniProgress");
                    }else{
                        progressBar.getStyleClass().removeAll("crveniProgress");
                        progressBar.getStyleClass().add("zeleniProgress");
                    }
                })
        );
    }

    public void cancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void okAction(ActionEvent actionEvent) {
        if(fldIme.getText().length()>=10){
            ObservableList<String> studenti = FXCollections.observableArrayList(lvStudenti.getItems());
            studenti.add(fldIme.getText());
            lvStudenti.setItems(studenti);
            lvStudenti.refresh();
            ((Stage)okBtn.getScene().getWindow()).close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Neispravno ime");
            alert.setHeaderText("Neispravno ime studenta");
            alert.setContentText("Ime studenta treba biti najmanje 10 karaktera dugačko");
            alert.showAndWait();
        }
    }

    public void setLvStudenti(ListView lvStudenti){
        this.lvStudenti = lvStudenti;
    }
}
