/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javax.persistence.EntityManager;
import tladatabase.Entity.PRT;
import tladatabase.EntityManager.PRTManager;
import java.util.List;
import javafx.scene.control.Button;

/**
 *
 * @author Elorm
 */
public class passwordPage {
    
    private static boolean isEmpty = false;
    
    public static boolean isIsEmpty() {
        return isEmpty;
    }
    
    public void pass(EntityManager em, Button removeBut, TextField f3, Text t1, Text t2, Text t3) {
        
        PRTManager pm = new PRTManager();
        
        List<PRT> prt = pm.findAllPRT(em);
        if (prt.isEmpty()) {
            t3.setOpacity(0.0);
            f3.setOpacity(0.0);
            removeBut.setDisable(true);
            isEmpty = true;
        } else {
            t1.setText("Current Password:");
            t2.setText("New Password:");
            
        }
    }
    
}
