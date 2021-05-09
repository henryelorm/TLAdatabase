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

public class TLAdatabase extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(TLAdatabase.class.getResource(Paths.passPage));

       
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(TLAdatabase.class.getResource("Css/pageLayout.css").toExternalForm());
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setTitle("The Lord's Academy Database");
        stage.getIcons().add(new Image(Paths.skulLogo_png));
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);
        stage.show();

    }

}
