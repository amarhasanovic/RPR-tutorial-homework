package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import org.assertj.core.internal.bytebuddy.asm.Advice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class GlavnaController {
    public TableView tableViewGradovi;
    public TableColumn colGradId;
    public TableColumn colGradNaziv;
    public TableColumn colGradStanovnika;
    public TableColumn colGradDrzava;
    public TableColumn colPostanskiBroj;
    private GeografijaDAO dao = GeografijaDAO.getInstance();

    public void initialize(){
        ObservableList<Grad> gradovi = FXCollections.observableArrayList(dao.gradovi());
        colGradId.setCellValueFactory(new PropertyValueFactory<Grad, Integer>("id"));
        colGradNaziv.setCellValueFactory(new PropertyValueFactory<Grad, String>("naziv"));
        colGradStanovnika.setCellValueFactory(new PropertyValueFactory<Grad, Integer>("brojStanovnika"));
        colGradDrzava.setCellValueFactory(new PropertyValueFactory<Grad,String>("drzava"));
        colPostanskiBroj.setCellValueFactory(new PropertyValueFactory<Grad, String>("postanskiBroj"));
        tableViewGradovi.setItems(gradovi);
    }

    public void dodajGradAction(ActionEvent actionEvent) {
        GradController ctrl = new GradController();
        ctrl.setDrzave(dao.drzave());
        Parent root;
        try{
            Locale defaultLocale = new Locale("bs");
            ResourceBundle bundle = ResourceBundle.getBundle("Translation", defaultLocale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"), bundle);
            loader.setController(ctrl);
            root = loader.load();
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle(bundle.getString("grad"));
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
            Locale defaultLocale = new Locale("bs");
            ResourceBundle bundle = ResourceBundle.getBundle("Translation", defaultLocale);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/drzava.fxml"), bundle);
            loader.setController(ctrl);
            root = loader.load();
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle(bundle.getString("drzava"));
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
//        new Thread(() -> {
//            Platform.runLater(() -> {
            GradController ctrl = new GradController((Grad) tableViewGradovi.getSelectionModel().getSelectedItem(), dao.drzave());
            Parent root;
            try{
                Locale defaultLocale = new Locale("bs");
                ResourceBundle bundle = ResourceBundle.getBundle("Translation", defaultLocale);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"), bundle);
                loader.setController(ctrl);
                root = loader.load();
                Stage secondaryStage = new Stage();
                secondaryStage.setTitle(bundle.getString("grad"));
                secondaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                secondaryStage.setResizable(false);
                secondaryStage.show();
                secondaryStage.setOnHiding(windowEvent -> {
                    if(ctrl.getIzmijenjeniGrad() != null) {
                        dao.izmijeniGrad(ctrl.getIzmijenjeniGrad());
                        this.initialize();
                    }
                });
            }catch(IOException e){
                e.printStackTrace();
            }
//            });
//        }).start();
    }

    public void obrisiGradAction(ActionEvent actionEvent) {
        if(tableViewGradovi.getSelectionModel().getSelectedItem() != null){
            Locale defaultLocale = new Locale("bs");
            ResourceBundle bundle = ResourceBundle.getBundle("Translation", defaultLocale);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, bundle.getString("obrisiGrad") + " " + tableViewGradovi.getSelectionModel().getSelectedItem() + " ?", ButtonType.OK, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                dao.obrisiGrad(((Grad) tableViewGradovi.getSelectionModel().getSelectedItem()).getNaziv());
                this.initialize();
            }
        }
    }

    public void stampaAction(ActionEvent actionEvent){
        try {
            new GradoviReport().showReport(dao.getConn());
        } catch (JRException e1) {
            e1.printStackTrace();
        }
    }

    public void jezikAction(ActionEvent actionEvent){
        ArrayList<String> jezici = new ArrayList<>();
        jezici.add("Bosanski");
        jezici.add("English");
        jezici.add("Deutsch");
        jezici.add("Français");
        ChoiceDialog dialog = new ChoiceDialog(jezici.get(0), jezici);
        Locale defaultLocale = new Locale("bs");
        ResourceBundle bundle = ResourceBundle.getBundle("Translation", defaultLocale);

        dialog.setTitle(bundle.getString("jezik"));
        dialog.setHeaderText(null);
        dialog.setContentText(bundle.getString("odaberiteJezik"));

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.get().equals(jezici.get(0))) {
                Locale.setDefault(new Locale("bs", "BA"));
                reloadScene();
            } else if (result.get().equals(jezici.get(1))) {
                Locale.setDefault(new Locale("en_US", "US"));
                reloadScene();
            } else if (result.get().equals(jezici.get(2))) {
                Locale.setDefault(new Locale("de", "DE"));
                reloadScene();
            } else if (result.get().equals(jezici.get(3))) {
                Locale.setDefault(new Locale("fr", "FR"));
                reloadScene();
            }
        }
    }

    private void reloadScene() {
        Locale defaultLocale = new Locale("bs");
        ResourceBundle bundle = ResourceBundle.getBundle("Translation", defaultLocale);
        Scene scene = tableViewGradovi.getScene();
        FXMLLoader loader = new FXMLLoader( getClass().getResource("/fxml/glavna.fxml" ), bundle);
        loader.setController(this);
        try {
            scene.setRoot(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
