/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.EntityManager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tladatabase.Entity.Fees_Class;

/**
 *
 * @author Elorm
 */
public class Fees_ClassManager {

    public void removeFeesClass(EntityManager em, Long id) {
        Fees_Class fc = findFeesClass(em, id);
        if (fc != null) {

            em.remove(fc);
        }
    }

    public Fees_Class findFeesClass(EntityManager em, Long id) {

        return em.find(Fees_Class.class, id);
    }

    public List<Fees_Class> findAllFeesClass(EntityManager em) {

        TypedQuery<Fees_Class> query = em.createQuery("SELECT e FROM Fees_Class e", Fees_Class.class);

        return query.getResultList();
    }

}
