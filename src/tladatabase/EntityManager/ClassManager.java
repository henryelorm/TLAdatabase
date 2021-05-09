/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.EntityManager;

import java.util.List;
import javafx.scene.control.TabPane;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tladatabase.Entity.Class;

/**
 *
 * @author Elorm
 */
public class ClassManager {
    
    public void removeClass(EntityManager em, Long id) {
        Class cl = findClass(em, id);
        if (cl != null) {
            em.remove(cl);
        }
    }
    
    public Class findClass(EntityManager em, Long id) {
        return em.find(Class.class, id);
    }
    
    public List<Class> findAllClasses(EntityManager em) {
        
        TypedQuery<Class> query = em.createQuery("SELECT e FROM Class e", Class.class);
        
        return query.getResultList();
    }
    
    public Long getClassIdFromClassName(EntityManager em, String tabName) {
        
        List<Class> findclassId = findAllClasses(em);
        
        for (int i = 0; i < findclassId.size(); ++i) {
            
            if (findclassId.get(i).getClassName().equals(tabName)) {
                
                return findclassId.get(i).getId();
            }
        }
        return null;
    }
    
}
