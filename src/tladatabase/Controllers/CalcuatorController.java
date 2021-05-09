/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Elorm
 */
public class CalcuatorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private double currentNumber;
    private String currentOperation;
    private String oldText;
    private String digit;

    @FXML
    private TextField field;

    @FXML
    private Button twoButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        field.setAlignment(Pos.CENTER_RIGHT);

    }

    @FXML
    void clearButtonInit(ActionEvent event) {

        if (!field.getText().isEmpty()) {

            field.setText("");
        }
    }

    @FXML
    void handleDigitAction(ActionEvent event) {
        digit = ((Button) event.getSource()).getText();
        oldText = field.getText();

        String newText = oldText + digit;
        field.setText(newText);

    }

    @FXML
    void equalButtonInit(ActionEvent event) {
        String newText = field.getText();
        if (!newText.isEmpty() && currentOperation != null) {
            double newNumber = Double.valueOf(newText);

            switch (currentOperation) {
                case "+":
                    currentNumber = currentNumber + newNumber;
                    break;
                case "-":
                    currentNumber = currentNumber - newNumber;
                    break;
                case "*":
                    currentNumber = currentNumber * newNumber;
                    break;
                case "/":
                    currentNumber = currentNumber / newNumber;
                    break;

                default:
                    break;
            }

            field.setText("" + currentNumber);
        }
    }

    @FXML
    void handleOPeration(ActionEvent event) {
        String currentText = field.getText();
        currentNumber = Double.valueOf(currentText);
        field.setText("");
        currentOperation = ((Button) event.getSource()).getText();
    }

}
