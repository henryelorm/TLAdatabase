/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.util.List;
import javax.persistence.EntityManager;
import tladatabase.Entity.ProgramRecords;
import tladatabase.EntityManager.RecordsManager;

/**
 *
 * @author Elorm
 */
public class RecordsNullified {
    
    public void programRecordsNullified(EntityManager em) {
        
        RecordsManager rmg = new RecordsManager();
        ProgramRecords prg = new ProgramRecords();
        List<ProgramRecords> listPr = rmg.findAllProgramRecord(em);
        
        if (listPr.isEmpty()) {
            em.getTransaction().begin();
            prg.setId(100L);
            prg.setComports(null);
            em.persist(prg);
            em.getTransaction().commit();
        }
        
    }
    
}
