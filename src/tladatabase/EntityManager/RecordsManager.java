/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.EntityManager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import tladatabase.Entity.ProgramRecords;

/**
 *
 * @author Elorm
 */
public class RecordsManager {
    
    
     public void removeStudent(EntityManager em, String id) {
         ProgramRecords prg = findProgramRecord(em, id);
        if (prg != null) {
            em.remove(prg);
        }
    }

    public ProgramRecords findProgramRecord(EntityManager em, String id) {

        return em.find(ProgramRecords.class, id);
    }

    public List<ProgramRecords> findAllProgramRecord(EntityManager em) {

        TypedQuery<ProgramRecords> query = em.createQuery("SELECT e FROM ProgramRecords e", ProgramRecords.class);

        return query.getResultList();
    }
    
}
