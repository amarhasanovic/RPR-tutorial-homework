package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ZadacaController {
    public TextField fldText;
    public ChoiceBox choiceColor;
    public Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    public Button unosBtn;
    public ListView lvStudents;
    public Slider sliderStudents;
    StudentModel model;

    @FXML
    public void initialize(){
        model = new StudentModel();
        model.napuni();
        choiceColor.getItems().add("Default");
        choiceColor.getItems().add("Crvena");
        choiceColor.getItems().add("Zelena");
        choiceColor.getItems().add("Plava");

        fldText.textProperty().addListener(
                ((observableValue, s, t1) -> {
                    if(model.getStudenti().size() > 14)
                        model.getStudenti().remove(model.getStudenti().size()-1);
                    model.getStudenti().add("Student" + fldText.getText());
                    model.getStudentiFiltered().setPredicate(str -> {
                        if(sliderStudents.getValue() == 15)
                            return true;
                        int index = model.getStudenti().indexOf(str);
                        boolean vrijednost = model.getStudenti().indexOf(str) < sliderStudents.getValue();
                        if(model.getStudenti().get(index) == str)
                            return vrijednost;
                        return false;
                    });
                })
        );
        model.getStudentiFiltered().setPredicate(str -> model.getStudenti().indexOf(str) < sliderStudents.getValue());
        sliderStudents.valueProperty().addListener(
                ((observableValue, number, t1) -> {
                    model.getStudentiFiltered().setPredicate(str -> {
                        if(sliderStudents.getValue() >= 15)
                            return true;
                        int index = model.getStudenti().indexOf(str);
                        boolean vrijednost = model.getStudenti().indexOf(str) < sliderStudents.getValue();
                        if(model.getStudenti().get(index) == str)
                            return vrijednost;
                        return false;
                    });
                })
        );
        lvStudents.setItems(model.getStudentiFiltered());
    }
    public void numericPress(ActionEvent actionEvent) {
        String number = ((Button)actionEvent.getSource()).getText();
        if(fldText.getText().equals("0")){
            fldText.setText(number);
        }else
            fldText.setText(fldText.getText()+number);
    }

    public void colorChange(ActionEvent actionEvent) {
        String color = (String) choiceColor.getSelectionModel().getSelectedItem();
        btn0.getStyleClass().removeAll("crveno", "zeleno", "plavo");
        btn1.getStyleClass().removeAll("crveno", "zeleno", "plavo");
        btn2.getStyleClass().removeAll("crveno", "zeleno", "plavo");
        btn3.getStyleClass().removeAll("crveno", "zeleno", "plavo");
        btn4.getStyleClass().removeAll("crveno", "zeleno", "plavo");
        btn5.getStyleClass().removeAll("crveno", "zeleno", "plavo");
        btn6.getStyleClass().removeAll("crveno", "zeleno", "plavo");
        btn7.getStyleClass().removeAll("crveno", "zeleno", "plavo");
        btn8.getStyleClass().removeAll("crveno", "zeleno", "plavo");
        btn9.getStyleClass().removeAll("crveno", "zeleno", "plavo");
        switch (color){
            case "Crvena": {
                btn0.getStyleClass().add("crveno");
                btn1.getStyleClass().add("crveno");
                btn2.getStyleClass().add("crveno");
                btn3.getStyleClass().add("crveno");
                btn4.getStyleClass().add("crveno");
                btn5.getStyleClass().add("crveno");
                btn6.getStyleClass().add("crveno");
                btn7.getStyleClass().add("crveno");
                btn8.getStyleClass().add("crveno");
                btn9.getStyleClass().add("crveno");
                break;
            }
            case "Zelena": {
                btn0.getStyleClass().add("zeleno");
                btn1.getStyleClass().add("zeleno");
                btn2.getStyleClass().add("zeleno");
                btn3.getStyleClass().add("zeleno");
                btn4.getStyleClass().add("zeleno");
                btn5.getStyleClass().add("zeleno");
                btn6.getStyleClass().add("zeleno");
                btn7.getStyleClass().add("zeleno");
                btn8.getStyleClass().add("zeleno");
                btn9.getStyleClass().add("zeleno");
                break;
            }
            case "Plava":  {
                btn0.getStyleClass().add("plavo");
                btn1.getStyleClass().add("plavo");
                btn2.getStyleClass().add("plavo");
                btn3.getStyleClass().add("plavo");
                btn4.getStyleClass().add("plavo");
                btn5.getStyleClass().add("plavo");
                btn6.getStyleClass().add("plavo");
                btn7.getStyleClass().add("plavo");
                btn8.getStyleClass().add("plavo");
                btn9.getStyleClass().add("plavo");
                break;
            }
            default: {
                btn0.setStyle("");
                btn1.setStyle("");
                btn2.setStyle("");
                btn3.setStyle("");
                btn4.setStyle("");
                btn5.setStyle("");
                btn6.setStyle("");
                btn7.setStyle("");
                btn8.setStyle("");
                btn9.setStyle("");
            }
        }
    }

    public void otvoriNovi(ActionEvent actionEvent) {
        Parent root;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/novi.fxml"));
            root = loader.load();
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Unos studenta");
            secondaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            secondaryStage.setResizable(false);
            secondaryStage.show();
            NoviController kontroler = loader.getController();
            kontroler.setLvStudenti(lvStudents);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
