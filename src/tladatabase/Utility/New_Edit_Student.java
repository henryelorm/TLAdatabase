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
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tladatabase.Controllers.MainPageController;
import tladatabase.Controllers.NewStudentController;
import tladatabase.Entity.Student;
import tladatabase.MainPage;

/**
 *
 * @author Elorm
 */
public class New_Edit_Student {
    
    public boolean newStudents(Student student) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainPageController.class.getResource(Paths.newStudent));
            Parent root = (Parent) loader.load();
            
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.setTitle("New Student's info");
            primaryStage.initOwner(MainPage.stage);
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.UTILITY);
            
            NewStudentController controller = loader.getController();
            controller.setDialogStage(primaryStage);
            controller.setStudents(student);
            
            primaryStage.showAndWait();
            
            return controller.isSavedClicked();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    
    public boolean EditStudents(Student student) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainPageController.class.getResource(Paths.newStudent));
            Parent root = (Parent) loader.load();
            
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Edit Student's info");
            primaryStage.initModality(Modality.WINDOW_MODAL);
            primaryStage.initOwner(MainPage.stage);
            primaryStage.setResizable(false);
            primaryStage.initStyle(StageStyle.UTILITY);
            
            NewStudentController controller = loader.getController();
            controller.setDialogStage(primaryStage);
            controller.setStudents(student);
            
            primaryStage.showAndWait();
            
            return controller.isSavedClicked();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    
    
    
    
    
}
