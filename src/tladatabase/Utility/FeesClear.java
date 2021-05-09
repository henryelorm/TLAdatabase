/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javax.persistence.EntityManager;
import tladatabase.Entity.Fees_Student;

/**
 *
 * @author Elorm
 */
public class FeesClear {

    public static void clearOnPromotionAndDemotion(EntityManager em, String id) {

        Fees_Student fs = em.find(Fees_Student.class, id);
        if (fs != null) {
            System.out.println(id);
            fs.setFees(0.0);
        }

    }

}
