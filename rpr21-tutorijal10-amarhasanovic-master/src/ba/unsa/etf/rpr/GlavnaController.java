package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class GlavnaController {
    public TableView tableViewGradovi;
    public TableColumn colGradId;
    public TableColumn colGradNaziv;
    public TableColumn colGradStanovnika;
    public TableColumn colGradDrzava;
    private GeografijaDAO dao = GeografijaDAO.getInstance();

    public void initialize(){
        ObservableList<Grad> gradovi = FXCollections.observableArrayList(dao.gradovi());
        colGradId.setCellValueFactory(new PropertyValueFactory<Grad, Integer>("id"));
        colGradNaziv.setCellValueFactory(new PropertyValueFactory<Grad, String>("naziv"));
        colGradStanovnika.setCellValueFactory(new PropertyValueFactory<Grad, Integer>("brojStanovnika"));
        colGradDrzava.setCellValueFactory(new PropertyValueFactory<Grad,String>("drzava"));
        tableViewGradovi.setItems(gradovi);
    }

    public void dodajGradAction(ActionEvent actionEvent) {
        GradController ctrl = new GradController();
        ctrl.setDrzave(dao.drzave());
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));
            loader.setController(ctrl);
            root = loader.load();
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Grad");
            secondaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            secondaryStage.setResizable(false);
            secondaryStage.show();
            secondaryStage.setOnHiding(windowEvent -> {
                if(ctrl.getGrad() != null) {
                    dao.dodajGrad(ctrl.getGrad());
                    this.initialize();
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void dodajDrzavuAction(ActionEvent actionEvent) {
        DrzavaController ctrl = new DrzavaController(null, dao.gradovi());
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"));
            loader.setController(ctrl);
            root = loader.load();
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Drzava");
            secondaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            secondaryStage.setResizable(false);
            secondaryStage.show();
            secondaryStage.setOnHiding(windowEvent -> {
                if(ctrl.getDrzava() != null) {
                    dao.dodajDrzavu(ctrl.getDrzava());
                    this.initialize();
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void izmijeniGradAction(ActionEvent actionEvent) {
        GradController ctrl = new GradController((Grad) tableViewGradovi.getSelectionModel().getSelectedItem(), dao.drzave());
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));
            loader.setController(ctrl);
            root = loader.load();
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Grad");
            secondaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            secondaryStage.setResizable(false);
            secondaryStage.show();
            secondaryStage.setOnHiding(windowEvent -> {
                if(ctrl.getGrad() != null) {
                    dao.izmijeniGrad(ctrl.getIzmijenjeniGrad());
                    this.initialize();
                }
            });
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void obrisiGradAction(ActionEvent actionEvent) {
        if(tableViewGradovi.getSelectionModel().getSelectedItem() != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Obriši grad " + tableViewGradovi.getSelectionModel().getSelectedItem() + " ?", ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                dao.obrisiGrad(((Grad) tableViewGradovi.getSelectionModel().getSelectedItem()).getNaziv());
                this.initialize();
            }
        }
    }

}
