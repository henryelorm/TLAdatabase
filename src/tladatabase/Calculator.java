/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tladatabase.Utility.Paths;

/**
 *
 * @author Elorm
 */
public class Calculator {

    public void Calculate(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(Calculator.class.getResource(Paths.calculator));
        Parent root = loader.load();

        root.setStyle("-fx-background-color:transparent;");
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setTitle("Calculator");
        primaryStage.setResizable(false);
        primaryStage.initModality(Modality.WINDOW_MODAL);
        primaryStage.initOwner(MainPage.stage);
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
