/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tladatabase.Controllers.ClassInfoController;
import tladatabase.MainPage;

/**
 *
 * @author Elorm
 */
public class ClassInfoStage {

    public ClassInfoStage(FXMLLoader loader, Stage primaryStage) throws IOException {

        loader.setLocation(ClassInfoController.class.getResource(Paths.classInfo));
        Parent root = (Parent) loader.load();

        root.setStyle("-fx-background-color:transparent;");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(MainPage.stage);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNIFIED);

        primaryStage.show();
    }

    public static void setTeachersInfo(TextField nameField, TextField phoneNumberField, String name, String phone) {

        if (((!"".equals(name) || name != null) && (!"".equals(phone) || phone != null)) == true) {
            nameField.setText(name);
            phoneNumberField.setText(phone);
        }

    }
}
