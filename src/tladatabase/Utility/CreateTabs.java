/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import tladatabase.Entity.Student;
import tladatabase.Entity.StudentImage;

/**
 *
 * @author Elorm
 */
public class CreateTabs {

    BugLog log = new BugLog();
    public Tab tab = new Tab();

    //   public Tab[] tabb;
    public Tab createTab(String className, Parent view, int tableSize) {

        tab.setText(className);
        tab.setClosable(true);
        tab.setId(String.valueOf(tableSize));

        tab.setStyle("-fx-font-size: 14px; -fx-alignment: CENTER;"
                + " -fx-text-fill: -fx-text-base-color;");

        //  students.getItems().add(null);
        tab.setContent(view);

        return tab;
    }

    public Tab createTab_TablesElement(TableView<Student> studentsView, Long cId, String className, Parent view, List<Student> findStudents, int i) {

        tab.setText(className);
        tab.setId(String.valueOf(i));
        tab.setClosable(true);

        tab.setStyle("-fx-font-size: 14px; -fx-alignment: CENTER;"
                + " -fx-text-fill: -fx-text-base-color;");
        /*
         findStudents.stream().filter((s) -> (cId.equals(s.getClassId()))).forEach((s) -> {

         studentsView.getItems().add(s);
         });
         */
        
        CustomeImage ci = new CustomeImage();
        for (int x = 0; x < findStudents.size(); ++x) {

            if (findStudents.get(x).getClassId().equals(cId)) {

                studentsView.getItems().addAll(findStudents.get(x));
            }

        }
       // System.err.println(tab.getId());
        tab.setContent(view);

        return tab;
    }
    
    private List<CustomeImage> image(List<StudentImage> images,String studentId){
        
        
        for(int i = 0; i< images.size(); ++i){
            
            if(images.get(i).getId().equals(studentId)){
                
                return null;
            }
            
        }
        return null;
    }

}
