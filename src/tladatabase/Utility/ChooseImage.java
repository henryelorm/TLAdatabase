/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import static java.awt.SystemColor.desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tladatabase.MainPage;

/**
 *
 * @author Elorm
 */
public class ChooseImage {
    
   public static String uriImage;
   public static Image getimage = null;
    private static void configFileChooser(final FileChooser fileChooser) {

        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

    }

    public static void getImageFile(ImageView imageview) {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        FileChooser fileChooser = new FileChooser();
        ChooseImage.configFileChooser(fileChooser);
        primaryStage.initOwner(MainPage.stage);
        File file = fileChooser.showOpenDialog(primaryStage);

        if (file != null) {

            Image image = new Image(file.toURI().toString());
            
            getimage = new Image(file.toURI().toString());
           // System.out.println( file.toURI().toString());
            imageview.setImage(getimage);
        } else {
            //JOptionPane.showMessageDialog(null, "Image Load Error: \n Error 122");
        }
    }

}
