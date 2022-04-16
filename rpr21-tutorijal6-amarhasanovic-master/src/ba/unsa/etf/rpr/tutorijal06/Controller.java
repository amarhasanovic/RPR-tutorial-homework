package ba.unsa.etf.rpr.tutorijal06;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label display;
    private double number1 = 0;
    private String operator = "";
    private boolean start = true;
    private Model model = new Model();
    @FXML
    public void processNumbers(ActionEvent actionEvent) {
        if(start){
            display.setText("0");
            start = false;
        }
        String value = ((Button)actionEvent.getSource()).getText();
        if(display.getText().equals("0") && !value.equals("."))
            display.setText(value);
        else
            display.setText(display.getText()+value);
    }

    @FXML
    public void processOperators(ActionEvent actionEvent) {
        String value = ((Button) actionEvent.getSource()).getText();
        if (!value.equals("=")) {
            if (!operator.isEmpty())
                return;
            operator = value;
            number1 = Double.parseDouble(display.getText());
            display.setText("0");
        }else{
            if(operator.isEmpty())
                return;
            double number2 = Double.parseDouble(display.getText());
            double output = model.calculate(number1, number2, operator);
            display.setText(String.valueOf(output));
            operator = "";
            start = true;
        }
    }
}
