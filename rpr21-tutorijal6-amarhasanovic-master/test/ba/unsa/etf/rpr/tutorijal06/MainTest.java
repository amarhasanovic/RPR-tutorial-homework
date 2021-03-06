package ba.unsa.etf.rpr.tutorijal06;

import static org.junit.jupiter.api.Assertions.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.Objects;

@ExtendWith(ApplicationExtension.class)
class MainTest {

    private Label display;

    @Start
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(Main.class.getResource("/fxml/digitron.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void startWithZero (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        assertEquals("0", display.getText());
    }

    @Test
    public void numberOne (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        assertEquals("1", display.getText());
    }

    @Test
    public void number123 (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        robot.clickOn("#btn2");
        robot.clickOn("#btn3");
        assertEquals("123", display.getText());
    }

    @Test
    public void number123Plus456 (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        robot.clickOn("#btn2");
        robot.clickOn("#btn3");
        robot.clickOn("#plusBtn");
        robot.clickOn("#btn4");
        robot.clickOn("#btn5");
        robot.clickOn("#btn6");
        assertEquals("456", display.getText());
    }

    @Test
    public void number123Plus456Equals (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        robot.clickOn("#btn2");
        robot.clickOn("#btn3");
        robot.clickOn("#plusBtn");
        robot.clickOn("#btn4");
        robot.clickOn("#btn5");
        robot.clickOn("#btn6");
        robot.clickOn("#equalsBtn");
        assertEquals("579.0", display.getText());
    }

    @Test
    public void dotBtn (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn8");
        robot.clickOn("#btn8");
        robot.clickOn("#dotBtn");
        robot.clickOn("#btn8");
        robot.clickOn("#btn8");
        robot.clickOn("#plusBtn");
        robot.clickOn("#btn1");
        robot.clickOn("#equalsBtn");
        assertEquals("89.88", display.getText());
    }

    @Test
    public void zeroBtn (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn0");
        robot.clickOn("#btn0");
        assertEquals("0", display.getText());
    }

    @Test
    public void zeroBtn2 (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn0");
        robot.clickOn("#btn0");
        robot.clickOn("#btn1");
        robot.clickOn("#btn0");
        assertEquals("10", display.getText());
    }
    @Test
    public void minusBtn(FxRobot robot){
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        robot.clickOn("#btn5");
        robot.clickOn("#minusBtn");
        robot.clickOn("#btn5");
        robot.clickOn("#equalsBtn");
        assertEquals("10.0", display.getText());
    }

    @Test
    public void divBtn1(FxRobot robot){
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn2");
        robot.clickOn("#btn1");
        robot.clickOn("#divideBtn");
        robot.clickOn("#btn2");
        robot.clickOn("#equalsBtn");
        assertEquals("10.5", display.getText());
    }

    @Test
    public void divBtn2(FxRobot robot){
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn2");
        robot.clickOn("#btn1");
        robot.clickOn("#divideBtn");
        robot.clickOn("#btn0");
        robot.clickOn("#equalsBtn");
        assertEquals("0.0", display.getText());
    }

    @Test
    public void modBtn(FxRobot robot){
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn2");
        robot.clickOn("#btn2");
        robot.clickOn("#modBtn");
        robot.clickOn("#btn5");
        robot.clickOn("#equalsBtn");
        assertEquals("2.0", display.getText());
    }

    @Test
    public void multiplyBtn(FxRobot robot){
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn4");
        robot.clickOn("#multiplyBtn");
        robot.clickOn("#btn2");
        robot.clickOn("#dotBtn");
        robot.clickOn("#btn5");
        robot.clickOn("#equalsBtn");
        assertEquals("10.0", display.getText());
    }
}