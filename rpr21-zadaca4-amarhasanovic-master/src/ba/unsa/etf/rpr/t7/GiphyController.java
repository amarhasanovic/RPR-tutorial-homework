package ba.unsa.etf.rpr.t7;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchFeed;
import at.mukprojects.giphy4j.exception.GiphyException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class GiphyController {
    public Button btnCancel;
    public Button btnSearch;
    public Button btnOk;
    public TextField fldSearch;
    public Button btnSelektovanaSlika;
    public TilePane tilePane;
    public ScrollPane scrollPane;
    private String odabranaSlika;
    private Image slika;

    //APIKEY

    KorisniciModel model = KorisniciModel.getInstance();

    GiphyController(KorisniciModel model){
        this.model = model;
        this.odabranaSlika = "resources/pictures/face-smile.png";
    }

    public void cancelAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public ImageView displayLoading(){
        Image image = new Image(getClass().getResource("/pictures/loading.gif").toString());
        ImageView view1 = new ImageView(image);
        view1.setFitHeight(128);
        view1.setFitWidth(128);
        view1.setPreserveRatio(false);
        return view1;
    }

    public void searchAction(ActionEvent actionEvent){
        ImageView image = displayLoading();
        tilePane.getChildren().clear();
        new Thread(() -> {
            Giphy giphy = new Giphy(APIKEY);
            Platform.runLater(() -> tilePane.getChildren().add(image));
            AtomicReference<SearchFeed> feed = new AtomicReference<>(new SearchFeed());
            try {
                feed.set(giphy.search(fldSearch.getText(), 25, 0));
            } catch (GiphyException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < feed.get().getDataList().size(); i++) {
                Platform.runLater(() -> {
                    if(!tilePane.getChildren().contains(image))
                        tilePane.getChildren().add(image);
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String imageURL = "https://i.giphy.com/media/" + feed.get().getDataList().get(i).getId() + "/giphy_s.gif";
                Button button = new Button();
                button.setMaxHeight(135);
                button.setMaxWidth(140);
                button.setMinHeight(135);
                button.setMaxWidth(140);
                ImageView view = new ImageView(new Image(imageURL, true));
                view.setFitHeight(128);
                view.setFitWidth(128);
                view.setPreserveRatio(false);
                button.setGraphic(view);
                button.setOnAction(actionEvent1 -> {
                    btnSelektovanaSlika = button;
                    odabranaSlika = imageURL;
                });
                Platform.runLater(() -> {
                    tilePane.getChildren().remove(image);
                    tilePane.getChildren().add(button);
                });
            }
        }).start();
    }

    public void okAction(ActionEvent actionEvent){
        if(btnSelektovanaSlika == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            ResourceBundle bundle = ResourceBundle.getBundle("Translation");
            alert.setTitle(bundle.getString("error")+"!");
            alert.setHeaderText(bundle.getString("error_text"));
            alert.setContentText(bundle.getString("error_text2"));
            alert.showAndWait();
        } else {
            if(model.getTrenutniKorisnik() != null) {
                model.getTrenutniKorisnik().setSlika(odabranaSlika);
                slika = new Image(odabranaSlika);
                model.promijeniKorisnika();
            }
            Stage stage = (Stage) btnOk.getScene().getWindow();
            stage.close();
        }
    }

    public Image getSlika(){
        return slika;
    }
}
