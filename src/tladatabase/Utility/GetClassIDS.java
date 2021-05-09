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

/**
 *
 * @author Elorm
 */
public class GetClassIDS {

    ObservableList<Long> l = FXCollections.observableArrayList();

    public ObservableList<Long> getClassId(EntityManager em) {

        PromotionAlg pra = new PromotionAlg();
        ClassManager clm = new ClassManager();
        l.clear();
         
        List<tladatabase.Entity.Class> classList = clm.findAllClasses(em);

        for (int i = 0; i < classList.size(); i++) {

            l.add(classList.get(i).getId());
        }
       
        return pra.fetchClasses(em, l);

    }

}
