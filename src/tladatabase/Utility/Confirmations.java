/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;


import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import tladatabase.MainPage;
/**
 *
 * @author Elorm
 */
public class Confirmations {

    public static Alert deleteConfirmation(String header, String context) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(MainPage.stage);
        alert.setTitle("Confirmation");
        alert.setHeaderText(header);
        alert.setContentText(context);

        return alert;

    }

    public static TextInputDialog editClassConfirmation() {

        TextInputDialog tid = new TextInputDialog();
        tid.initOwner(MainPage.stage);
        tid.setTitle("New Class Name");
        tid.setContentText("Pleae input new class name: ");
        tid.setHeaderText(null);

        return tid;

    }
    
    public static TextInputDialog passWordRemovalConfirmation() {

        TextInputDialog tid = new TextInputDialog();
        tid.initOwner(MainPage.stage);
        tid.setTitle("Remove Password");
        tid.setContentText("Pleae input current Password:");
        tid.setHeaderText(null);

        return tid;

    }


    public static TextInputDialog simNumberChange() {

        TextInputDialog tid = new TextInputDialog();
        tid.initOwner(MainPage.stage);
        tid.setTitle("Input Sim Number");
        tid.setContentText("Pleae input Sim Number: ");
        tid.setHeaderText(null);

        return tid;

    }

    public static Alert studentPro_Demo_Confirmation(String header, String context) {
        OptionStage ops = new OptionStage();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        // alert.initOwner(ops.primaryStage);
        alert.setTitle("Confirmation");
        alert.setHeaderText(header);
        alert.setContentText(context);

        return alert;

    }
}
