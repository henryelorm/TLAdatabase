/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.persistence.EntityManager;
import tladatabase.EntityManager.ClassManager;
import tladatabase.Entity.Class;

/**
 *
 * @author Elorm
 */
public class GetClassList {

    static ObservableList<Class> clsfees = FXCollections.observableArrayList();

    public static ObservableList<Class> fetchClasses(EntityManager em) {

        ClassManager cm = new ClassManager();

        List<Class> classlist = cm.findAllClasses(em);
        clsfees.clear();
        classlist.stream().forEach((classlist1) -> {
            clsfees.addAll(classlist1);
        });

        return clsfees;
    }

}
