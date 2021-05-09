/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

import java.util.UUID;

/**
 *
 * @author Elorm
 */
public class IDs_generator {

    

    public static String class_Id() {

        return UUID.randomUUID().toString();

    }
    
    public static String student_Id(){
        return UUID.randomUUID().toString();
    }

}
