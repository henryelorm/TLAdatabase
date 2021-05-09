/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

/**
 *
 * @author Elorm
 */
public class Null_and_Empty_Check {

    public static String nullCheck(String string) {

        RestrictTextField rtf = new RestrictTextField();

        rtf.setRestrict("[0-9]");
        rtf.setMaxLength(10);
        rtf.setText(string);

        if ("".equals(string)) {

            return "";

        } else {
            return string;
        }

    }

   
}
