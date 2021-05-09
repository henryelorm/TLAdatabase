/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javax.persistence.EntityManager;
import tladatabase.Entity.PRT;

/**
 *
 * @author Elorm
 */
public class PassPage_manage {

    public static int UserInfo(EntityManager em, String name, String password) {

        PRT prt = new PRT();
        
        if(prt.getUserName().equals(name)&& prt.getPassword().equals(password)){
            return 1;
        }
         return 0;
    }

}
