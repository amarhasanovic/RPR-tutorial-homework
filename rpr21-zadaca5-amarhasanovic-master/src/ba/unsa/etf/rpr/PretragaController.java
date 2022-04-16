package ba.unsa.etf.rpr;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.File;

public class PretragaController {
    public ListView lvPaths;
    public TextField search;
    private String slikaPath;

    public void initialize(){
        lvPaths.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldPath, newPath) -> {
            if(newPath != null)
                slikaPath = (String) lvPaths.getSelectionModel().getSelectedItem();
        }));
    }

    private void walk(String path) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) return;
        for (File f : list) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath());
            }
            if(f.getAbsoluteFile().getAbsolutePath().contains(search.getText())){
                Platform.runLater(() -> lvPaths.getItems().add(f.getAbsoluteFile().getAbsolutePath()));
            }
        }
    }

    public void traziAction (ActionEvent actionEvent){
        lvPaths.getItems().clear();
        new Thread(() -> {
            walk("C:/");
        }).start();
    }

    public String getSlikaPath(){
        return slikaPath;
    }
}
