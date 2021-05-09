/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.util.List;
import javax.persistence.EntityManager;
import tladatabase.EntityManager.ClassManager;

/**
 *
 * @author Elorm
 */
public class GetClassIDFromName {

    public Long getClassIdFromClassName(EntityManager em, String className) {

        ClassManager cls = new ClassManager();
        
        List<tladatabase.Entity.Class> findclassId = cls.findAllClasses(em);

        for (int i = 0; i < findclassId.size(); ++i) {

            if (findclassId.get(i).getClassName().equals(className)) {

                return findclassId.get(i).getId();
            }
        }
        return null;
    }
}
