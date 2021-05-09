/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tladatabase.Utility.Paths;

/**
 *
 * @author Elorm
 */
public class MainPage extends Application {
    
    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(MainPage.class.getResource(Paths.mainPage));

        root.setStyle("-fx-background-color:transparent;");
        MainPage.stage = stage;
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNIFIED);
        stage.setTitle("The Lord's Academy Database");
        stage.getIcons().add(new Image(Paths.skulLogo_png));
        stage.show();

    }

}
