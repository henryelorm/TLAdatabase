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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tladatabase.MainPage;

/**
 *
 * @author Elorm
 */
public class OptionStage {

    public Stage primaryStage;

    public void optionstage(FXMLLoader fxmlLoader) throws IOException {

        fxmlLoader.setLocation(getClass().getResource(Paths.options));
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(MainPage.stage);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.showAndWait();

    }

}
