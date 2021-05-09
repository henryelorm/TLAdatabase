/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javax.persistence.EntityManager;
import tladatabase.Entity.Class;
import tladatabase.EntityManager.ClassManager;

/**
 *
 * @author Elorm
 */
public class SelectionId {

    public Long getlassSelectionId(EntityManager em, ComboBox studclass) {

        ClassManager clm = new ClassManager();

        List<tladatabase.Entity.Class> findClassName = clm.findAllClasses(em);

        for (Class findClassName1 : findClassName) {
            if (studclass.getValue().equals(findClassName1.getClassName())) {
                return findClassName1.getId();
            }
        }
        return null;
    }
    
    public Long getClassSelectionId(EntityManager em, String studclass) {

        ClassManager clm = new ClassManager();

        List<tladatabase.Entity.Class> findClassName = clm.findAllClasses(em);

        for (Class findClassName1 : findClassName) {
            if (studclass.equals(findClassName1.getClassName())) {
                return findClassName1.getId();
            }
        }
        return null;
    }

    public static String getClassFromId(EntityManager em, Long id) {

        ClassManager clm = new ClassManager();

        List<Class> findClass = clm.findAllClasses(em);

        for (int i = 0; i < findClass.size(); ++i) {
            if (findClass.get(i).getId().equals(id)) {
                return findClass.get(i).getClassName();
            }
        }
        return "";

    }

    public static String getTabOfClassId(TabPane tabpane, String tabName) {

        for (int i = 0; i < tabpane.getTabs().size(); ++i) {

            if (tabpane.getTabs().get(i).getText().equals(tabName)) {

                return (tabpane.getTabs().get(i).getId());
            }
        }
        return "0";

    }

}
