/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javax.persistence.EntityManager;
import tladatabase.Entity.Student;
import tladatabase.EntityManager.StudentManager;

/**
 *
 * @author Elorm
 */
public class SearchEngine {

    private static ObservableList<Student> getAllStudents = FXCollections.observableArrayList();
    public static ObservableList<String> getasString = FXCollections.observableArrayList();

    public static void search(EntityManager em, String nameSearch, ListView<String> studentList) {

        StudentManager stm = new StudentManager();
        getAllStudents.setAll(stm.findAllStudents(em));
        getasString.clear();

        for (int i = 0; i < getAllStudents.size(); i++) {

            try {
                if (nameSearch.equalsIgnoreCase(getAllStudents.get(i).getLastName().substring(0, nameSearch.length()))) {

                    getasString.add(getAllStudents.get(i).getLastName() + " " + getAllStudents.get(i).getFirstName() + " " + getAllStudents.get(i).getOtherName());

                    studentList.setItems(getasString);
                } else {
                    studentList.setItems(getasString);
                   // System.out.println("no name found  " + getAllStudents.get(i).getLastName().substring(0, nameSearch.length()));
                }
            } catch (StringIndexOutOfBoundsException error) {
                getAllStudents.remove(i);
            }

        }

    }
}
