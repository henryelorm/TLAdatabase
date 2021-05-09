/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tladatabase.Controllers.CreateClassController;
import tladatabase.Controllers.MainPageController;
import tladatabase.MainPage;
import tladatabase.Entity.Class;

/**
 *
 * @author Elorm
 */
public class NewClass {

    public boolean newClasses(Class classes, Parent root, FXMLLoader loader) {

        root.setStyle("-fx-background-color:transparent;");
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNIFIED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(MainPage.stage);
        stage.setResizable(false);
        stage.setTitle("Create New Class");

        CreateClassController ccc = loader.getController();
        ccc.setDialogStage(stage);
        ccc.setClasses(classes);

        stage.showAndWait();

        return ccc.isCreated();

    }

}
