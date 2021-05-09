/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.EntityManager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tladatabase.Entity.PRT;

/**
 *
 * @author Elorm
 */
public class PRTManager {

    public void removePRT(EntityManager em, String id) {
        PRT prt = findPRT(em, id);
        if (prt != null) {
            em.remove(prt);
        }
    }

    public PRT findPRT(EntityManager em, String id) {

        return em.find(PRT.class, id);
    }

    public List<PRT> findAllPRT(EntityManager em) {

        TypedQuery<PRT> query = em.createQuery("SELECT e FROM PRT e", PRT.class);

        return query.getResultList();
    }
}
